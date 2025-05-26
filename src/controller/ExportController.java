package controller;

import dao.*;
import exceptions.*;
import model.*;
import java.util.List;

public class ExportController {

    private final ExportDAO exportDAO;
    private final StorageDAO storageDAO;
    private final OrderDAO orderDAO;

    public ExportController(ExportDAO exportDAO, StorageDAO storageDAO, OrderDAO orderDAO) {
        this.exportDAO = exportDAO;
        this.storageDAO = storageDAO;
        this.orderDAO = orderDAO;
    }

    public List<String[]> getstatistics(String date1, String date2) throws DatabaseException {
        return exportDAO.getstatistics(date1, date2);
    }

    public void moveBagFromStorageToExport(String storageId, String clientName, String totalWeight) throws DatabaseException {
        exportDAO.moveBagFromStorageToExport(storageId, clientName, totalWeight);
    }

    public void removeExportByOrderId(String Ord_id) throws DatabaseException {
        exportDAO.removeExportByOrderId(Ord_id);
    }

    public boolean findExportByOrderId(String Ord_id) throws DatabaseException {
        return exportDAO.findExportByOrderId(Ord_id);
    }

    public List<String[]> getYuwmya(boolean oldWay, String dateFrom, String dateTo, String selectedCIDs) throws DatabaseException {
        return exportDAO.getYuwmya(oldWay, dateFrom, dateTo, selectedCIDs);
    }

    public String getPalletsForOrder(String proName, String clientName, String lot, String exported_date, String ord_wight) throws DatabaseException {
        return exportDAO.getPalletsForOrder(proName, clientName, lot, exported_date, ord_wight);
    }

    public void moveBagFromExportToStorage(boolean old, String lot, String proName, String clientName, String exported_date) throws DatabaseException, BusinessException {
        List<Export> exports = exportDAO.restoreExportToStorage(lot, proName, clientName, exported_date);

        if (!exports.isEmpty()) {

            for (Export exp : exports) {
                storageDAO.addBag(new Bag(exp.getStorageID(), exp.getPro_id(), exp.getTot_wight(), exp.getWeight(),
                        exp.getLot(), exp.getNum_of_con(), exp.getPallet_numb(), exp.isUsed(), exp.getInserted_date(), exp.getEmpty_pack()));
            }

            if (old) {

                if (!exportDAO.deleteExportOldWay(lot, proName, clientName, exported_date)) {
                    throw new BusinessException(" تم استرجاع البيان بنجاح");
                } else {
                    throw new BusinessException(" حدث خطأ ما");
                }

            } else {
                removeExportByOrderId(exports.getFirst().getOrd_id() + "");
                orderDAO.deleteOrderById(exports.getFirst().getOrd_id() + "");
                if (findExportByOrderId(exports.getFirst().getOrd_id() + "")) {
                    throw new BusinessException(" تم استرجاع البيان بنجاح");
                } else {
                    throw new BusinessException("رجا أعد تحميل الجدول");
                }
            }
        } else {
            throw new BusinessException("رجا أعد تحميل الجدول");
        }

    }
}
