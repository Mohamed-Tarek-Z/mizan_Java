package utils;

import exceptions.BusinessException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class PrinterManager {

    private final ErrorListener errorListener;
    private PrintService TicketPrinter;

    public PrinterManager(ErrorListener errorListener) throws BusinessException {
        this.errorListener = errorListener;
        this.TicketPrinter = getPrinterByName(utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print"));
    }

    /**
     * reinitialize printers after any modification
     *
     * @throws exceptions.BusinessException
     */
    public void reInitPrinters() throws BusinessException {
        this.TicketPrinter = getPrinterByName(utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print"));
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
     * print the ticket from printer panel
     *
     * @param panel swing panel to be printed printer can be changed from
     * Configuration file
     */
    public void printPanelToImage(JPanel panel) {
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

                    printImage(panelImage);
                } catch (BusinessException ex) {
                    Logger.getLogger(PrinterManager.class.getName()).log(Level.SEVERE, null, ex);
                    errorListener.onError(ex);
                }
            }).start();
        });
    }

    private void printImage(BufferedImage image) throws BusinessException {
        try {
            if (TicketPrinter == null) {
                throw new BusinessException(utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print") + "Ticket printer not found!");
            }
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintService(TicketPrinter);
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
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new MediaPrintableArea(0, 0, 100, 100, MediaPrintableArea.MM));
            job.print(attributes);
        } catch (PrinterException e) {
            throw new BusinessException("error in panel Printing");
        }
    }

 

}
