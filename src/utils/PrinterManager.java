package utils;

import exceptions.BusinessException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PrinterManager {

    private final ErrorListener errorListener;
    private PrintService TicketPrinter;

    public PrinterManager(ErrorListener errorListener) throws BusinessException {
        this.errorListener = errorListener;
        this.TicketPrinter = getPrinterByName(
                utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print"));
    }

    /**
     * reinitialize printers after any modification
     *
     * @throws exceptions.BusinessException
     */
    public void reInitPrinters() throws BusinessException {
        this.TicketPrinter = getPrinterByName(
                utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print"));
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
            try {
                panel.setSize(300, 320);
                if (TicketPrinter == null) {
                    throw new BusinessException(
                            utils.CheckConfigFileAndFolder().getProperty("ticketPrinterName", "Microsoft Print")
                            + "Ticket printer not found!");
                }
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintService(TicketPrinter);
                Paper paper = new Paper();
                PageFormat pageFormat = printerJob.defaultPage();

                paper.setSize(pageFormat.getWidth(), pageFormat.getHeight());
                paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());

                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                // Let the printer adjust the PageFormat if necessary
                pageFormat = printerJob.validatePage(pageFormat);

                printerJob.setPrintable((Graphics graphics, PageFormat pf, int pageIndex) -> {
                    if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }
                    Graphics2D g2 = (Graphics2D) graphics.create();
                    try {
                        
                        double scaleX = pf.getImageableWidth() / panel.getWidth();
                        double scaleY = pf.getImageableHeight() / panel.getHeight();
                        
                        g2.translate(pf.getImageableX(), pf.getImageableY());
                        g2.scale(scaleX, scaleY);
                        panel.printAll(g2);
                        
                    } finally {
                        g2.dispose();
                    }
                    return Printable.PAGE_EXISTS;
                }, pageFormat);
                new Thread(() -> {
                    try {
                        printerJob.print();
                    } catch (PrinterException ex) {
                        errorListener.onError(ex);
                    }
                }).start();

            } catch (PrinterException ex) {
                errorListener.onError(ex);
            }
        });
    }

}
