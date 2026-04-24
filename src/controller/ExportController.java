package controller;

import repository.OrderRepository;
import repository.ExportRepository;
import repository.StorageRepository;
import exceptions.*;
import model.*;
import java.util.List;

public class ExportController {

    private final ExportRepository exportRepo;
    private final StorageRepository storageRepo;
    private final OrderRepository orderRepo;

    public ExportController(ExportRepository exportRepo, StorageRepository storageRepo, OrderRepository orderRepo) {
        this.exportRepo = exportRepo;
        this.storageRepo = storageRepo;
        this.orderRepo = orderRepo;
    }

    public List<String[]> getstatistics(String date1, String date2) throws DatabaseException {
        return exportRepo.getstatistics(date1, date2);
    }

    public void moveBagFromStorageToExport(String storageId, String clientName, String ord_id) throws DatabaseException {
        exportRepo.moveBagFromStorageToExport(storageId, clientName, ord_id);
    }

    public void removeExportByOrderId(String Ord_id) throws DatabaseException {
        exportRepo.removeExportByOrderId(Ord_id);
    }

    public boolean findExportByOrderId(String Ord_id) throws DatabaseException {
        return exportRepo.findExportByOrderId(Ord_id);
    }

    public List<String[]> getYuwmya(boolean oldWay, String dateFrom, String dateTo, String selectedCIDs) throws DatabaseException {
        return exportRepo.getYuwmya(oldWay, dateFrom, dateTo, selectedCIDs);
    }

    public String getDetailsForOrder(int ordID) throws DatabaseException {
        return exportRepo.getDetailsForOrder(ordID);
    }

    public void moveBagFromExportToStorage(boolean old, String lot, String proName, String clientName, String exported_date) throws DatabaseException, BusinessException {
        List<Export> exports = exportRepo.restoreExportToStorage(lot, proName, clientName, exported_date);

        if (!exports.isEmpty()) {

            for (Export exp : exports) {
                storageRepo.addBag(new Bag(exp.getStorageID(), exp.getPro_id(), exp.getTot_wight(), exp.getWeight(),
                        exp.getLot(), exp.getNum_of_con(), exp.getPallet_numb(), exp.isUsed(), exp.getInserted_date(), exp.getEmpty_pack()));
            }

            if (old) {

                if (!exportRepo.deleteExportOldWay(lot, proName, clientName, exported_date)) {
                    throw new BusinessException(" تم استرجاع البيان بنجاح");
                } else {
                    throw new BusinessException(" حدث خطأ ما");
                }

            } else {
                removeExportByOrderId(exports.getFirst().getOrd_id() + "");
                orderRepo.deleteOrderById(exports.getFirst().getOrd_id() + "");
                if (findExportByOrderId(exports.getFirst().getOrd_id() + "")) {
                    throw new BusinessException(" تم استرجاع البيان بنجاح");
                } else {
                    throw new BusinessException("رجا أعد تحميل الجدول يوجد شئ خطأ");
                }
            }
        } else {
            throw new BusinessException("رجا أعد تحميل الجدول يوجد شئ خطأ");
        }

    }
}
