package controller;

import repository.ProductRepository;
import exceptions.*;
import java.util.List;
import model.Product;

public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAvailableProducts() throws DatabaseException {
        return productRepo.getProducts();
    }

    public List<Product> getAvailableProductsLike(String subOfName) throws DatabaseException {
        return productRepo.getProductsLike(subOfName);
    }

    public Product getProduct(String productName) throws DatabaseException, BusinessException {
        return productRepo.getProductByName(productName);
    }

    public Product getProduct(int id) throws DatabaseException, BusinessException {
        return productRepo.getProductById(id);
    }

    public void addNewProduct(String productName, String weight_of_con, String color, boolean IsBox) throws DatabaseException, BusinessException {
        if (productName != null && !productName.trim().isEmpty()
                && weight_of_con != null && !weight_of_con.trim().isEmpty()) {
            productRepo.addProduct(new Product(0, productName, weight_of_con, color, IsBox));
        } else {
            throw new BusinessException("برجاء إدخال البيانات كاملة");
        }
    }

    public void updateProduct(int proId, String productName, String weight_of_con, String color, boolean IsBox) throws DatabaseException, BusinessException {
        if (productName != null && !productName.trim().isEmpty()
                && weight_of_con != null && !weight_of_con.trim().isEmpty()) {
            productRepo.editProduct(new Product(proId, productName, weight_of_con, color, IsBox));
        } else {
            throw new BusinessException("برجاء إدخال البيانات كاملة");
        }
    }

    public boolean removeProduct(int proId) throws DatabaseException, BusinessException {
        return productRepo.deleteProduct(proId);
    }
}
