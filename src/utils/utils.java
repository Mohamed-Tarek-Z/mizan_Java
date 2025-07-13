package utils;

import exceptions.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class utils {

    /**
     * Takes a String and if it is a number in Arabic returns the equivalent in
     * double type
     *
     * @param input String to be converted to double
     * @return double equivalent to input number in Arabic letters Or return 0.0
     * when no equivalent number in Arabic letters
     */
    public static double ToDoubleEnglish(String input) {
        if (input == null) {
            return 0.0;
        }
        String eng = toEnglishDigits(input);

        return Double.parseDouble(eng);
    }

    /**
     * Takes a number in double and returns the equivalent in String type in
     * Arabic with Zeros like "١,٥٥٠"
     *
     * @param input double to be converted to Arabic double string like "١,٥٥٠"
     * @return Arabic String equivalent to input number in Arabic letters Or
     * return "" when no equivalent number in Arabic letters
     */
    public static String ToDoubleArabic(double input) {
        String EnglishNum = new DecimalFormat("0.000").format(input);
        String Arab = toArabicDigits(EnglishNum);
        return Arab;
    }

    /**
     * Takes a text and add style to it to be used in jOptionpane
     *
     * @param text String to be styled for jOptionpane
     * @return styled String to use in jOptionpane
     */
    public static String addStyle(String text) {
        return "<html><body><h1 style='font-family: Arial; font-size: 20pt; text-align: center; '>"
                + text.strip() + "</h1></body></html>";
    }

    /**
     * Takes a fileName and add style to it to be used in jOptionpane
     *
     * @param fileName file name to be check if exist create new file with same
     * name but add number to it like >>> filename(1)
     * @return File object create with the new name
     */
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

    /**
     * Check for some files and folder like Configuration file and it parent
     * folder
     *
     * @return Properties object with Configuration file open in it
     * @throws exceptions.BusinessException
     */
    public static Properties CheckConfigFileAndFolder() throws BusinessException {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\Temp"));
            if (!Files.exists(Paths.get(System.getProperty("user.dir") + "\\Temp\\config.properties"))) {
                FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\config.properties");
                fileOutputStream.close();
            }

            Properties properties = new Properties();
            FileInputStream input = new FileInputStream(new File(System.getProperty("user.dir") + "\\Temp\\config.properties"));
            properties.load(input);
            return properties;

        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("ملف غير موجود");
        }
    }

    /**
     * generates QRcode with inputs send to it
     *
     * @param data the date to be added to QR code
     * @param width width of qr
     * @param height height of qr
     * @return byte[] buffer with qr data to save as you want
     * @throws exceptions.BusinessException
     */
    public static byte[] generateQRcode(String data, int width, int height) throws BusinessException {
        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 0);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, width, height, hintMap);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                ImageIO.write(image, "png", bos);
                return bos.toByteArray();
            }
        } catch (WriterException | IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new BusinessException("error on QR Gen");
        }
    }

    /**
     * Takes a text and add style to it to be used in jOptionpane
     *
     * @param input String to be checked for matching the pattern
     * @param patternToMatch String contain regex pattern
     * @return Boolean true if the input matches the pattern .
     */
    public static boolean isInputMatchPattern(String input, String patternToMatch) {
        Pattern pattern = Pattern.compile(patternToMatch);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static String toEnglishDigits(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replace("٠", "0").replace("١", "1").replace("٢", "2").replace("٣", "3")
                .replace("٤", "4").replace("٥", "5").replace("٦", "6").replace("٧", "7")
                .replace("٨", "8").replace("٩", "9").replace("٫", ".");
    }

    public static String toArabicDigits(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replace("0", "٠").replace("1", "١").replace("2", "٢").replace("3", "٣")
                .replace("4", "٤").replace("5", "٥").replace("6", "٦").replace("7", "٧")
                .replace("8", "٨").replace("9", "٩").replace(".", "٫").replace("ز", "٫");
    }

}
