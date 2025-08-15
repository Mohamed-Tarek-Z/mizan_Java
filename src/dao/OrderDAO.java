package dao;

import exceptions.DatabaseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.sqlcon;

public class OrderDAO {

    private final sqlcon dbConnection;

    public OrderDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String addOrder() throws DatabaseException {
        try {
            ResultSet rs = dbConnection.inDataReturnInseted("orders", "ord_wight,ord_date", 0.0 + ",GETDATE()", new String[]{"ord_id"});
            if (rs.next()) {
                return rs.getString("ord_id");
            }
            return "0";
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }

    public void updateOrder(String id, double totalWeight) throws DatabaseException {
        try {
            dbConnection.update("orders", "ord_wight=" + totalWeight, "ord_id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }

    public void deleteOrderById(String ordTid) throws DatabaseException {
        try {
            dbConnection.delData("orders", "ord_id=" + ordTid);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }
}
