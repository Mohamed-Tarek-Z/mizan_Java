package controller;

import dao.ProductDAO;
import exceptions.*;
import java.util.List;
import model.Product;

public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAvailableProducts() throws DatabaseException {
        return productDAO.getProducts();
    }

    public List<Product> getAvailableProductsLike(String subOfName) throws DatabaseException {
        return productDAO.getProductsLike(subOfName);
    }

    public Product getProduct(String productName) throws DatabaseException {
        return productDAO.getProductByName(productName);
    }

    public Product getProduct(int id) throws DatabaseException, BusinessException {
        return productDAO.getProductById(id);
    }

    public void addNewProduct(String productName, String weight_of_con, String color, boolean IsBox) throws DatabaseException, BusinessException {
        if (productName != null && !productName.trim().isEmpty()
                && weight_of_con != null && !weight_of_con.trim().isEmpty()) {
            productDAO.addProduct(new Product(0, productName, weight_of_con, color, IsBox));
        } else {
            throw new BusinessException("برجاء إدخال البيانات كاملة");
        }
    }

    public void updateProduct(int proId, String productName, String weight_of_con, String color, boolean IsBox) throws DatabaseException, BusinessException {
        if (productName != null && !productName.trim().isEmpty()
                && weight_of_con != null && !weight_of_con.trim().isEmpty()) {
            productDAO.editProduct(new Product(proId, productName, weight_of_con, color, IsBox));
        } else {
            throw new BusinessException("برجاء إدخال البيانات كاملة");
        }
    }

    public boolean removeProduct(int proId) throws DatabaseException, BusinessException {
        return productDAO.deleteProduct(proId);
    }
}
