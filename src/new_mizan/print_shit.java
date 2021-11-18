/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author XifiremanX
 */
public class print_shit {

    void excel_120(int serial, List<String> order_ids, JTextField total_weight, JTextField jTextField6, JComboBox jComboBox_pro_in_reports, JTable jTable3, JTextField jTextField5, sqlcon opj, JFileChooser jFileChooser1) {

        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("120.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = null;

                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + jTextField6.getText() + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + jComboBox_pro_in_reports.getSelectedItem() + "";
                String _5 = "رقم اللـــوط :" + mainform.ToDoubleArabic(mainform.ToStringEnglish(jTable3.getValueAt(0, 2) + ""));

                for (int i = 0; i < 70 - jTextField6.getText().length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 65 - jComboBox_pro_in_reports.getSelectedItem().toString().length(); i++) {
                    _4 += " ";

                }
                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + "\n" + _4 + _5);

                String __1 = "عدد الشكاير :          " + mainform.ToDoubleArabic((int) mainform.ToDoubleEnglish(jTextField5.getText()) + "") + "  شيكاره";
                String __2 = "الــــــــــــــــــــــــــــــــوزن :       " + mainform.ToDoubleArabic(total_weight.getText()) + "";
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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= mainform.ToDoubleEnglish(jTextField5.getText()); i++) {
                        if (jTable3.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()) - (int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

            }
            create_excel_in_path(serial, order_ids, total_weight, jTable3, jTextField6, date_now, workbook, opj, jFileChooser1, jComboBox_pro_in_reports);
            jTextField5.setText("");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void excel_160(int serial, List<String> order_ids, JTextField total_weight, JTextField jTextField6, JComboBox jComboBox_pro_in_reports, JTable jTable3, JTextField jTextField5, sqlcon opj, JFileChooser jFileChooser1) {
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("160.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = null;

                String _1 = "                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + jTextField6.getText() + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + jComboBox_pro_in_reports.getSelectedItem() + "";
                String _5 = "رقم اللـــوط :" + mainform.ToDoubleArabic(mainform.ToStringEnglish(jTable3.getValueAt(0, 2) + ""));

                for (int i = 0; i < 70 - jTextField6.getText().length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 65 - jComboBox_pro_in_reports.getSelectedItem().toString().length(); i++) {
                    _4 += " ";

                }
                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + "\n" + _4 + _5);

                String __1 = "عدد الشكاير :           " + mainform.ToDoubleArabic((int) mainform.ToDoubleEnglish(jTextField5.getText()) + "") + "  شيكاره";
                String __2 = "الــــــــــــــــــــــــــــــــوزن :       " + mainform.ToDoubleArabic(total_weight.getText()) + "";
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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= mainform.ToDoubleEnglish(jTextField5.getText()); i++) {
                        if (jTable3.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()) - (int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

            }
            create_excel_in_path(serial, order_ids, total_weight, jTable3, jTextField6, date_now, workbook, opj, jFileChooser1, jComboBox_pro_in_reports);
            jTextField5.setText("");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void excel_60_60(int serial, List<String> order_ids, List<String> ne, JTextField total_weight, JTextField jTextField6, JComboBox jComboBox_pro_in_reports, JTable jTable3, JTable first_table, JTextField jTextField5, String num_of_shikra, String wieght, String name_of_type, sqlcon opj, JFileChooser jFileChooser1) {
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("60-60.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = null;

                String _1 = "                                                  إذن تـسليم بضاعة\n";
                String _2 = "السيد :" + jTextField6.getText() + "";
                String _3 = "التاريـــخ :" + date_now.format(arabicDateFormatter) + "";
                String _4 = "صنف :" + name_of_type + "";
                String _5 = "رقم اللـــوط :" + mainform.ToDoubleArabic(mainform.ToStringEnglish(first_table.getValueAt(0, 2) + ""));
                String _6 = "صنف :" + jComboBox_pro_in_reports.getSelectedItem() + "";
                String _7 = "رقم اللـــوط :" + mainform.ToDoubleArabic(mainform.ToStringEnglish(jTable3.getValueAt(0, 2) + ""));

                for (int i = 0; i < 66 - jTextField6.getText().length(); i++) {
                    _2 += " ";
                }
                for (int i = 0; i < 60 - jComboBox_pro_in_reports.getSelectedItem().toString().length(); i++) {
                    _4 += " ";

                }
                for (int i = 0; i < 65 - name_of_type.length(); i++) {
                    _5 += " ";

                }

                cell = sheet.getRow(0).getCell(0);
                cell.setCellValue(_1 + _2 + _3 + "\n" + _4 + _6 + "\n" + _5 + _7);

                String __1 = "عدد الشكاير : " + mainform.ToDoubleArabic((int) mainform.ToDoubleEnglish(num_of_shikra) + "") + " شيكاره";
                String __2 = "الـــــــوزن :  " + mainform.ToDoubleArabic(wieght) + "";
                String __3 = "عدد الشكاير : " + mainform.ToDoubleArabic((int) mainform.ToDoubleEnglish(jTextField5.getText()) + "") + " شيكاره";
                String __4 = "الـــــــوزن :  " + mainform.ToDoubleArabic(total_weight.getText()) + "";
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

                    for (int i = 2 + t; i <= 21 + t && i - 1 <= mainform.ToDoubleEnglish(num_of_shikra); i++) {
                        if (first_table.getRowCount() == i - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * mainform.ToDoubleEnglish(first_table.getValueAt(i - 2, 1).toString()) - (int) mainform.ToDoubleEnglish(first_table.getValueAt(i - 2, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) mainform.ToDoubleEnglish(first_table.getValueAt(i - 2, 1).toString()));
                    }
                    f += 3;
                }

                f = 10;
                for (int t = 60; t < 121; t += 20) {

                    for (int i = 2 + t; i <= 21 + t && i - 60 - 1 <= mainform.ToDoubleEnglish(jTextField5.getText()); i++) {
                        if (jTable3.getRowCount() == i + 60 - 2) {
                            break;
                        }
                        cell = sheet.getRow(i - t).getCell(f);
                        cell.setCellValue(((1000 * mainform.ToDoubleEnglish(jTable3.getValueAt(i - 62, 1).toString()) - (int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 62, 1).toString()) * 1000)));
                        cell = sheet.getRow(i - t).getCell(f + 1);
                        cell.setCellValue((int) mainform.ToDoubleEnglish(jTable3.getValueAt(i - 62, 1).toString()));
                    }
                    f += 3;
                }

            }

            File file = this.NewName(System.getProperty("user.dir") + "\\querys\\" + jTextField6.getText() + "~" + date_now + ".xlsx");
            try (FileOutputStream outFile = new FileOutputStream(file)) {
                workbook.write(outFile);
                Desktop desktop = Desktop.getDesktop();
                jFileChooser1.showSaveDialog(jTable3);
                if (jFileChooser1.getSelectedFile() != null) {
                    File file1 = this.NewName(jFileChooser1.getSelectedFile().getAbsolutePath() + "\\" + jTextField6.getText() + "~" + date_now + ".xlsx");
                    FileOutputStream outFile1 = new FileOutputStream(file1);
                    workbook.write(outFile1);
                }
                desktop.open(file);
                serial = 0;

                if (!opj.dataRead("*", "clients", "cli_name=N'" + jTextField6.getText() + "'").next()) {
                    opj.inData("clients", "cli_name", "N'" + jTextField6.getText() + "'");
                }
                order_ids.forEach(order_id -> {
                    opj.inData("export", "pro_id,cli_id,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used",
                            "(select pro_id from products where pro_name=N'" + jComboBox_pro_in_reports.getSelectedItem() + "')"
                            + ",(select top(1) cli_id from clients where cli_name=N'" + jTextField6.getText() + "')"
                            + ",(select weight_ from storage where storage_id=" + order_id + ")"
                            + ",(select lot from storage where storage_id= " + order_id + " )"
                            + ",(select date_ from storage where storage_id= " + order_id + " )"
                            + ",GETDATE()"
                            + ",(select num_of_con from storage where storage_id= " + order_id + " ) "
                            + ",(select pallet_numb from storage where storage_id= " + order_id + " )"
                            + ",(select used from storage where storage_id= " + order_id + " )");

                    opj.delData("storage", "storage_id=" + order_id + "");
                });
                ne.forEach(nes -> {
                    opj.inData("export", "pro_id,cli_id,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used",
                            "(select pro_id from products where pro_name=N'" + name_of_type + "')"
                            + ",(select top(1) cli_id from clients where cli_name=N'" + jTextField6.getText() + "')"
                            + ",(select weight_ from storage where storage_id=" + nes + ")"
                            + ",(select lot from storage where storage_id= " + nes + " )"
                            + ",(select date_ from storage where storage_id= " + nes + " )"
                            + ",GETDATE()  "
                            + ",(select num_of_con from storage where storage_id= " + nes + " ) "
                            + ",(select pallet_numb from storage where storage_id= " + nes + " )"
                            + ",(select used from storage where storage_id= " + nes + " )");
                    opj.delData("storage", "storage_id=" + nes + "");
                });
                ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
                ((DefaultTableModel) first_table.getModel()).setRowCount(0);
                ne.clear();
                order_ids.clear();
                total_weight.setText("");
                jTextField6.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
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

    void create_excel_in_path(int serial, List<String> order_ids, JTextField total_weight, JTable jTable3, JTextField jTextField6, LocalDate date_now, XSSFWorkbook workbook, sqlcon opj, JFileChooser jFileChooser1, JComboBox combox_product) throws FileNotFoundException, IOException {
        File file = this.NewName(System.getProperty("user.dir") + "\\querys\\" + jTextField6.getText() + "~" + date_now + ".xlsx");
        try (FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
            Desktop desktop = Desktop.getDesktop();
            jFileChooser1.showSaveDialog(jTable3);
            if (jFileChooser1.getSelectedFile() != null) {
                File file1 = this.NewName(jFileChooser1.getSelectedFile().getAbsolutePath() + "\\" + jTextField6.getText() + "~" + date_now + ".xlsx");
                FileOutputStream outFile1 = new FileOutputStream(file1);
                workbook.write(outFile1);
            }

            desktop.open(file);
            serial = 0;
            if (!opj.dataRead("*", "clients", "cli_name=N'" + jTextField6.getText() + "'").next()) {
                opj.inData("clients", "cli_name", "N'" + jTextField6.getText() + "'");
            }

            order_ids.forEach(order_id -> {

                opj.inData("export", "pro_id,cli_id,weight_,lot,inserted_date,exported_date,num_of_con,pallet_numb,used",
                        "(select pro_id from products where pro_name=N'" + combox_product.getSelectedItem() + "')"
                        + ",(select  top(1) cli_id from clients where cli_name=N'" + jTextField6.getText() + "')"
                        + ",(select weight_ from storage where storage_id=" + order_id + ")"
                        + ",(select lot from storage where storage_id= " + order_id + " )"
                        + ",(select date_ from storage where storage_id= " + order_id + " )"
                        + ",GETDATE() "
                        + ",(select num_of_con from storage where storage_id= " + order_id + " )"
                        + ",(select pallet_numb from storage where storage_id= " + order_id + " )"
                        + ",(select used from storage where storage_id= " + order_id + " )  ");

                opj.delData("storage", "storage_id=" + order_id + "");
            });
            ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
            order_ids.clear();
            total_weight.setText("");
            jTextField6.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(print_shit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    File NewName(String fileName) {
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
        String path = System.getProperty("user.dir") + "\\Temp\\QR"+imgName+".png";
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, SizeX, SizeY, hintMap);
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path).toPath());
    }
}
