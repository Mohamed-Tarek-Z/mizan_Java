package utils;

import exceptions.BusinessException;
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
            int printerDPI = 203;
            int width = (int) (panel.getWidth() * printerDPI / 72.0);
            int height = (int) (panel.getHeight() * printerDPI / 72.0);
            double scaleX = width / (double) panel.getWidth();
            double scaleY = height / (double) panel.getHeight();
            double scale = Math.min(scaleX, scaleY);

            BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2d = panelImage.createGraphics();
            g2d.scale(scale, scale);
            panel.printAll(g2d);
            g2d.dispose();
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
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.drawImage(image, 0, 0, 760, 486, null); // these number after many testing with 10 x 10 paper Size
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
