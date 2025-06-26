package utils;

import exceptions.BusinessException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PrinterManager {

    private final ErrorListener errorListener;

    public PrinterManager(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    /**
     * enumerate all printers queues installed on computer and search for
     * printerName that is being passed
     *
     * @param printerName name of printer queue to search for
     * @return PrintService if found
     */
    private PrintService getPrinterByName(String printerName) {
        return Arrays.stream(PrintServiceLookup.lookupPrintServices(null, null))
                .filter(p -> p.getName().contains(printerName))
                .findFirst()
                .orElse(null);
    }

    /**
     * takes the data buffer and send it to printer
     *
     * @param data buffer contain QR image data to send to printer queue printer
     * can be changed from Configuration file
     * @throws exceptions.BusinessException
     */
    private void QRPrint(byte[] data) throws BusinessException {
        try {
            PrintService xPrinter = getPrinterByName(new utils().CheckConfigFileAndFolder().getProperty("qrPrinterName", "Xprinter"));
            if (xPrinter == null) {
                throw new BusinessException("Xprinter not found!");
            }
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

            pras.add(new MediaPrintableArea(0, 3, 18, 18, MediaPrintableArea.MM));
            DocPrintJob job = xPrinter.createPrintJob();
            Doc doc = new SimpleDoc(data, flavor, null);
            job.print(doc, pras);
        } catch (PrintException ex) {
            throw new BusinessException("error in QRPrinting");
        }
    }

    /* 
    public void printPanelToImage(JPanel panel) throws BusinessException {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService HWPrinter = getPrinterByName(new utils().CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Honeywell"));
            if (HWPrinter == null) {
                throw new BusinessException("HWPrinter not found!");
            }
            job.setPrintService(HWPrinter);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new MediaPrintableArea(0, 0, 100, 100, MediaPrintableArea.MM));

            job.setJobName("Print Ticket");
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                // Convert JPanel to Image
                int dpi = 300; // High DPI for sharp quality
                double scaleFactor = dpi / 72.0; // Convert from points to pixels

                // Scale up panel size for high-resolution rendering
                int width = (int) (panel.getWidth() * scaleFactor);
                int height = (int) (panel.getHeight() * scaleFactor);

                // Create a high-resolution BufferedImage
                BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB
                );
                Graphics2D g2dImage = panelImage.createGraphics();

                // Apply high-quality rendering settings
                g2dImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2dImage.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g2dImage.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2dImage.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2dImage.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

                // Scale Graphics2D to high resolution
                g2dImage.scale(scaleFactor, scaleFactor);

                // Paint the panel onto the BufferedImage
                panel.paint(g2dImage);
                g2dImage.dispose();

                // Save image for debugging (optional)
                //ImageIO.write(panelImage, "PNG", new File(System.getProperty("user.dir") + "\\Temp\\debug_print.png"));
                // Print Image
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                //                int dpiForPrint = 203;
                //                double pixelsPerCm = dpiForPrint / 2.54;
                int widthPrint = (int) 760;//(10 * pixelsPerCm);
                int heightPrint = (int) 486;//(10 * pixelsPerCm);

                g2d.drawImage(panelImage, 0, 1, widthPrint, heightPrint, Color.WHITE, null);

                return Printable.PAGE_EXISTS;
            });
            job.print(attributes);

        } catch (PrinterException e) {
            throw new BusinessException("error in panel Printing");
        }
    } 
     */
    /**
     * print the ticket from printer panel
     *
     * @param panel swing panel to be printed printer can be changed from
     * Configuration file
     * @param troll
     */
    public void printPanelToImage(JPanel panel, boolean troll) {
        SwingUtilities.invokeLater(() -> {
            int dpi = 300; // High DPI for sharp quality
            double scaleFactor = dpi / 72.0; // Convert from points to pixels

            // Scale up panel size for high-resolution rendering
            int width = (int) (panel.getWidth() * scaleFactor);
            int height = (int) (panel.getHeight() * scaleFactor);

            // Create a high-resolution BufferedImage
            BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2dImage = panelImage.createGraphics();

            // Apply high-quality rendering settings
            g2dImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2dImage.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2dImage.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2dImage.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2dImage.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            // Scale Graphics2D to high resolution
            g2dImage.scale(scaleFactor, scaleFactor);

            // Paint the panel onto the BufferedImage
            panel.paint(g2dImage);
            //panel.printAll(g2dImage);

            g2dImage.dispose();
            // Save image for debugging (optional) 
            //ImageIO.write(panelImage, "PNG", new File(System.getProperty("user.dir") + "\\Temp\\debug_print.png"));
            new Thread(() -> {
                try {
                    if (troll) {
                        Thread.sleep(((int) (Math.random() * 6) + 1) * 1000);
                    }
                    printImage(panelImage);
                } catch (BusinessException ex) {
                    Logger.getLogger(PrinterManager.class.getName()).log(Level.SEVERE, null, ex);
                    errorListener.onError(ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrinterManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        });
    }

    private void printImage(BufferedImage image) throws BusinessException {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService HWPrinter = getPrinterByName(new utils().CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Honeywell"));
            if (HWPrinter == null) {
                throw new BusinessException("HWPrinter not found!");
            }
            job.setPrintService(HWPrinter);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new MediaPrintableArea(0, 0, 100, 100, MediaPrintableArea.MM));

            job.setJobName("Print Ticket");
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                //                int dpiForPrint = 203;
                //                double pixelsPerCm = dpiForPrint / 2.54;
                int widthPrint = (int) 760;//(10 * pixelsPerCm);
                int heightPrint = (int) 486;//(10 * pixelsPerCm);

                g2d.drawImage(image, 0, 1, widthPrint, heightPrint, Color.WHITE, null);

                return Printable.PAGE_EXISTS;
            });
            job.print(attributes);
        } catch (PrinterException e) {
            throw new BusinessException("error in panel Printing");
        }
    }

    /**
     * print the ticket from excel file print panel should be faster because it
     * does not rely on other programs
     *
     * @throws exceptions.BusinessException
     */
    /**
     * print the ticket from excel file print panel should be faster because it
     * does not rely on other programs
     *
     * @throws exceptions.BusinessException
     */
    public void print_excel_ticket() throws BusinessException {
        try {
            Desktop.getDesktop().print(new File(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx"));
        } catch (IOException ex) {
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * print and creates the tickets from send to it
     *
     * @param values this is data to create tickets with
     * @param panel this is the swing panel to print
     * @param printTicket a Boolean to check if you want to print the big ticket
     * or not
     * @param printQr a Boolean to check if you want to print the QR ticket or
     * not
     * @param printingByExcelWay a Boolean to check if you want to by Excel or
     * panel
     * @param troll
     * @throws exceptions.BusinessException
     */
    public void printTickets(ArrayList<String> values, JPanel panel, boolean printTicket, boolean printQr, boolean printingByExcelWay, boolean troll) throws BusinessException {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\Temp"));
            String n = values.get(2);
            while (n.length() < 20) {
                n += " ";
            }
            if (printQr) {
                QRPrint(new utils().generateQRcode("{\n \"رقم البالتة\": " + values.get(0) + " ,\n  \"الصنف\": " + n + " ,\n\"اللوط\": " + values.get(3)
                        + " ,\n\"الوزن الصافي\": " + values.get(6) + "\n}", 400, 400));
            }
            if (printTicket) {
                if (!printingByExcelWay) {
                    printPanelToImage(panel, troll);
                } else {
                    new ExcelManager().excel_Ticket(values);
                    print_excel_ticket();
                }
            }
        } catch (IOException ex) {
            throw new BusinessException("ملف غير موجود");
        }
    }

}
