package dao;

import exceptions.BusinessException;
import exceptions.DatabaseException;
import model.sqlcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import utils.utils;

public class ProductDAO {

    private final sqlcon dbConnection;
    private final utils util;

    public ProductDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
        this.util = new utils();
    }

    public List<Product> getProducts() throws DatabaseException {
        try {
            List<Product> products = new ArrayList<>();

            ResultSet rs = dbConnection.dataRead("pro_id, pro_name, weight_of_con, Color, IsBox", "products");
            while (rs.next()) {
                products.add(new Product(rs.getInt("pro_id"), rs.getString("pro_name"), rs.getString("weight_of_con"), rs.getString("Color"), rs.getBoolean("IsBox")));
            }

            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب البيانات", ex);
        }
    }

    public List<Product> getProductsLike(String subOfName) throws DatabaseException {
        try {
            List<Product> products = new ArrayList<>();

            ResultSet rs = dbConnection.dataRead("pro_id, pro_name, weight_of_con, Color, IsBox", "products", "pro_name like N'%" + subOfName.strip() + "%' ");
            while (rs.next()) {
                products.add(new Product(rs.getInt("pro_id"), rs.getString("pro_name"), rs.getString("weight_of_con"), rs.getString("Color"), rs.getBoolean("IsBox")));
            }

            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب البيانات التي تشبه " + subOfName, ex);
        }
    }

    public void addProduct(Product pro) throws DatabaseException {
        try {
            dbConnection.inData("products", "pro_name, weight_of_con, Color, IsBox",
                    "N'" + pro.getName() + "',N'"
                    + util.toEnglishDigits(pro.getWeight_of_con()) + "',N'"
                    + pro.getColor() + "'," + (pro.isIsBox() ? "1" : "0"));
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء إضافة البيانات", ex);
        }
    }

    public void editProduct(Product pro) throws DatabaseException {
        try {
            dbConnection.update("products",
                    "pro_name=N'" + pro.getName() + "' ,weight_of_con=N'"
                    + util.toEnglishDigits(pro.getWeight_of_con()) + "' ,Color=N'"
                    + pro.getColor() + "',IsBox = " + (pro.isIsBox() ? "1" : "0"),
                    "pro_id=" + pro.getId() + " ");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء تعديل بيانات الصنف: " + pro.getName(), ex);
        }
    }

    public boolean deleteProduct(int proId) throws DatabaseException, BusinessException {
        try {
            if (!dbConnection.dataRead("*", "storage", "pro_id=" + proId + "").next()) {
                dbConnection.delData("products", "pro_id=" + proId + " ");
                return true;
            }
            throw new BusinessException("رجاء مسح الشكائر أولا");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء حذف البيانات", ex);
        }

    }

    public Product getProductByName(String productName) throws DatabaseException, BusinessException {
        try {
            ResultSet rs = dbConnection.dataRead("pro_id, pro_name, weight_of_con, Color, IsBox", "products", "pro_name=N'" + productName + "'");
            if (rs.next()) {
                return new Product(rs.getInt("pro_id"), rs.getString("pro_name"), rs.getString("weight_of_con"), rs.getString("Color"), rs.getBoolean("IsBox"));
            }
            throw new BusinessException("لايوجد صنف بأسم: " + productName);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات صنف بأسم : " + productName, ex);
        }
    }

    public Product getProductById(int id) throws DatabaseException, BusinessException {
        try {
            ResultSet rs = dbConnection.dataRead("pro_id, pro_name, weight_of_con, Color, IsBox", "products", "pro_id=" + id);
            if (rs.next()) {
                return new Product(rs.getInt("pro_id"), rs.getString("pro_name"), rs.getString("weight_of_con"), rs.getString("Color"), rs.getBoolean("IsBox"));
            }
            throw new BusinessException("لايوجد صنف بمسلسل : " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء طلب بيانات صنف بأسم : " + id, ex);
        }
    }

    // Other database-related methods...
}
