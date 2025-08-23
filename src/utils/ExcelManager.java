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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.Bag;
import model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

    private final Locale arabicLocale = Locale.forLanguageTag("ar");
    private final DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
    private LocalDate date_now = LocalDate.now();

    private final String EXCEL_PATH = System.getProperty("user.dir") + "\\Temp\\myFile.xlsx";

    /**
     * this method it used in creating the header for the excels
     *
     * @param ClientName String that contain Client name
     * @param productName String that contain product name
     * @param lotNum String that contain Lot number
     *
     * @return String that contain the right formatted header for excel file
     */
    private String contactForHeader(String ClientName, String productName, String lotNum) {
        date_now = LocalDate.now();
        String _1 = "                                                  إذن تـسليم بضاعة\n";
        String _2 = "السيد :" + ClientName + "";
        String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
        String _4 = "صنف :" + productName;
        String _5 = "رقم اللـــوط :" + lotNum;

        for (int i = 0; i < 70 - ClientName.length(); i++) {
            _2 += " ";
        }
        for (int i = 0; i < 65 - productName.length(); i++) {
            _4 += " ";

        }
        return (_1 + _2 + _3 + "\n" + "\n" + _4 + _5);
    }

    /**
     * this method it used to apply HighLight Color to cell
     *
     * @param cell Cell cell object to apply color to
     * @param style CellStyle object just init new CellStyle
     * @param color IndexedColors object select color from saved colors
     */
    private void applyColorToCell(Cell cell, CellStyle style, IndexedColors color) {
        style.cloneStyleFrom(cell.getCellStyle());
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    /**
     * this method it used to creating a permit with 120 unit space
     *
     * @param bags list< Bag > Bags of order
     * @param ClientName String that contain Client name
     * @param product Product object used in order
     * @param excelBackupPath String the shows the path for backup location
     * @param highLight Boolean to check is highlight needed or not
     *
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.DatabaseException
     * @throws exceptions.BusinessException
     */
    public boolean excel_120(List<Bag> bags, String ClientName, Product product,
            String excelBackupPath, boolean highLight) throws DatabaseException, BusinessException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\120.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            CellStyle style1 = workbook.createCellStyle();
            CellStyle style2 = workbook.createCellStyle();

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, product.getName(), bags.getFirst().getLot()));

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

            int col = 1, row = 2;
            double TotalWeight = 0.0;

            for (Bag bag : bags) {
                cell = sheet.getRow(row).getCell(col);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style1, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((bag.getWeight() - (int) bag.getWeight()) * 1000);
                cell = sheet.getRow(row).getCell(col + 1);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style2, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((int) bag.getWeight());
                TotalWeight += bag.getWeight();
                row++;
                if (row >= 22) {
                    col += 3;
                    row = 2;
                }
            }

            cell = sheet.getRow(23).getCell(11);
            cell.setCellValue((product.isBox() ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + utils.toArabicDigits(bags.size() + "")
                    + (product.isBox() ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــوزن :       " + utils.ToDoubleArabic(TotalWeight) + "");

            create_excel_in_path(ClientName, workbook, excelBackupPath);
            return true;

        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * this method it used to creating a permit with 120 unit space
     *
     * @param bags list< Bag > Bags of order
     * @param ClientName String that contain Client name
     * @param product Product object used in order
     * @param excelBackupPath String the shows the path for backup location
     * @param highLight Boolean to check is highlight needed or not
     *
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.DatabaseException
     * @throws exceptions.BusinessException
     */
    public boolean excel_160(List<Bag> bags, String ClientName, Product product,
            String excelBackupPath, boolean highLight) throws DatabaseException, BusinessException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\160.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            CellStyle style1 = workbook.createCellStyle();
            CellStyle style2 = workbook.createCellStyle();

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, product.getName(), bags.getFirst().getLot()));

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

            int col = 1, row = 2;
            double TotalWeight = 0.0;
            for (Bag bag : bags) {
                cell = sheet.getRow(row).getCell(col);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style1, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((bag.getWeight() - (int) bag.getWeight()) * 1000);
                cell = sheet.getRow(row).getCell(col + 1);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style2, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((int) bag.getWeight());
                TotalWeight += bag.getWeight();
                row++;
                if (row >= 22) {
                    col += 3;
                    row = 2;
                }
            }
            cell = sheet.getRow(23).getCell(16);
            cell.setCellValue((product.isBox() ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + utils.toArabicDigits(bags.size() + "")
                    + (product.isBox() ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــوزن :       " + utils.ToDoubleArabic(TotalWeight) + "");

            create_excel_in_path(ClientName, workbook, excelBackupPath);

            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * this method it used to creating a permit with 120 unit space
     *
     * @param bags list< Bag > Bags of order
     * @param ClientName String that contain Client name
     * @param product Product object used in order
     * @param excelBackupPath String the shows the path for backup location
     * @param highLight Boolean to check is highlight needed or not
     *
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.DatabaseException
     * @throws exceptions.BusinessException
     */
    public boolean excel_200(List<Bag> bags, String ClientName, Product product,
            String excelBackupPath, boolean highLight) throws DatabaseException, BusinessException {
        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\200.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            CellStyle style1 = workbook.createCellStyle();
            CellStyle style2 = workbook.createCellStyle();

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(contactForHeader(ClientName, product.getName(), bags.getFirst().getLot()));

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

            int col = 1, row = 2;
            double TotalWeight = 0.0;
            for (Bag bag : bags) {
                cell = sheet.getRow(row).getCell(col);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style1, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((bag.getWeight() - (int) bag.getWeight()) * 1000);
                cell = sheet.getRow(row).getCell(col + 1);
                if (highLight && bag.isUsed()) {
                    applyColorToCell(cell, style2, IndexedColors.GREY_25_PERCENT);
                }
                cell.setCellValue((int) bag.getWeight());
                TotalWeight += bag.getWeight();
                row++;
                if (row >= 22) {
                    col += 3;
                    row = 2;
                }
            }
            cell = sheet.getRow(23).getCell(21);
            cell.setCellValue((product.isBox() ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                    + utils.toArabicDigits(bags.size() + "")
                    + (product.isBox() ? " صندوق" : "  شيكاره") + "\n" + "الــــــــــــــــــــــوزن :       " + utils.ToDoubleArabic(TotalWeight) + "");

            create_excel_in_path(ClientName, workbook, excelBackupPath);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }

    }

    /**
     * this method it used to creating a permit with 120 unit space
     *
     * @param fOrderBags list< Bag > Bags of First order
     * @param sOrderBags list< Bag > Bags of Second order
     * @param ClientName String that contain Client name
     * @param fOrderProduct Product object used in First order
     * @param sOrderProduct Product object used in Second order
     * @param excelBackupPath String the shows the path for backup location
     * @param fHighLight Boolean to check is highlight for first permit needed
     * or not
     * @param sHighLight Boolean to check is highlight for second permit needed
     * or not
     *
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.DatabaseException
     * @throws exceptions.BusinessException
     */
    public boolean excel_60_60(List<Bag> fOrderBags, List<Bag> sOrderBags,
            String ClientName, Product fOrderProduct, Product sOrderProduct,
            String excelBackupPath, boolean fHighLight, boolean sHighLight
    ) throws DatabaseException, BusinessException {

        try (FileInputStream file = new FileInputStream(new File("Donot_Change\\60-60.xlsx"))) {
            date_now = LocalDate.now();
            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                CellStyle style1 = workbook.createCellStyle();
                CellStyle style2 = workbook.createCellStyle();

                Cell cell;
                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + fOrderProduct.getName() + "";
                String _5 = "رقم اللـــوط :" + fOrderBags.getFirst().getLot();
                String _6 = "صنف :" + sOrderProduct.getName() + "";
                String _7 = "رقم اللـــوط :" + sOrderBags.getFirst().getLot();
                for (int i = 0; i < 66 - ClientName.length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 60 - sOrderProduct.getName().length(); i++) {
                    _4 += " ";

                }
                for (int i = 0; i < 65 - fOrderProduct.getName().length(); i++) {
                    _5 += " ";

                }

                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + _4 + _6 + "\n" + _5 + _7);

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

                int col = 1, row = 2;
                double fTotalWeight = 0.0;
                for (Bag bag : fOrderBags) {
                    cell = sheet.getRow(row).getCell(col);
                    if (fHighLight && bag.isUsed()) {
                        applyColorToCell(cell, style1, IndexedColors.GREY_25_PERCENT);
                    }
                    cell.setCellValue((bag.getWeight() - (int) bag.getWeight()) * 1000);
                    cell = sheet.getRow(row).getCell(col + 1);
                    if (fHighLight && bag.isUsed()) {
                        applyColorToCell(cell, style2, IndexedColors.GREY_25_PERCENT);
                    }
                    cell.setCellValue((int) bag.getWeight());
                    fTotalWeight += bag.getWeight();
                    row++;
                    if (row >= 22) {
                        col += 3;
                        row = 2;
                    }
                }

                col = 10;
                row = 2;
                double sTotalWeight = 0.0;
                for (Bag bag : sOrderBags) {
                    cell = sheet.getRow(row).getCell(col);
                    if (sHighLight && bag.isUsed()) {
                        applyColorToCell(cell, style1, IndexedColors.GREY_25_PERCENT);
                    }
                    cell.setCellValue((bag.getWeight() - (int) bag.getWeight()) * 1000);
                    cell = sheet.getRow(row).getCell(col + 1);
                    if (sHighLight && bag.isUsed()) {
                        applyColorToCell(cell, style2, IndexedColors.GREY_25_PERCENT);
                    }
                    cell.setCellValue((int) bag.getWeight());
                    sTotalWeight += bag.getWeight();
                    row++;
                    if (row >= 22) {
                        col += 3;
                        row = 2;
                    }
                }
                String __1 = (fOrderProduct.isBox() ? "عدد الصناديق : " : "عدد الشكاير : ")
                        + utils.toArabicDigits(fOrderBags.size() + "")
                        + (fOrderProduct.isBox() ? " صندوق" : "  شيكاره");
                String __2 = "الــوزن :  " + utils.ToDoubleArabic(fTotalWeight) + "";
                String __3 = (sOrderProduct.isBox() ? "عدد الصناديق : " : "عدد الشكاير : ")
                        + utils.toArabicDigits(sOrderBags.size() + "")
                        + (sOrderProduct.isBox() ? " صندوق" : "  شيكاره");
                String __4 = "الــوزن :  " + utils.ToDoubleArabic(sTotalWeight) + "";
                cell = sheet.getRow(23).getCell(11);
                cell.setCellValue(__1 + "  " + __2 + "\n" + __3 + " " + __4);
                create_excel_in_path(ClientName, workbook, excelBackupPath);

            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }

    }

    /**
     * this method it used to creating a Excel with stock
     *
     * @param stock list< string[] > result of query from SQL
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.BusinessException
     */
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

                CellStyle baseStyle = sheet.getRow(RowIndex).getCell(2).getCellStyle();
                CellStyle highLightStyle = workbook.createCellStyle();

                for (String[] row : stock) {

                    if (RowIndex >= 6) {
                        CellCopyPolicy poli = new CellCopyPolicy();
                        //poli.setCopyCellStyle(true);
                        //poli.setCopyCellValue(true);
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
                    if (row[4].equals("true")) {
                        applyColorToCell(cell, highLightStyle, IndexedColors.LIGHT_GREEN);
                    } else {
                        cell.setCellStyle(baseStyle);
                    }
                    cell.setCellValue(utils.toArabicDigits(row[1]));

                    cell = sheet.getRow(RowIndex).getCell(3);
                    if (row[4].equals("true")) {
                        applyColorToCell(cell, highLightStyle, IndexedColors.LIGHT_GREEN);
                    } else {
                        cell.setCellStyle(baseStyle);
                    }
                    cell.setCellValue(utils.toArabicDigits(row[2]));

                    cell = sheet.getRow(RowIndex).getCell(4);
                    if (row[4].equals("true")) {
                        applyColorToCell(cell, highLightStyle, IndexedColors.LIGHT_GREEN);
                    } else {
                        cell.setCellStyle(baseStyle);
                    }
                    cell.setCellValue(utils.toArabicDigits(row[3]));

//                    cell = sheet.getRow(RowIndex).getCell(5);
//                    cell.setCellValue(utils.toArabicDigits(row[4]));
                    RowIndex++;
                }
                if (reg.size() > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                }
                reg.clear();
            }
            try (FileOutputStream fileOut = new FileOutputStream(
                    System.getProperty("user.dir") + "\\Temp\\Stock " + date_now + ".xlsx")) {
                workbook.write(fileOut);
            }
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\Temp\\"));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * this method it used to creating a Excel with stock
     *
     * @param statstic list< string[] > result of query from SQL
     * @param date1 starting date
     * @param date2 ending date
     * @return Boolean that indicates whether the file created or not
     * @throws exceptions.BusinessException
     */
    public boolean staticsticsExcel(List<String[]> statstic, String date1, String date2) throws BusinessException {
        try {
            int RowIndex = 4;
            XSSFWorkbook workbook;
            try (FileInputStream EX = new FileInputStream(new File("Donot_Change\\Stock.xlsx"))) {
                workbook = new XSSFWorkbook(EX);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = sheet.getRow(1).getCell(1);
                cell.setCellValue(
                        "التاريـــخ من :   " + utils.toArabicDigits(date1) + "  إلى : " + utils.toArabicDigits(date2));
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
                            cell.setCellValue(utils.ToDoubleArabic(prevTot));
                        }
                        reg.clear();
                        reg.add(RowIndex);
                        prevName = row[0];

                        cell = sheet.getRow(RowIndex).getCell(1);
                        cell.setCellValue(row[0]);

                        prevTot = Double.parseDouble(row[3]);
                    }

                    cell = sheet.getRow(RowIndex).getCell(2);
                    cell.setCellValue(utils.toArabicDigits(row[1]));

                    cell = sheet.getRow(RowIndex).getCell(3);
                    cell.setCellValue(utils.toArabicDigits(row[2]));

                    cell = sheet.getRow(RowIndex).getCell(4);
                    cell.setCellValue(utils.toArabicDigits(row[3]));

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

    /**
     * this method it used to creating a permits and it setts formulas to the
     * last cell in column to calculate sum of the cells above
     *
     * @param cell Cell object
     * @param sheet XSSFSheet object
     */
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

    /**
     * this method it used to create excel in path with client and date
     *
     * @param ClientName String with client name
     * @param workbook XSSFWorkbook object
     * @param excelBackupPath String with backup path
     *
     * @throws exceptions.DatabaseException
     * @throws exceptions.BusinessException
     */
    private void create_excel_in_path(String ClientName, XSSFWorkbook workbook, String excelBackupPath) throws DatabaseException, BusinessException {
        try {
            date_now = LocalDate.now();

            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\querys"));
            File file = utils.NewName(System.getProperty("user.dir") + "\\querys\\" + ClientName + "~" + date_now + ".xlsx");
            try (FileOutputStream outFile = new FileOutputStream(file)) {
                try (workbook) {
                    workbook.write(outFile);
                    Desktop desktop = Desktop.getDesktop();

                    if (!excelBackupPath.isBlank()) {
                        File file1 = utils.NewName(excelBackupPath + "\\" + ClientName + "~" + date_now + ".xlsx");
                        try (FileOutputStream outFile1 = new FileOutputStream(file1)) {
                            workbook.write(outFile1);
                        }
                    }

                    desktop.open(file);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * this method it used in creating ticket excel if used
     *
     * @param values the values to create excel with
     * @throws exceptions.BusinessException
     */
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
