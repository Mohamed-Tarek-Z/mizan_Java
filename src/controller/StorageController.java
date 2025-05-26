package controller;

import dao.ProductDAO;
import dao.StorageDAO;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.util.Date;
import java.util.List;
import model.Product;
import model.Bag;
import utils.utils;

public class StorageController {

    private final StorageDAO storageDAO;
    private final ProductDAO productDAO;
    private final utils util;

    public StorageController(StorageDAO storageDAO, ProductDAO productDAO) {
        this.storageDAO = storageDAO;
        this.productDAO = productDAO;
        this.util = new utils();
    }

    public int addStorage(String productName, String totalWeight, String netWeight, String lotNumber,
            String numOfCon, String palletNumber, boolean isUsed, String empty_pack) throws DatabaseException, BusinessException {
        Product product = productDAO.getProductByName(productName);

        int[] storageCountUsed_NUsed = storageDAO.getStorageCount((int) util.ToDoubleEnglish(palletNumber), util.ToStringEnglish(lotNumber), product.getId());
        if (storageCountUsed_NUsed[0] == 0 && storageCountUsed_NUsed[1] == 0) {
            Bag bag = new Bag(0, product.getId(), util.ToDoubleEnglish(totalWeight), util.ToDoubleEnglish(netWeight),
                    util.ToStringEnglish(lotNumber), (int) util.ToDoubleEnglish(numOfCon), (int) util.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(), util.ToDoubleEnglish(empty_pack)/100);
            storageDAO.addBag(bag);
            return (int) util.ToDoubleEnglish(palletNumber);
        } else if ((isUsed && storageCountUsed_NUsed[1] != 0) || (!isUsed && storageCountUsed_NUsed[0] != 0)) {
            //ex pallet mark not vaild
            throw new BusinessException("خطأ في تعليم الشكارة");
        } else if ((isUsed && storageCountUsed_NUsed[1] == 0 && storageCountUsed_NUsed[0] >= 20) || (!isUsed && storageCountUsed_NUsed[0] == 0 && storageCountUsed_NUsed[1] >= 20)) {
            return addStorage(productName, totalWeight, netWeight, lotNumber, numOfCon, (util.ToDoubleEnglish(palletNumber) + 1) + "", isUsed, empty_pack);
        } else if ((isUsed && storageCountUsed_NUsed[1] == 0 && storageCountUsed_NUsed[0] < 20) || (!isUsed && storageCountUsed_NUsed[0] == 0 && storageCountUsed_NUsed[1] < 20)) {
            Bag bag = new Bag(0, product.getId(), util.ToDoubleEnglish(totalWeight), util.ToDoubleEnglish(netWeight),
                    util.ToStringEnglish(lotNumber), (int) util.ToDoubleEnglish(numOfCon), (int) util.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(), util.ToDoubleEnglish(empty_pack)/100);
            storageDAO.addBag(bag);
            return (int) util.ToDoubleEnglish(palletNumber);
        }
        throw new BusinessException("خطأ في إدخال الشكارة");
    }
    // edite and delete

    public boolean updateStorage(int storage_id, String productName, String totalWeight, String netWeight, String lotNumber,
            String numOfCon, String palletNumber, boolean isUsed, String empty_pack) throws DatabaseException, BusinessException {
        Product product = productDAO.getProductByName(productName);

        int[] storageCount = storageDAO.getStorageCount((int) util.ToDoubleEnglish(palletNumber), util.ToStringEnglish(lotNumber), product.getId());
        if (storageCount[0] == 0 && storageCount[1] == 0) {
            Bag bag = new Bag(storage_id, product.getId(), util.ToDoubleEnglish(totalWeight), util.ToDoubleEnglish(netWeight),
                    util.ToStringEnglish(lotNumber), (int) util.ToDoubleEnglish(numOfCon), (int) util.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(), util.ToDoubleEnglish(empty_pack));
            storageDAO.editBag(bag);
            return true;
        } else if ((isUsed && storageCount[1] != 0) || (!isUsed && storageCount[0] != 0)) {
            //ex pallet mark not vaild
            throw new BusinessException("خطأ في تعليم الشكارة");
        } else if ((isUsed && storageCount[1] == 0 && storageCount[0] >= 20) || (!isUsed && storageCount[0] == 0 && storageCount[1] >= 20)) {
            throw new BusinessException("البالتة ممتلئة");
        } else if ((isUsed && storageCount[1] == 0 && storageCount[0] < 20) || (!isUsed && storageCount[0] == 0 && storageCount[1] < 20)) {
            Bag bag = new Bag(storage_id, product.getId(), util.ToDoubleEnglish(totalWeight), util.ToDoubleEnglish(netWeight),
                    util.ToStringEnglish(lotNumber), (int) util.ToDoubleEnglish(numOfCon), (int) util.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(),util.ToDoubleEnglish(empty_pack));
            storageDAO.editBag(bag);
            return true;
        }
        throw new BusinessException("خطأ في تعديل الشكارة");
    }

    public boolean removeBag(String StorageId) throws DatabaseException {
        return storageDAO.deleteBag(StorageId);
    }

    public Bag getBagById(int bagId) throws DatabaseException, BusinessException {
        Bag bag = storageDAO.getBagbyId(bagId);
        if (bag != null) {
            return bag;
        }
        throw new BusinessException("خطأ في أخذ بيانات الشكارة");
    }

    public List<Bag> getBags(String proName) throws DatabaseException {
        return storageDAO.getBags(proName);
    }

    public List<Bag> getBagsToReport(int topNumber, String proName, String palletNumber, String lotNumber) throws DatabaseException {
        return storageDAO.getBagsToReport(topNumber, proName, util.ToStringEnglish(palletNumber), util.ToStringEnglish(lotNumber));
    }

    public String calc_pallet_weight(String palletNumber, String lotNumber, String productName) throws DatabaseException {
        return storageDAO.calc_pallet_weight((int) util.ToDoubleEnglish(palletNumber), util.ToStringEnglish(lotNumber), productName);
    }

    public int countpallet(int palletNumber, String lotNumber, String productName, boolean IsUsed) throws DatabaseException, BusinessException {
        int[] count = storageDAO.getStorageCount(palletNumber, util.ToStringEnglish(lotNumber),
                productDAO.getProductByName(productName).getId());
        return (IsUsed ? count[0] : count[1]);
    }

    public List<String[]> getPalletsForReport(String proName) throws DatabaseException {
        return storageDAO.getPalletsForReport(proName);
    }

    public List<String[]> getStockOfProduct(String ProName) throws DatabaseException {
        return storageDAO.getStockOfProduct(ProName);
    }

    public List<String[]> getAllStock() throws DatabaseException {
        return storageDAO.getAllStock();
    }

}
