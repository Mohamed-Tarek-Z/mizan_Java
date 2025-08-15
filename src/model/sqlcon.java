package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlcon {

    private String ip;
    private final String url;
    public Statement st;
    private final Connection conn;

    public sqlcon(String ip) throws SQLException {

        // Access configuration values
        this.ip = ip;
        url = "jdbc:sqlserver://" + ip + ":1433;databaseName=mizan;username=sa;password=111;encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url);
        st = conn.createStatement();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void inData(String tableName, String colName, String values) throws SQLException {
        st.execute("insert into " + tableName + " (" + colName + ") values (" + values + ")");
    }

    public ResultSet inDataReturnInseted(String tableName, String colName, String values, String output[]) throws SQLException {
        String out = "";
        for (String s : output) {
            out += "inserted." + s + ",";
        }
        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return st.executeQuery("insert into " + tableName + " (" + colName + ") output " + out + " values (" + values + ")");
    }

    public void inDataIdentityON(String tableName, String colName, String values) throws SQLException {
        st.execute("SET IDENTITY_INSERT " + tableName + " ON;  insert into " + tableName + " (" + colName + ") values (" + values + ");SET IDENTITY_INSERT " + tableName + " OFF;");
    }

    public void update(String tableName, String editData, String condition) throws SQLException {
        st.execute("update " + tableName + " set " + editData + " WHERE " + condition);
    }

    public void update(String tableName, String editData) throws SQLException {
        st.execute("update " + tableName + " set " + editData);
    }

    public void delData(String tableName, String condition) throws SQLException {
        st.execute("DELETE FROM " + tableName + " WHERE " + condition);
    }

    public ResultSet dataRead(String colName, String tableName, String condition) throws SQLException {
        return st.executeQuery("select " + colName + " from " + tableName + " where " + condition);
    }

    public ResultSet dataRead(String colName, String tableName) throws SQLException {
        return st.executeQuery("select " + colName + " from " + tableName);
    }

    public boolean backup(String FileNameWithPath) throws SQLException {
        return st.execute("BACKUP DATABASE [mizan] TO  DISK = N'" + FileNameWithPath + "' WITH NOFORMAT, NOINIT");
    }

    public boolean restore(String FileNameWithPath) throws SQLException {
        return st.execute("RESTORE DATABASE [mizan] FROM  DISK = N'" + FileNameWithPath + "'");
    }
}
