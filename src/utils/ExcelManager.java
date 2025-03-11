package utils;

import exceptions.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

    private final Locale arabicLocale = Locale.forLanguageTag("ar");
    private final DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
    private LocalDate date_now = LocalDate.now();
    private final utils util = new utils();

    private final String EXCEL_PATH = System.getProperty("user.dir") + "\\Temp\\myFile.xlsx";

    private String contactForHeader(String ClientName, String productName, String lotNum) {
        date_now = LocalDate.now();
        String _1 = "                                                  إذن تـسليم بضاعة\n";
        String _2 = "السيد :" + ClientName + "";
        String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
        String _4 = "صنف :" + productName;
        String _5 = "رقم اللـــوط :" + util.ToDoubleArabic(util.ToStringEnglish(lotNum));

        for (int i = 0; i < 70 - ClientName.length(); i++) {
            _2 += " ";
        }
        for (int i = 0; i < 65 - productName.length(); i++) {
            _4 += " ";

        }
        return (_1 + _2 + _3 + "\n" + "\n" + _4 + _5);
    }

    public boolean excel_120(List<String> orderIds, String totalWeight, String ClientName, String productName,
            JTable BagsTable, String excelBackupPath, boolean isBoxes) throws DatabaseException, BusinessException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\120.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, productName, BagsTable.getValueAt(0, 2) + ""));

            cell = sheet.getRow(23).getCell(11);
            cell.setCellValue((isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + util.ToDoubleArabic(orderIds.size() + "")
                    + (isBoxes ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــــــــــــوزن :       " + util.ToDoubleArabic(totalWeight) + "");

            this.cell_functions(cell, sheet);
            cell = sheet.getRow(26).getCell(11);
            String strFormula1 = "MOD((SUM(A23,E23,H23,K23,N23,Q23)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(13);
            strFormula1 = "MOD((SUM(C23,F23,I23,L23,O23,R23)+((SUM(A23,E23,H23,K23,N23,Q23)/1000))-MOD(SUM(A23,E23,H23,K23,N23,Q23)/1000,1)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(15);
            strFormula1 = "(SUM(C23,F23,I23,L23,O23,R23)+(SUM(A23,E23,H23,K23,N23,Q23)/1000)-MOD((SUM(C23,F23,I23,L23,O23,R23)+(SUM(A23,E23,H23,K23,N23,Q23)/1000)),1000))/1000";
            cell.setCellFormula(strFormula1);

            int f = 1;
            for (int t = 0; t < 121; t += 20) {
                for (int i = 2 + t; i <= 21 + t && i - 1 <= orderIds.size(); i++) {
                    if (orderIds.size() == i - 2) {
                        break;
                    }
                    cell = sheet.getRow(i - t).getCell(f);
                    cell.setCellValue(((1000 * util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                    cell = sheet.getRow(i - t).getCell(f + 1);
                    cell.setCellValue((int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                }
                f += 3;
            }
            create_excel_in_path(ClientName, workbook, excelBackupPath);
            return true;

        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    public boolean excel_160(List<String> orderIds, String totalWeight, String ClientName,
            String productName, JTable BagsTable, String excelBackupPath, boolean isBoxes) throws BusinessException, DatabaseException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\160.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, productName, BagsTable.getValueAt(0, 2) + ""));

            cell = sheet.getRow(23).getCell(16);
            cell.setCellValue((isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + util.ToDoubleArabic(orderIds.size() + "")
                    + (isBoxes ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــــــــــــوزن :       " + util.ToDoubleArabic(totalWeight) + "");

            this.cell_functions(cell, sheet);

            cell = sheet.getRow(22).getCell(19);
            String strFormula1 = "IF(MOD(SUM(T3:T22),1000)>0,MOD(SUM(T3:T22),1000),IF(COUNTBLANK(T3:T22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(20);
            strFormula1 = "IF(OR(SUM(U3:U22)>0,SUM(T3:T22)>0),(SUM(U3:U22))+(QUOTIENT(SUM(T3:T22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(22);
            strFormula1 = "IF(MOD(SUM(W3:W22),1000)>0,MOD(SUM(W3:W22),1000),IF(COUNTBLANK(W3:W22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(23);
            strFormula1 = "IF(OR(SUM(X3:X22)>0,SUM(W3:W22)>0),(SUM(X3:X22))+(QUOTIENT(SUM(W3:W22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(16);
            strFormula1 = "MOD((SUM(A23,E23,H23,K23,N23,Q23,T23,W23)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(19);
            strFormula1 = "MOD((SUM(C23,F23,I23,L23,O23,R23,U23,X23)+((SUM(A23,E23,H23,K23,N23,Q23,T23,W23)/1000))-MOD(SUM(A23,E23,H23,K23,N23,Q23,T23,W23)/1000,1)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(21);
            strFormula1 = "(SUM(C23,F23,I23,L23,O23,R23,U23,X23)+(SUM(A23,E23,H23,K23,N23,Q23,T23,W23)/1000)-MOD((SUM(C23,F23,I23,L23,O23,R23,U23,X23)+(SUM(A23,E23,H23,K23,N23,Q23,T23,W23)/1000)),1000))/1000";
            cell.setCellFormula(strFormula1);

            int f = 1;
            for (int t = 0; t < 161; t += 20) {

                for (int i = 2 + t; i <= 21 + t && i - 1 <= orderIds.size(); i++) {
                    if (orderIds.size() == i - 2) {
                        break;
                    }
                    cell = sheet.getRow(i - t).getCell(f);
                    cell.setCellValue(((1000 * util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                    cell = sheet.getRow(i - t).getCell(f + 1);
                    cell.setCellValue((int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                }
                f += 3;
            }

            create_excel_in_path(ClientName, workbook, excelBackupPath);

            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    public boolean excel_200(List<String> orderIds, String totalWeight, String ClientName, String productName,
            JTable BagsTable, String excelBackupPath, boolean isBoxes) throws BusinessException, DatabaseException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\200.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, productName, BagsTable.getValueAt(0, 2) + ""));

            cell = sheet.getRow(23).getCell(21);
            cell.setCellValue((isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + util.ToDoubleArabic(orderIds.size() + "")
                    + (isBoxes ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــــــــــــوزن :       " + util.ToDoubleArabic(totalWeight) + "");

            this.cell_functions(cell, sheet);

            cell = sheet.getRow(22).getCell(19);
            String strFormula1 = "IF(MOD(SUM(T3:T22),1000)>0,MOD(SUM(T3:T22),1000),IF(COUNTBLANK(T3:T22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(20);
            strFormula1 = "IF(OR(SUM(U3:U22)>0,SUM(T3:T22)>0),(SUM(U3:U22))+(QUOTIENT(SUM(T3:T22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(22);
            strFormula1 = "IF(MOD(SUM(W3:W22),1000)>0,MOD(SUM(W3:W22),1000),IF(COUNTBLANK(W3:W22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(23);
            strFormula1 = "IF(OR(SUM(X3:X22)>0,SUM(W3:W22)>0),(SUM(X3:X22))+(QUOTIENT(SUM(W3:W22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            ///
            cell = sheet.getRow(22).getCell(25);
            strFormula1 = "IF(MOD(SUM(Z3:Z22),1000)>0,MOD(SUM(Z3:Z22),1000),IF(COUNTBLANK(Z3:Z22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(26);
            strFormula1 = "IF(OR(SUM(AA3:AA22)>0,SUM(Z3:Z22)>0),(SUM(AA3:AA22))+(QUOTIENT(SUM(Z3:Z22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(28);
            strFormula1 = "IF(MOD(SUM(AC3:AC22),1000)>0,MOD(SUM(AC3:AC22),1000),IF(COUNTBLANK(AC3:AC22)=20,\" \",0))";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(22).getCell(29);
            strFormula1 = "IF(OR(SUM(AD3:AD22)>0,SUM(AC3:AC22)>0),(SUM(AD3:AD22))+(QUOTIENT(SUM(AC3:AC22),1000)),\" \")";
            cell.setCellFormula(strFormula1);

            ///
            cell = sheet.getRow(26).getCell(21);
            strFormula1 = "MOD((SUM(A23,E23,H23,K23,N23,Q23,T23,W23,Z23,AC23)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(24);
            strFormula1 = "MOD((SUM(C23,F23,I23,L23,O23,R23,U23,X23,AA23,AD23)+((SUM(A23,E23,H23,K23,N23,Q23,T23,W23,Z23,AC23)/1000))-MOD(SUM(A23,E23,H23,K23,N23,Q23,T23,W23,Z23,AC23)/1000,1)),1000)";
            cell.setCellFormula(strFormula1);

            cell = sheet.getRow(26).getCell(27);
            strFormula1 = "(SUM(C23,F23,I23,L23,O23,R23,U23,X23,AA23,AD23)+(SUM(A23,E23,H23,K23,N23,Q23,T23,W23,Z23,AC23)/1000)-MOD((SUM(C23,F23,I23,L23,O23,R23,U23,X23,AA23,AD23)+(SUM(A23,E23,H23,K23,N23,Q23,T23,W23,Z23,AC23)/1000)),1000))/1000";
            cell.setCellFormula(strFormula1);

            int f = 1;
            for (int t = 0; t < 201; t += 20) {

                for (int i = 2 + t; i <= 21 + t && i - 1 <= orderIds.size(); i++) {
                    if (orderIds.size() == i - 2) {
                        break;
                    }
                    cell = sheet.getRow(i - t).getCell(f);
                    cell.setCellValue(((1000 * util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                    cell = sheet.getRow(i - t).getCell(f + 1);
                    cell.setCellValue((int) util.ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                }
                f += 3;
            }

            create_excel_in_path(ClientName, workbook, excelBackupPath);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }

    }

    public boolean excel_60_60(List<String> sOrderIds, List<String> fOrderIds,
            String wieghtSOrder, String wieghtFOrder,
            String ClientName, String typeSOrder, String typeFOrder,
            boolean FIsBoxes, boolean SIsBoxes,
            JTable tableSOrder, JTable tableFOrder,
            String excelBackupPath
    ) throws DatabaseException, BusinessException {

        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\60-60.xlsx"))) {
            date_now = LocalDate.now();
            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;
                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + typeFOrder + "";
                String _5 = "رقم اللـــوط :" + util.ToDoubleArabic(util.ToStringEnglish(tableFOrder.getValueAt(0, 2) + ""));
                String _6 = "صنف :" + typeSOrder + "";
                String _7 = "رقم اللـــوط :" + util.ToDoubleArabic(util.ToStringEnglish(tableSOrder.getValueAt(0, 2) + ""));
                for (int i = 0; i < 66 - ClientName.length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 60 - typeSOrder.length(); i++) {
                    _4 += " ";

                }
                for (int i = 0; i < 65 - typeFOrder.length(); i++) {
                    _5 += " ";

                }

                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + _4 + _6 + "\n" + _5 + _7);
                String __1 = (FIsBoxes ? "عدد الصناديق : " : "عدد الشكاير : ")
                        + util.ToDoubleArabic(fOrderIds.size() + "")
                        + (FIsBoxes ? " صندوق" : "  شيكاره");
                String __2 = "الـــــــوزن :  " + util.ToDoubleArabic(wieghtFOrder) + "";
                String __3 = (SIsBoxes ? "عدد الصناديق : " : "عدد الشكاير : ")
                        + util.ToDoubleArabic(sOrderIds.size() + "")
                        + (SIsBoxes ? " صندوق" : "  شيكاره");
                String __4 = "الـــــــوزن :  " + util.ToDoubleArabic(wieghtSOrder) + "";
                cell = sheet.getRow(23).getCell(11);
                cell.setCellValue(__1 + "  " + __2 + "\n" + __3 + " " + __4);
                this.cell_functions(cell, sheet);
                cell = sheet.getRow(22).getCell(9);
                String strFormula1 = "IF(MOD(SUM(K3:K22),1000)>0,MOD(SUM(K3:K22),1000),IF(COUNTBLANK(K3:K22)=20,\" \",0))";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(25).getCell(11);
                strFormula1 = "MOD((SUM(A23,E23,H23)),1000)";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(25).getCell(13);
                strFormula1 = "MOD((SUM(C23,F23,I23)+((SUM(A23,E23,H23)/1000))-MOD(SUM(A23,E23,H23)/1000,1)),1000)";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(25).getCell(15);
                strFormula1 = "(SUM(C23,F23,I23)+(SUM(A23,E23,H23)/1000)-MOD((SUM(C23,F23,I23)+(SUM(A23,E23,H23)/1000)),1000))/1000";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(26).getCell(11);
                strFormula1 = "MOD((SUM(J23,N23,Q23)),1000)";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(26).getCell(13);
                strFormula1 = "MOD((SUM(L23,O23,R23)+((SUM(J23,N23,Q23)/1000))-MOD(SUM(J23,N23,Q23)/1000,1)),1000)";
                cell.setCellFormula(strFormula1);
                cell = sheet.getRow(26).getCell(15);
                strFormula1 = "(SUM(L23,O23,R23)+(SUM(J23,N23,Q23)/1000)-MOD((SUM(L23,O23,R23)+(SUM(J23,N23,Q23)/1000)),1000))/1000";
                cell.setCellFormula(strFormula1);
                int f = 1;
                for (int t = 0; t < 61; t += 20) {

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= fOrderIds.size(); i++) {
                        if (fOrderIds.size() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * util.ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()) - (int) util.ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) util.ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }
                f = 10;
                for (int t = 60; t < 121; t += 20) {

                    for (int i = 2 + t; i <= 21 + t && i - 60 - 1 <= sOrderIds.size(); i++) {
                        if (sOrderIds.size() == i + 60 - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * util.ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()) - (int) util.ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) util.ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()));
                    }
                    f += 3;
                }

                create_excel_in_path(ClientName, workbook, excelBackupPath);

            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }

    }

    public boolean stockExcel(List<String[]> stock) throws BusinessException {
        try {
            int RowIndex = 4;
            date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream EX = new FileInputStream(new File("Donot_Change\\Stock.xlsx"))) {
                workbook = new XSSFWorkbook(EX);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = sheet.getRow(1).getCell(1);
                cell.setCellValue("التاريـــخ :   " + date_now.format(arabicDateFormatter) + "");
                String prevname = "";
                ArrayList<Integer> reg = new ArrayList<>();
                for (String[] row : stock) {

                    if (RowIndex >= 6) {
                        CellCopyPolicy poli = new CellCopyPolicy();
                        poli.setCopyCellStyle(true);
                        poli.setCopyCellValue(true);
                        sheet.copyRows(RowIndex - 1, RowIndex, RowIndex, poli);
                    }
                    if (prevname.equalsIgnoreCase(row[0])) {
                        reg.add(RowIndex);
                    } else {
                        if (reg.size() > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                        }
                        reg.clear();
                        reg.add(RowIndex);
                        prevname = row[0];
                        cell = sheet.getRow(RowIndex).getCell(1);
                        cell.setCellValue(row[0]);
                    }

                    cell = sheet.getRow(RowIndex).getCell(2);
                    cell.setCellValue(util.ToDoubleArabic(row[1]));

                    cell = sheet.getRow(RowIndex).getCell(3);
                    cell.setCellValue(util.ToDoubleArabic(row[2]));

                    cell = sheet.getRow(RowIndex).getCell(4);
                    cell.setCellValue(util.ToDoubleArabic(row[3]));

                    RowIndex++;
                }
                if (reg.size() > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                }
                reg.clear();
            }
            try (FileOutputStream fileOut = new FileOutputStream(
                    System.getProperty("user.dir") + "\\Temp\\Stock.xlsx")) {
                workbook.write(fileOut);
            }
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\Temp\\"));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    public boolean staticsticsExcel(List<String[]> statstic, String date1, String date2) throws BusinessException {
        try {
            int RowIndex = 4;
            XSSFWorkbook workbook;
            try (FileInputStream EX = new FileInputStream(new File("Donot_Change\\Stock.xlsx"))) {
                workbook = new XSSFWorkbook(EX);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = sheet.getRow(1).getCell(1);
                cell.setCellValue(
                        "التاريـــخ من :   " + util.ToDoubleArabic(date1) + "  إلى : " + util.ToDoubleArabic(date2));
                double prevTot = 0.0;
                String prevName = "";
                ArrayList<Integer> reg = new ArrayList<>();
                for (String[] row : statstic) {

                    if (RowIndex >= 6) {
                        CellCopyPolicy poli = new CellCopyPolicy();
                        poli.setCopyCellStyle(true);
                        poli.setCopyCellValue(true);
                        sheet.copyRows(RowIndex - 1, RowIndex, RowIndex, poli);
                    }
                    if (prevName.equalsIgnoreCase(row[0])) {
                        reg.add(RowIndex);
                        prevTot += Double.parseDouble(row[3]);
                    } else {
                        if (reg.size() > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                            sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 5, 5));
                            cell = sheet.getRow(reg.get(0)).getCell(5);
                            cell.setCellValue(util.ToDoubleArabic(new DecimalFormat("#.###").format(prevTot)));
                        }
                        reg.clear();
                        reg.add(RowIndex);
                        prevName = row[0];

                        cell = sheet.getRow(RowIndex).getCell(1);
                        cell.setCellValue(row[0]);

                        prevTot = Double.parseDouble(row[3]);
                    }

                    cell = sheet.getRow(RowIndex).getCell(2);
                    cell.setCellValue(util.ToDoubleArabic(row[1]));

                    cell = sheet.getRow(RowIndex).getCell(3);
                    cell.setCellValue(util.ToDoubleArabic(row[2]));

                    cell = sheet.getRow(RowIndex).getCell(4);
                    cell.setCellValue(util.ToDoubleArabic(row[3]));

                    RowIndex++;
                }
                if (reg.size() > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 5, 5));
                }
                reg.clear();
            }
            try (FileOutputStream fileOut = new FileOutputStream(
                    System.getProperty("user.dir") + "\\Temp\\Statistics.xlsx")) {
                workbook.write(fileOut);
            }
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\Temp\\"));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    private void cell_functions(Cell cell, XSSFSheet sheet) {
        cell = sheet.getRow(22).getCell(0);
        String strFormula = "IF(MOD(SUM(B3:B22),1000)>0,MOD(SUM(B3:B22),1000),IF(COUNTBLANK(B3:B22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(2);
        strFormula = "IF(OR(SUM(C3:C22)>0,SUM(B3:B22)>0),(SUM(C3:C22))+(QUOTIENT(SUM(B3:B22),1000)),\" \")";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(4);
        strFormula = "IF(MOD(SUM(E3:E22),1000)>0,MOD(SUM(E3:E22),1000),IF(COUNTBLANK(E3:E22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(5);
        strFormula = "IF(OR(SUM(F3:F22)>0, SUM(E3:E22)>0),(SUM(F3:F22))+(QUOTIENT(SUM(E3:E22),1000)),\" \")";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(7);
        strFormula = "IF(MOD(SUM(H3:H22),1000)>0,MOD(SUM(H3:H22),1000),IF(COUNTBLANK(H3:H22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(8);
        strFormula = "IF(OR(SUM(I3:I22)>0,SUM(H3:H22)>0),(SUM(I3:I22))+(QUOTIENT(SUM(H3:H22),1000)),\" \")";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(10);
        strFormula = "IF(MOD(SUM(K3:K22),1000)>0,MOD(SUM(K3:K22),1000),IF(COUNTBLANK(K3:K22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(11);
        strFormula = "IF(OR(SUM(L3:L22)>0,SUM(K3:K22)>0),(SUM(L3:L22))+(QUOTIENT(SUM(K3:K22),1000)),\" \")";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(13);
        strFormula = "IF(MOD(SUM(N3:N22),1000)>0,MOD(SUM(N3:N22),1000),IF(COUNTBLANK(N3:N22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(14);
        strFormula = "IF(OR(SUM(O3:O22)>0,SUM(N3:N22)>0),(SUM(O3:O22))+(QUOTIENT(SUM(N3:N22),1000)),\" \")";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(16);
        strFormula = "IF(MOD(SUM(Q3:Q22),1000)>0,MOD(SUM(Q3:Q22),1000),IF(COUNTBLANK(Q3:Q22)=20,\" \",0))";
        cell.setCellFormula(strFormula);

        cell = sheet.getRow(22).getCell(17);
        strFormula = "IF(OR(SUM(R3:R22)>0,SUM(Q3:Q22)>0),(SUM(R3:R22))+(QUOTIENT(SUM(Q3:Q22),1000)),\" \")";
        cell.setCellFormula(strFormula);
    }

    private void create_excel_in_path(String ClientName, XSSFWorkbook workbook, String excelBackupPath) throws DatabaseException, BusinessException {
        try {
            date_now = LocalDate.now();

            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\querys"));
            File file = util.NewName(System.getProperty("user.dir") + "\\querys\\" + ClientName + "~" + date_now + ".xlsx");
            try (FileOutputStream outFile = new FileOutputStream(file)) {
                try (workbook) {
                    workbook.write(outFile);
                    Desktop desktop = Desktop.getDesktop();

                    if (!excelBackupPath.isBlank()) {
                        File file1 = util.NewName(excelBackupPath + "\\" + ClientName + "~" + date_now + ".xlsx");
                        FileOutputStream outFile1 = new FileOutputStream(file1);
                        workbook.write(outFile1);
                    }

                    desktop.open(file);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    public void excel_Ticket(ArrayList<String> values) throws BusinessException {
        try {

            try (FileInputStream EX = new FileInputStream(new File("Donot_Change\\Ticket.xlsx")); XSSFWorkbook workbook = new XSSFWorkbook(EX)) {

                XSSFSheet sheet = workbook.getSheetAt(0);

                for (int i = 0; i < values.size(); i++) {
                    Cell cell = sheet.getRow(i).getCell(0);
                    if (cell != null) {
                        cell.setCellValue(values.get(i));
                    }
                }

                try (FileOutputStream fileOut = new FileOutputStream(EXCEL_PATH)) {
                    // Write workbook to memory
                    workbook.write(fileOut);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

}
