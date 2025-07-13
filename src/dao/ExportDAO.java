package dao;

import exceptions.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.sqlcon;
import model.Export;
import utils.utils;

public class ExportDAO {

    private final sqlcon dbConnection;
    private final utils util;

    public ExportDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
        this.util = new utils();
    }

    public List<String[]> getstatistics(String date1, String date2) throws DatabaseException {
        try {
            List<String[]> statistics = new ArrayList<>();
            ResultSet st = dbConnection.dataRead(
                    "(select pro_name from products where products.pro_id=s.pro_id) as TypeName, lot as Lot, sum(bags) as Bags, sum(total) as Total",
                    "( select pro_id, lot, count(weight_)as bags, sum(weight_) as total from storage where (date_ between '"
                    + date1 + "'  and '" + date2
                    + "') group by lot, pro_id UNION ALL select pro_id, lot, count(weight_)as bags, sum(weight_) as total from export where (inserted_date between '"
                    + date1 + "'  and '" + date2
                    + "') group by lot, pro_id )s group by s.lot, s.pro_id order by s.pro_id");

            while (st.next()) {
                statistics.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4)});
            }

            return statistics;
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public void moveBagFromStorageToExport(String storageId, String clientName, String totalWeight) throws DatabaseException {
        try {
            dbConnection.inData("export", "pro_id,cli_id,tot_wight,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used,ord_id,empty_pack,storageID",
                    "(select pro_id from storage where storage_id=" + storageId + ")"
                    + ",(select  top(1) cli_id from clients where cli_name=N'" + clientName + "')"
                    + ",(select tot_wight from storage where storage_id=" + storageId + ")"
                    + ",(select weight_ from storage where storage_id=" + storageId + ")"
                    + ",(select lot from storage where storage_id= " + storageId + " )"
                    + ",(select date_ from storage where storage_id= " + storageId + " )"
                    + ",GETDATE() "
                    + ",(select num_of_con from storage where storage_id= " + storageId + " )"
                    + ",(select pallet_numb from storage where storage_id= " + storageId + " )"
                    + ",(select used from storage where storage_id= " + storageId + " )"
                    + ",(SELECT TOP 1 ord_id FROM orders where ord_wight=" + util.ToDoubleEnglish(totalWeight) + " ORDER BY ord_id DESC)"
                    + ",(select empty_pack from storage where storage_id= " + storageId + " ),"
                    + storageId
            );
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public void removeExportByOrderId(String Ord_id) throws DatabaseException {
        try {
            dbConnection.delData("export", "ord_id=" + Ord_id);
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public boolean findExportByOrderId(String Ord_id) throws DatabaseException {
        try {
            return !dbConnection.dataRead("*", "export", "ord_id=" + Ord_id).next();
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public String getPalletsForOrder(String proName, String clientName, String lot, String exported_date, String ord_wight) throws DatabaseException {
        try {
            String temp = "";
            ResultSet st = dbConnection.dataRead("distinct pallet_numb",
                    "export inner join orders on orders.ord_id=export.ord_id",
                    "pro_id = ( select pro_id from products where pro_name =N'"
                    + proName + "') and" + " cli_id IN ( select cli_id from clients where cli_name=N'"
                    + clientName + "') and" + " lot = N'" + util.toEnglishDigits(lot) + "' and"
                    + " exported_date='" + util.toEnglishDigits(exported_date) + "'and ord_wight =" + util.ToDoubleEnglish(ord_wight));
            while (st.next()) {
                temp += st.getString(1) + " , ";
            }
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public List<String[]> getYuwmya(boolean oldWay, String dateFrom, String dateTo, String selectedCIDs) throws DatabaseException {
        try {
            List<String[]> youmya = new ArrayList<>();
            ResultSet st = !oldWay ? dbConnection.dataRead(
                    "ord_wight,pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_)",
                    "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id inner join orders on orders.ord_id=export.ord_id",
                    !selectedCIDs.isEmpty() ? "exported_date between '" + dateFrom
                    + "' and '" + dateTo + "' and export.cli_id in (" + selectedCIDs + ") "
                    + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name "
                    : "exported_date between '" + dateFrom + "' and '" + dateTo + "' "
                    + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name ")
                    : dbConnection.dataRead(
                            "sum(weight_),pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_)",
                            "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id",
                            !selectedCIDs.isEmpty() ? "exported_date between '" + dateFrom + "' and '"
                            + dateTo + "' and export.cli_id in (" + selectedCIDs + ") "
                            + " group by products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name "
                            : "exported_date between '" + dateFrom + "' and '" + dateTo + "' "
                            + " group by products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name ");
            while (st.next()) {
                youmya.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4), st.getString(5), st.getString(6)});
            }

            return youmya;
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public List<Export> restoreExportToStorage(String lot, String proName, String clientName, String exported_date) throws DatabaseException {
        try {
            List<Export> exports = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead(
                    "*", "export", "lot=N'" + util.toEnglishDigits(lot) + "' "
                    + "and pro_id=(select pro_id from products where pro_name=N'" + proName + "') "
                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + clientName + "')"
                    + " and exported_date = '"
                    + util.toEnglishDigits(exported_date) + "'" + "and num_of_con is not null  and pallet_numb is not null order by exp_id DESC");
            while (rs.next()) {
                exports.add(new Export(rs.getInt("exp_id"), rs.getInt("pro_id"), rs.getInt("cli_id"), rs.getInt("ord_id"), rs.getInt("num_of_con"),
                        rs.getInt("pallet_numb"), rs.getDouble("tot_wight"),
                        rs.getDouble("weight_"), rs.getString("lot"),
                        rs.getBoolean("used"), rs.getDate("inserted_date"), rs.getDate("exported_date"), rs.getDouble("empty_pack"), rs.getInt("storageID")));
            }
            return exports;
        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public boolean deleteExportOldWay(String lot, String proName, String clientName, String exported_date) throws DatabaseException {
        try {
            dbConnection.delData("export", "lot=N'"
                    + util.toEnglishDigits(lot) + "'and pro_id=(select pro_id from products where pro_name=N'"
                    + proName + "') and cli_id=(select top(1) cli_id from clients where cli_name=N'"
                    + clientName + "') and exported_date = '"
                    + util.toEnglishDigits(exported_date)
                    + "' and num_of_con is not null  and pallet_numb is not null ");
            return !dbConnection.dataRead("*", "export", "lot=N'"
                    + util.toEnglishDigits(lot) + "'and pro_id=(select pro_id from products where pro_name=N'"
                    + proName + "') "
                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'"
                    + clientName + "')"
                    + " and exported_date = '"
                    + util.toEnglishDigits(exported_date)
                    + "'and num_of_con is not null  and pallet_numb is not null").next();

        } catch (SQLException ex) {
            Logger.getLogger(ExportDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

}
