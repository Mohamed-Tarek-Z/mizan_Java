package dao;

import exceptions.DatabaseException;
import model.sqlcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bag;

public class StorageDAO {

    private final sqlcon dbConnection;

    public StorageDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Bag> getBags(String proName) throws DatabaseException {
        try {
            List<Bag> Bags = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead("storage_id,pro_id,tot_wight,weight_,lot,num_of_con,pallet_numb,used,date_",
                    "storage ",
                    "storage.pro_id=(select pro_id from products where pro_name=N'"
                    + proName + "') order by "
                    + "CASE WHEN ISNUMERIC(lot) = 1 AND lot NOT LIKE '%[^0-9]%' THEN 0 ELSE 1 END, CASE WHEN "
                    + "ISNUMERIC(lot) = 1 AND lot NOT LIKE '%[^0-9]%' THEN CAST(lot AS bigint) ELSE NULL END DESC, lot"
                    + ", used, pallet_numb DESC, storage_id DESC");
            while (rs.next()) {
                Bags.add(new Bag(rs.getInt("storage_id"), rs.getInt("pro_id"), rs.getDouble("tot_wight"), rs.getDouble("weight_"), rs.getString("lot"),
                        rs.getInt("num_of_con"), rs.getInt("pallet_numb"), rs.getBoolean("used"), rs.getDate("date_")));
            }

            return Bags;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }

    public List<Bag> getBagsToReport(int topNumber, String proName, String palletNumber, String lotNumber) throws DatabaseException {
        try {
            List<Bag> Bags = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead("TOP(" + (topNumber <= 0 ? 20 : topNumber) + ") storage_id,pro_id,tot_wight,weight_,lot,num_of_con,pallet_numb,used,date_", "storage",
                    " pro_id=(select pro_id from products where pro_name=N'"
                    + proName + "' ) and pallet_numb=" + palletNumber + " and lot=N'" + lotNumber + "' order by pallet_numb ,storage_id DESC ");
            while (rs.next()) {
                Bags.add(new Bag(rs.getInt("storage_id"), rs.getInt("pro_id"), rs.getDouble("tot_wight"), rs.getDouble("weight_"), rs.getString("lot"),
                        rs.getInt("num_of_con"), rs.getInt("pallet_numb"), rs.getBoolean("used"), rs.getDate("date_")));
            }

            return Bags;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }

    public List<String[]> getPalletsForReport(String proName) throws DatabaseException {
        try {
            List<String[]> pallets = new ArrayList<>();
            ResultSet st = dbConnection.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage",
                    "pro_id=(select pro_id from products where pro_name=N'"
                    + proName + "' )  GROUP BY lot,pallet_numb,used");

            while (st.next()) {
                pallets.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4),
                    st.getBoolean(5) + ""});
            }

            return pallets;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات المخزن", ex);
        }
    }

    public void addBag(Bag bag) throws DatabaseException {
        try {
            dbConnection.inData("storage", "pro_id,tot_wight,weight_,lot,pallet_numb,date_,num_of_con,used",
                    bag.getPro_id() + "," + bag.getTot_wight() + ","
                    + bag.getWeight() + ",N'"
                    + bag.getLot() + "',"
                    + bag.getPallet_numb() + ",GETDATE(),"
                    + bag.getNum_of_con() + ","
                    + (bag.isUsed() ? 1 : 0) + " "
            );
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء إضافة الشكارة", ex);
        }
    }

    public void editBag(Bag bag) throws DatabaseException {
        try {
            dbConnection.update("storage",
                    "weight_= " + bag.getWeight() + ","
                    + "tot_wight= " + bag.getTot_wight() + ","
                    + "num_of_con= " + bag.getNum_of_con() + ","
                    + "lot= N'" + bag.getLot() + "',"
                    + "pallet_numb=" + bag.getPallet_numb() + ","
                    + " pro_id=" + bag.getPro_id() + ","
                    + "used=" + (bag.isUsed() ? 1 : 0) + " ",
                    "storage_id=" + bag.getId() + ""
            );
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء تعديل الشكارة", ex);
        }
    }

    public boolean deleteBag(String bagId) throws DatabaseException {
        try {
            dbConnection.delData("storage", "storage_id=" + bagId + "");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حذف الشكارة", ex);
        }
    }

    public int[] getStorageCount(int palletNumber, String lotNumber, int productId) throws DatabaseException {
        try {
            ResultSet rs = dbConnection.dataRead("count(storage_id)", "storage",
                    "pallet_numb=" + palletNumber + " AND lot=N'" + lotNumber + "' AND pro_id=" + productId + " AND used =" + 1 + " ");
            rs.next();
            int usedC = rs.getInt(1);
            rs = dbConnection.dataRead("count(storage_id)", "storage",
                    "pallet_numb=" + palletNumber + " AND lot=N'" + lotNumber + "' AND pro_id=" + productId + " AND used =" + 0 + " ");
            rs.next();
            int nUsedC = rs.getInt(1);
            return new int[]{usedC, nUsedC};
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء عد الشكارة", ex);
        }
    }

    public Bag getBagbyId(int bagId) throws DatabaseException {
        try {
            Bag bag = null;
            ResultSet rs = dbConnection.dataRead("storage_id,pro_id,tot_wight,weight_,lot,num_of_con,pallet_numb,used,date_",
                    "storage ", "storage_id=" + bagId + "");
            if (rs.next()) {
                bag = new Bag(rs.getInt("storage_id"), rs.getInt("pro_id"), rs.getDouble("tot_wight"), rs.getDouble("weight_"), rs.getString("lot"),
                        rs.getInt("num_of_con"), rs.getInt("pallet_numb"), rs.getBoolean("used"), rs.getDate("date_"));
            }
            return bag;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض الشكارة", ex);
        }
    }

    public String calc_pallet_weight(int palletNumber, String lotNumber, String productName) throws DatabaseException {
        try {

            ResultSet st = dbConnection.dataRead("sum(weight_)", "storage",
                    "lot=N'" + lotNumber + "' and pallet_numb="
                    + palletNumber
                    + " and pro_id=(select pro_id from products where pro_name=N'"
                    + productName + "')");
            st.next();
            return st.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءحساب مجموع البالتة", ex);
        }
    }

    public List<String[]> getStockOfProduct(String ProName) throws DatabaseException {
        try {
            List<String[]> Stock = new ArrayList<>();
            ResultSet st = dbConnection.dataRead("lot ,COUNT( distinct pallet_numb)  ,count(weight_),SUM(weight_)", "storage",
                    "pro_id=(select pro_id from products where pro_name=N'" + ProName
                    + "')  group by lot");

            while (st.next()) {
                Stock.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4)});
            }

            return Stock;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

    public List<String[]> getAllStock() throws DatabaseException {
        try {
            List<String[]> Stock = new ArrayList<>();
            ResultSet st = dbConnection.dataRead("pro_name, lot, COUNT(weight_), SUM(weight_)", "storage ,products",
                    "storage.pro_id = products.pro_id group by pro_name, lot order by pro_name ");

            while (st.next()) {
                Stock.add(new String[]{st.getString(1), st.getString(2),
                    st.getString(3), st.getString(4)});
            }

            return Stock;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حساب المخزن", ex);
        }
    }

}
