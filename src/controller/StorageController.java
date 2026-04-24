package controller;

import repository.ProductRepository;
import repository.StorageRepository;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.util.Date;
import java.util.List;
import model.Product;
import model.Bag;
import utils.utils;

public class StorageController {

    private final StorageRepository storageRepo;
    private final ProductRepository productRepo;

    public StorageController(StorageRepository storageRepo, ProductRepository productRepo) {
        this.storageRepo = storageRepo;
        this.productRepo = productRepo;
    }

    public int[] addStorage(Bag req, boolean ignoreLimit) throws Exception {

        validateInput(req, ignoreLimit);

        int palletNumber = resolvePalletNumber(req);

        req.setPallet_numb(palletNumber);

        validateMarked(req);

        storageRepo.addBag(req);

        int count = countpallet(req);

        return new int[]{palletNumber, count};
    }

    // edite and delete
    public boolean updateStorage(int storage_id, String productName, String totalWeight, String netWeight, String lotNumber,
            String numOfCon, String palletNumber, boolean isUsed, String empty_pack) throws DatabaseException, BusinessException {
        Product product = productRepo.getProductByName(productName);

        int[] storageCount = storageRepo.getStorageCount((int) utils.ToDoubleEnglish(palletNumber), utils.toEnglishDigits(lotNumber), product.getId());
        if (storageCount[0] == 0 && storageCount[1] == 0) {
            Bag bag = new Bag(storage_id, product.getId(), utils.ToDoubleEnglish(totalWeight), utils.ToDoubleEnglish(netWeight),
                    utils.toEnglishDigits(lotNumber), (int) utils.ToDoubleEnglish(numOfCon), (int) utils.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(), utils.ToDoubleEnglish(empty_pack));
            storageRepo.editBag(bag);
            return true;
        } else if ((isUsed && storageCount[1] != 0) || (!isUsed && storageCount[0] != 0)) {
            //ex pallet mark not vaild
            throw new BusinessException("خطأ في تعليم الشكارة");
        } else if ((isUsed && storageCount[1] == 0 && storageCount[0] >= 20) || (!isUsed && storageCount[0] == 0 && storageCount[1] >= 20)) {
            throw new BusinessException("البالتة ممتلئة");
        } else if ((isUsed && storageCount[1] == 0 && storageCount[0] < 20) || (!isUsed && storageCount[0] == 0 && storageCount[1] < 20)) {
            Bag bag = new Bag(storage_id, product.getId(), utils.ToDoubleEnglish(totalWeight), utils.ToDoubleEnglish(netWeight),
                    utils.toEnglishDigits(lotNumber), (int) utils.ToDoubleEnglish(numOfCon), (int) utils.ToDoubleEnglish(palletNumber),
                    isUsed, new Date(), utils.ToDoubleEnglish(empty_pack));
            storageRepo.editBag(bag);
            return true;
        }
        throw new BusinessException("خطأ في تعديل الشكارة");
    }

    public boolean removeBag(String StorageId) throws DatabaseException {
        return storageRepo.deleteBag(StorageId);
    }

    public Bag getBagById(int bagId) throws DatabaseException, BusinessException {
        Bag bag = storageRepo.getBagbyId(bagId);
        if (bag != null) {
            return bag;
        }
        throw new BusinessException("خطأ في أخذ بيانات الشكارة");
    }

    public List<Bag> getBags(String proName) throws DatabaseException {
        return storageRepo.getBags(proName);
    }

    public List<Bag> getBagsToReport(int topNumber, String proName, String palletNumber, String lotNumber) throws DatabaseException {
        return storageRepo.getBagsToReport(topNumber, proName, utils.toEnglishDigits(palletNumber), utils.toEnglishDigits(lotNumber));
    }

    public String calc_pallet_weight(String palletNumber, String lotNumber, String productName) throws DatabaseException {
        return storageRepo.calc_pallet_weight((int) utils.ToDoubleEnglish(palletNumber), utils.toEnglishDigits(lotNumber), productName);
    }

    public int countpallet(int palletNumber, String lotNumber, String productName, boolean IsUsed) throws DatabaseException, BusinessException {
        int[] count = storageRepo.getStorageCount(palletNumber, utils.toEnglishDigits(lotNumber),
                productRepo.getProductByName(productName).getId());
        return (IsUsed ? count[0] : count[1]);
    }

    public int countpallet(int palletNumber, String lotNumber, int productID, boolean IsUsed) throws DatabaseException, BusinessException {
        int[] count = storageRepo.getStorageCount(palletNumber, utils.toEnglishDigits(lotNumber), productID);
        return (IsUsed ? count[0] : count[1]);
    }

    public int countpallet(Bag req) throws DatabaseException, BusinessException {
        int[] count = storageRepo.getStorageCount(req.getPallet_numb(), req.getLot(), req.getPro_id());
        return (req.isUsed() ? count[0] : count[1]);
    }

    public List<String[]> getPalletsForReport(String proName) throws DatabaseException {
        return storageRepo.getPalletsForReport(proName);
    }

    public List<String[]> getStockOfProduct(String ProName) throws DatabaseException {
        return storageRepo.getStockOfProduct(ProName);
    }

    public List<String[]> getAllStock() throws DatabaseException {
        return storageRepo.getAllStock();
    }

    private void validateInput(Bag req, boolean IgnoreLimit) throws BusinessException {
        if (req.getPro_id() == 0) {
            throw new BusinessException("برجاء إختيار صنف ");
        }
        if (req.getLot().isBlank()) {
            throw new BusinessException("برجاء إدخال رقم اللوط ");
        }

        if (req.getNum_of_con() < 1) {
            throw new BusinessException("برجاء إدخال عدد الكون ");
        }

        if (!IgnoreLimit) {
            if (req.getTot_wight() >= 60.0 && req.getTot_wight() < 0.0) {
                throw new BusinessException("خطأ في وزن الشيكاره");
            }
            if (req.getWeight() >= 60.0 && req.getWeight() < 0.0) {
                throw new BusinessException("خطأ في وزن الشيكاره");
            }
        }
    }

    private void validateMarked(Bag req) throws BusinessException, DatabaseException {
        int[] res = storageRepo.getStorageCount(req.getPallet_numb(), req.getLot(), req.getPro_id());
        int marked = res[0];
        int notMarked = res[1];

        if (marked > 0 && notMarked > 0) {
            throw new BusinessException("رجاء حل مشكلة التعليم اولا");
        }

        if ((marked == 0 && notMarked > 0 && req.isUsed())
                || (notMarked == 0 && marked > 0 && !req.isUsed())) {
            throw new BusinessException("خطأ في تعليم الشكاره");
        }
    }

    private int resolvePalletNumber(Bag req) throws BusinessException, DatabaseException {
        int pallet = req.getPallet_numb();

        while (countpallet(pallet, req.getLot(), req.getPro_id(), req.isUsed()) >= 20) {
            pallet++;
        }

        return pallet;
    }
}
