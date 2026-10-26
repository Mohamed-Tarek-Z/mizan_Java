package dataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import utils.Config;

public final class SqlServerStartup {

    private static final String SERVICE_NAME = Config.get("SERVICE_NAME", "MSSQLSERVER");

    // Change these settings to match your SQL Server installation.
    private static final String JDBC_URL
            = "jdbc:sqlserver://" + Config.get("ip", "localhost")
            + ":1433;databaseName=mizan;username=sa;password=111;encrypt=true;trustServerCertificate=true";

    private SqlServerStartup() {
    }

    public static Statement connectWhenReady()
            throws IOException, InterruptedException, SQLException {

        ensureServiceRunning();

        long deadline = System.nanoTime()
                + TimeUnit.SECONDS.toNanos(60);

        SQLException lastError = null;

        while (System.nanoTime() < deadline) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL);

                return connection.createStatement();

            } catch (SQLException e) {
                lastError = e;
                Thread.sleep(2000);
            }
        }

        throw new SQLException(
                "SQL Server did not become accessible within 60 seconds.",
                lastError
        );
    }

    private static void ensureServiceRunning()
            throws IOException, InterruptedException, SQLException {

        if (!Config.get("ip", "localhost").equalsIgnoreCase("localhost")) {
            return;
        }

        String status = runCommand(
                "sc.exe", "query", SERVICE_NAME
        );

        if (status.contains("RUNNING")) {

            return;
        }

        if (!status.contains("STOPPED")) {
            throw new SQLException(
                    "Unexpected SQL Server service state:\n" + status
            );
        }

        runCommand(
                "sc.exe", "start", SERVICE_NAME
        );

    }

    private static String runCommand(String... command)
            throws IOException, InterruptedException, SQLException {

        Process process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start();

        if (!process.waitFor(15, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            throw new SQLException(
                    "Timed out executing: " + String.join(" ", command)
            );
        }

        String output = new String(
                process.getInputStream().readAllBytes()
        );

        if (process.exitValue() != 0) {
            if (command.length >= 2
                    && command[1].equalsIgnoreCase("start")) {
                return output;
            }

            throw new SQLException(
                    "Command failed: " + String.join(" ", command)
                    + "\n" + output
            );
        }

        return output;
    }
}
