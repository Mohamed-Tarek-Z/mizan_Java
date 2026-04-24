package repository;

import exceptions.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.sqlcon;
import model.Export;
import model.Product;
import utils.utils;

public class ExportRepository {

    private final sqlcon dbConnection;

    public ExportRepository(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<String[]> getstatistics(String date1, String date2, Product p) throws DatabaseException {
        try {
            List<String[]> statistics = new ArrayList<>();
            ResultSet st = dbConnection.dataRead(
                    "(select pro_name from products where products.pro_id=s.pro_id) as TypeName, lot as Lot, sum(bags) as Bags, sum(total) as Total",
                    "( select pro_id, lot, count(weight_)as bags, sum(weight_) as total from storage where (date_ between '"
                    + date1 + "'  and '" + date2 + (p != null ? "') and pro_id =" + p.getId() : "')")
                    + " group by lot, pro_id UNION ALL select pro_id, lot, count(weight_)as bags, sum(weight_) as total from export where (inserted_date between '"
                    + date1 + "'  and '" + date2 + (p != null ? "') and pro_id =" + p.getId() : "')")
                    + " group by lot, pro_id )s group by s.lot, s.pro_id order by s.pro_id");

            while (st.next()) {
                statistics.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4)});
            }

            return statistics;
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public void moveBagFromStorageToExport(String storageId, String clientName, String ord_id) throws DatabaseException {
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
                    + "," + ord_id
                    + ",(select empty_pack from storage where storage_id= " + storageId + " ),"
                    + storageId
            );
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public void removeExportByOrderId(String Ord_id) throws DatabaseException {
        try {
            dbConnection.delData("export", "ord_id=" + Ord_id);
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public boolean findExportByOrderId(String Ord_id) throws DatabaseException {
        try {
            return !dbConnection.dataRead("*", "export", "ord_id=" + Ord_id).next();
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public String getDetailsForOrder(int ordID) throws DatabaseException {
        try {
            String temp = "";
            ResultSet st = dbConnection.dataRead("STRING_AGG(CAST(pallet_numb AS VARCHAR), ',') AS packages, SUM(sums) AS total_sum",
                    "( SELECT DISTINCT pallet_numb, sum(num_of_con) as sums FROM export WHERE ord_id = " + ordID + " group by pallet_numb )t");
            while (st.next()) {
                temp = "pallets " + st.getString(1) + " Number Of cone " + st.getString(2) + "\n";
            }
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public List<Object[]> getYuwmya(String dateFrom, String dateTo, String selectedCIDs) throws DatabaseException {
        try {
            List<Object[]> youmya = new ArrayList<>();
            ResultSet st = dbConnection.dataRead(
                    "ord_wight,pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_),export.ord_id",
                    "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id inner join orders on orders.ord_id=export.ord_id",
                    !selectedCIDs.isEmpty() ? "exported_date between '" + dateFrom
                    + "' and '" + dateTo + "' and export.cli_id in (" + selectedCIDs + ") "
                    + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date,export.ord_id order by exported_date ,cli_name "
                    : "exported_date between '" + dateFrom + "' and '" + dateTo + "' "
                    + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date,export.ord_id order by exported_date ,cli_name ");

            while (st.next()) {
                youmya.add(new Object[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4), st.getString(5), st.getString(6), st.getInt(7)});
            }

            return youmya;
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public List<Export> restoreExportToStorage(int ord_id) throws DatabaseException {
        try {
            List<Export> exports = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead("*", "export", "ord_id=" + ord_id + " order by exp_id DESC");
            while (rs.next()) {
                exports.add(new Export(rs.getInt("exp_id"), rs.getInt("pro_id"), rs.getInt("cli_id"), rs.getInt("ord_id"), rs.getInt("num_of_con"),
                        rs.getInt("pallet_numb"), rs.getDouble("tot_wight"),
                        rs.getDouble("weight_"), rs.getString("lot"),
                        rs.getBoolean("used"), rs.getDate("inserted_date"), rs.getDate("exported_date"), rs.getDouble("empty_pack"), rs.getInt("storageID")));
            }
            return exports;
        } catch (SQLException ex) {
            Logger.getLogger(ExportRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

}
