package dao;

import exceptions.DatabaseException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.sqlcon;
import utils.utils;

public class OrderDAO {

    private final sqlcon dbConnection;

    public OrderDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addOrder(String totalWeight) throws DatabaseException {
        try {
            dbConnection.inData("orders", "ord_wight,ord_date", utils.ToDoubleEnglish(totalWeight) + ",GETDATE()");
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
