package new_mizan;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static new_mizan.mainform.ToDoubleArabic;
import static new_mizan.mainform.ToDoubleEnglish;
import static new_mizan.mainform.ToStringEnglish;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author XifiremanX
 */
public class print_shit {

    boolean excel_120(int serial, List<String> orderIds, String totalWeight, String ClientName, String productName, JTable BagsTable, sqlcon opj, JFileChooser jFileChooser1, boolean isBoxes) {

        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("Donot_Change\\120.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;

                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + productName;
                String _5 = "رقم اللـــوط :" + ToDoubleArabic(ToStringEnglish(BagsTable.getValueAt(0, 2) + ""));

                for (int i = 0; i < 70 - ClientName.length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 65 - productName.length(); i++) {
                    _4 += " ";

                }
                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + "\n" + _4 + _5);
                String __1 = (isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                        + ToDoubleArabic(BagsTable.getRowCount() + "")
                        + (isBoxes ? " صندوق" : "  شيكاره");
                String __2 = "الــــــــــــــــــــــــــــــــوزن :       " + ToDoubleArabic(totalWeight) + "";
                cell = sheet.getRow(23).getCell(11);
                cell.setCellValue(__1 + "\n" + __2);

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
                    for (int i = 2 + t; i <= 21 + t && i - 1 <= BagsTable.getRowCount(); i++) {
                        if (BagsTable.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }
            }
            return create_excel_in_path(serial, orderIds, totalWeight, BagsTable, ClientName, date_now, workbook, opj, jFileChooser1, productName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean excel_160(int serial, List<String> orderIds, String totalWeight, String ClientName, String productName, JTable BagsTable, sqlcon opj, JFileChooser jFileChooser1, boolean isBoxes) {
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("Donot_Change\\160.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;

                String _1 = "                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + productName + "";
                String _5 = "رقم اللـــوط :" + ToDoubleArabic(ToStringEnglish(BagsTable.getValueAt(0, 2) + ""));

                for (int i = 0; i < 70 - ClientName.length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 65 - productName.length(); i++) {
                    _4 += " ";

                }
                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + "\n" + _4 + _5);
                String __1 = (isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                        + ToDoubleArabic(BagsTable.getRowCount() + "")
                        + (isBoxes ? " صندوق" : "  شيكاره");
                String __2 = "الــــــــــــــــــــــــــــــــوزن :       " + ToDoubleArabic(totalWeight) + "";
                cell = sheet.getRow(23).getCell(16);
                cell.setCellValue(__1 + "\n" + __2);

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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= BagsTable.getRowCount(); i++) {
                        if (BagsTable.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

            }
            return create_excel_in_path(serial, orderIds, totalWeight, BagsTable, ClientName, date_now, workbook, opj, jFileChooser1, productName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    boolean excel_200(int serial, List<String> orderIds, String totalWeight, String ClientName, String productName, JTable BagsTable, sqlcon opj, JFileChooser jFileChooser1, boolean isBoxes) {
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("Donot_Change\\200.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;

                String _1 = "                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + productName + "";
                String _5 = "رقم اللـــوط :" + ToDoubleArabic(ToStringEnglish(BagsTable.getValueAt(0, 2) + ""));

                for (int i = 0; i < 70 - ClientName.length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 65 - productName.length(); i++) {
                    _4 += " ";

                }
                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + "\n" + _4 + _5);
                String __1 = (isBoxes ? "عدد الصناديق :          " : "عدد الشكاير :          ")
                        + ToDoubleArabic(BagsTable.getRowCount() + "")
                        + (isBoxes ? " صندوق" : "  شيكاره");
                String __2 = "الــــــــــــــــــــــــــــــــوزن :       " + ToDoubleArabic(totalWeight) + "";
                cell = sheet.getRow(23).getCell(21);
                cell.setCellValue(__1 + "\n" + __2);

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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= BagsTable.getRowCount(); i++) {
                        if (BagsTable.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) - (int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) ToDoubleEnglish(BagsTable.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

            }
            return create_excel_in_path(serial, orderIds, totalWeight, BagsTable, ClientName, date_now, workbook, opj, jFileChooser1, productName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean excel_60_60(int serial, List<String> sOrderIds, List<String> fOrderIds,
            String wieghtSOrder, String ClientName, String typeSOrder,
            JTable tableSOrder, JTable tableFOrder, String bagCountFOrder, String wieghtFOrder, String typeFOrder, sqlcon opj,
            JFileChooser jFileChooser1, boolean FIsBoxes, boolean SIsBoxes) {
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("Donot_Change\\60-60.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;

                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + ClientName + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + typeFOrder + "";
                String _5 = "رقم اللـــوط :" + ToDoubleArabic(ToStringEnglish(tableFOrder.getValueAt(0, 2) + ""));
                String _6 = "صنف :" + typeSOrder + "";
                String _7 = "رقم اللـــوط :" + ToDoubleArabic(ToStringEnglish(tableSOrder.getValueAt(0, 2) + ""));

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
                        + ToDoubleArabic((int) ToDoubleEnglish(bagCountFOrder) + "")
                        + (FIsBoxes ? " صندوق" : "  شيكاره");
                String __2 = "الـــــــوزن :  " + ToDoubleArabic(wieghtFOrder) + "";
                String __3 = (SIsBoxes ? "عدد الصناديق : " : "عدد الشكاير : ")
                        + ToDoubleArabic(tableSOrder.getRowCount() + "")
                        + (SIsBoxes ? " صندوق" : "  شيكاره");
                String __4 = "الـــــــوزن :  " + ToDoubleArabic(wieghtSOrder) + "";
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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= ToDoubleEnglish(bagCountFOrder); i++) {
                        if (tableFOrder.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()) - (int) ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) ToDoubleEnglish(tableFOrder.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

                f = 10;
                for (int t = 60; t < 121; t += 20) {

                    for (int i = 2 + t; i <= 21 + t && i - 60 - 1 <= tableSOrder.getRowCount(); i++) {
                        if (tableSOrder.getRowCount() == i + 60 - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()) - (int) ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) ToDoubleEnglish(tableSOrder.getValueAt(i - 62, 1).toString()));
                    }
                    f += 3;
                }

            }
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\querys"));
            File file = print_shit.NewName(System.getProperty("user.dir") + "\\querys\\" + ClientName + "~" + date_now + ".xlsx");
            try (FileOutputStream outFile = new FileOutputStream(file)) {
                workbook.write(outFile);
                Desktop desktop = Desktop.getDesktop();
                jFileChooser1.showSaveDialog(tableSOrder);
                if (jFileChooser1.getSelectedFile() != null) {
                    File file1 = print_shit.NewName(jFileChooser1.getSelectedFile().getAbsolutePath() + "\\" + ClientName + "~" + date_now + ".xlsx");
                    FileOutputStream outFile1 = new FileOutputStream(file1);
                    workbook.write(outFile1);
                }
                desktop.open(file);
                serial = 0;
                String name = ClientName.split("تسليم")[0].strip();
                if (!opj.dataRead("*", "clients", "cli_name=N'" + name + "'").next()) {
                    opj.inData("clients", "cli_name", "N'" + name + "'");
                }
                opj.inData("orders", "ord_wight,ord_date", ToDoubleEnglish(wieghtSOrder) + ",GETDATE()");
                sOrderIds.forEach(order_id -> {
                    opj.inData("export", "pro_id,cli_id,tot_wight,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used,ord_id",
                            "(select pro_id from products where pro_name=N'" + typeSOrder + "')"
                            + ",(select top(1) cli_id from clients where cli_name=N'" + name + "')"
                            + ",(select tot_wight from storage where storage_id=" + order_id + ")"
                            + ",(select weight_ from storage where storage_id=" + order_id + ")"
                            + ",(select lot from storage where storage_id= " + order_id + " )"
                            + ",(select date_ from storage where storage_id= " + order_id + " )"
                            + ",GETDATE()"
                            + ",(select num_of_con from storage where storage_id= " + order_id + " ) "
                            + ",(select pallet_numb from storage where storage_id= " + order_id + " )"
                            + ",(select used from storage where storage_id= " + order_id + " )"
                            + ",(SELECT TOP 1 ord_id FROM orders where ord_wight=" + ToDoubleEnglish(wieghtSOrder) + " ORDER BY ord_id DESC)"
                    );

                    opj.delData("storage", "storage_id=" + order_id + "");
                });
                opj.inData("orders", "ord_wight,ord_date", ToDoubleEnglish(wieghtFOrder) + ",GETDATE()");
                fOrderIds.forEach(StoreID -> {
                    opj.inData("export", "pro_id,cli_id,tot_wight,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used,ord_id",
                            "(select pro_id from products where pro_name=N'" + typeFOrder + "')"
                            + ",(select top(1) cli_id from clients where cli_name=N'" + name + "')"
                            + ",(select tot_wight from storage where storage_id=" + StoreID + ")"
                            + ",(select weight_ from storage where storage_id=" + StoreID + ")"
                            + ",(select lot from storage where storage_id= " + StoreID + " )"
                            + ",(select date_ from storage where storage_id= " + StoreID + " )"
                            + ",GETDATE()  "
                            + ",(select num_of_con from storage where storage_id= " + StoreID + " ) "
                            + ",(select pallet_numb from storage where storage_id= " + StoreID + " )"
                            + ",(select used from storage where storage_id= " + StoreID + " )"
                            + ",(SELECT TOP 1 ord_id FROM orders where ord_wight=" + ToDoubleEnglish(wieghtFOrder) + " ORDER BY ord_id DESC)"
                    );
                    opj.delData("storage", "storage_id=" + StoreID + "");
                });
                ((DefaultTableModel) tableSOrder.getModel()).setRowCount(0);
                ((DefaultTableModel) tableFOrder.getModel()).setRowCount(0);
                fOrderIds.clear();
                sOrderIds.clear();

            } catch (SQLException ex) {
                Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
            }
            workbook.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    void cell_functions(Cell cell, XSSFSheet sheet) {
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

    boolean create_excel_in_path(int serial, List<String> orderIds, String totalWeight, JTable BagsTable, String ClientName, LocalDate date_now, XSSFWorkbook workbook, sqlcon opj, JFileChooser jFileChooser1, String productName) throws FileNotFoundException, IOException {
        String name = ClientName.split("تسليم")[0].strip();
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\querys"));
        File file = print_shit.NewName(System.getProperty("user.dir") + "\\querys\\" + ClientName + "~" + date_now + ".xlsx");
        try (FileOutputStream outFile = new FileOutputStream(file)) {
            try (workbook) {
                workbook.write(outFile);
                Desktop desktop = Desktop.getDesktop();
                jFileChooser1.showSaveDialog(BagsTable);
                if (jFileChooser1.getSelectedFile() != null) {
                    File file1 = print_shit.NewName(jFileChooser1.getSelectedFile().getAbsolutePath() + "\\" + ClientName + "~" + date_now + ".xlsx");
                    FileOutputStream outFile1 = new FileOutputStream(file1);
                    workbook.write(outFile1);
                }

                desktop.open(file);
                serial = 0;
                if (!opj.dataRead("*", "clients", "cli_name=N'" + name + "'").next()) {
                    opj.inData("clients", "cli_name", "N'" + name + "'");
                }
                opj.inData("orders", "ord_wight,ord_date", ToDoubleEnglish(totalWeight) + ",GETDATE()");
                orderIds.forEach(order_id -> {

                    opj.inData("export", "pro_id,cli_id,tot_wight,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used,ord_id",
                            "(select pro_id from products where pro_name=N'" + productName + "')"
                            + ",(select  top(1) cli_id from clients where cli_name=N'" + name + "')"
                            + ",(select tot_wight from storage where storage_id=" + order_id + ")"
                            + ",(select weight_ from storage where storage_id=" + order_id + ")"
                            + ",(select lot from storage where storage_id= " + order_id + " )"
                            + ",(select date_ from storage where storage_id= " + order_id + " )"
                            + ",GETDATE() "
                            + ",(select num_of_con from storage where storage_id= " + order_id + " )"
                            + ",(select pallet_numb from storage where storage_id= " + order_id + " )"
                            + ",(select used from storage where storage_id= " + order_id + " )"
                            + ",(SELECT TOP 1 ord_id FROM orders where ord_wight=" + ToDoubleEnglish(totalWeight) + " ORDER BY ord_id DESC)"
                    );

                    opj.delData("storage", "storage_id=" + order_id + "");
                });
                ((DefaultTableModel) BagsTable.getModel()).setRowCount(0);
                orderIds.clear();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static File NewName(String fileName) {
        int idxOfDot = fileName.lastIndexOf('.');
        String extension = fileName.substring(idxOfDot + 1);
        String fullname = fileName.substring(0, idxOfDot);
        String[] Splt = fullname.split("~");
        Path path = Paths.get(fileName);
        for (int i = 1; Files.exists(path); i++) {
            fileName = Splt[0] + " (" + i + ") " + Splt[1] + "." + extension;
            path = Paths.get(fileName);
        }
        return new File(fileName);
    }

    public static void generateQRcode(String data, String imgName, int SizeX, int SizeY) throws WriterException, IOException {
        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN, 0);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        String path = System.getProperty("user.dir") + "\\Temp\\QR" + imgName + ".png";
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, SizeX, SizeY, hintMap);
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path).toPath());
    }
}
