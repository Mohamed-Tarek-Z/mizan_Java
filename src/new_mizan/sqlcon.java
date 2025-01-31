package new_mizan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class sqlcon {

    String ip;
    private String url;
    Statement st;
    Connection conn;

    public sqlcon() throws Exception {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\Temp\\config.properties")) {
            properties.load(input);

            // Access configuration values
            ip = properties.getProperty("ip","localhost");
            System.out.println(ip);
            url = "jdbc:sqlserver://" + ip + ":1433;databaseName=mizan;username=sa;password=111;encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();
        } catch (IOException e) {
            ip = "localhost";
            System.out.println(ip);

            url = "jdbc:sqlserver://" + ip + ":1433;databaseName=mizan;username=sa;password=111;encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();

            e.printStackTrace();
        }
    }

    public void inData(String tableName, String colName, String values) {
        String qu = "insert into " + tableName + " (" + colName + ") values (" + values + ")";
        try {
            st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String tableName, String editData, String condition) {
        String qu = "update " + tableName + " set " + editData + " WHERE " + condition;
        try {
            st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String tableName, String editData) {
        String qu = "update " + tableName + " set " + editData;
        try {
            st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delData(String tableName, String condition) {
        String qu = "DELETE FROM " + tableName + " WHERE " + condition;
        try {
            st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet dataRead(String colName, String tableName, String condition) {
        String qu = "select " + colName + " from " + tableName + " where " + condition;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet dataRead(String colName, String tableName) {
        String qu = "select " + colName + " from " + tableName;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean backup(String FileNameWithPath) {
        String qu = "BACKUP DATABASE [mizan] TO  DISK = N'" + FileNameWithPath + "' WITH NOFORMAT, NOINIT";
        try {
            return st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean restore(String FileNameWithPath) {
        String qu = "RESTORE DATABASE [mizan] FROM  DISK = N'" + FileNameWithPath + "'";
        try {
            return st.execute(qu);
        } catch (SQLException ex) {
            Logger.getLogger(sqlcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
