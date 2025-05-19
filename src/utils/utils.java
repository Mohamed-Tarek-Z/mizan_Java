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
import javax.imageio.ImageIO;

public class utils {

    /**
     * Takes a char and if it is a number returns the equivalent number in
     * Arabic letters
     *
     * @param input Char to be checked if it has an equivalent number in Arabic
     * letters
     * @return char equivalent to input number in Arabic letters Or return input
     * when no equivalent number in Arabic letters
     */
    public char ToNumArab(char input) {
        return switch (input) {
            case '0' ->
                '٠';
            case '1' ->
                '١';
            case '2' ->
                '٢';
            case '3' ->
                '٣';
            case '4' ->
                '٤';
            case '5' ->
                '٥';
            case '6' ->
                '٦';
            case '7' ->
                '٧';
            case '8' ->
                '٨';
            case '9' ->
                '٩';
            case '.' ->
                '٫';
            case 'ز' ->
                '٫';
            default ->
                input;
        };
    }

    /**
     * Takes a char and if it is a number returns the equivalent number in
     * English letters
     *
     * @param input Char to be checked if it has an equivalent number in English
     * letters
     * @return char equivalent to input number in English letters Or return
     * input when no equivalent number in English letters
     */
    public char ToNumEng(char input) {
        return switch (input) {
            case '٠' ->
                '0';
            case '١' ->
                '1';
            case '٢' ->
                '2';
            case '٣' ->
                '3';
            case '٤' ->
                '4';
            case '٥' ->
                '5';
            case '٦' ->
                '6';
            case '٧' ->
                '7';
            case '٨' ->
                '8';
            case '٩' ->
                '9';
            case '٫' ->
                '.';
            default ->
                input;
        };
    }

    /**
     * Takes a String and if it is a number in Arabic returns the equivalent in
     * double type
     *
     * @param input String to be converted to double
     * @return double equivalent to input number in Arabic letters Or return 0.0
     * when no equivalent number in Arabic letters
     */
    public double ToDoubleEnglish(String input) {
        if (input == null) {
            return 0.0;
        }
        String eng = "";
        for (char c : input.toCharArray()) {

            if (c == '-' || c == '+') {
                eng = c + eng;
            } else {
                if (Character.isDigit(c) || c == '.' || c == '٫') {
                    eng += ToNumEng(c);
                }
            }

        }
        return Double.parseDouble(eng);
    }

    /**
     * Takes a String and if it is a number in Arabic returns the equivalent in
     * String type in english
     *
     * @param input String to be converted to English string
     * @return English String equivalent to input number in Arabic letters Or
     * return "" when no equivalent number in Arabic letters
     */
    public String ToStringEnglish(String input) {
        if (input == null) {
            return "";
        }
        String eng = "";
        for (char c : input.toCharArray()) {
            eng += ToNumEng(c);
        }
        return eng;
    }

    /**
     * Takes a String and if it is a number in English returns the equivalent in
     * String type in Arabic
     *
     * @param input String to be converted to Arabic string
     * @return Arabic String equivalent to input number in Arabic letters Or
     * return "" when no equivalent number in Arabic letters
     */
    public String ToStringArabic(String input) {
        if (input == null) {
            return "";
        }
        String Arab = "";
        for (char c : input.toCharArray()) {
            Arab += ToNumArab(c);
        }
        return Arab;
    }

    /**
     * Takes a number in double and returns the equivalent in String type in
     * Arabic with Zeros like "١,٥٥٠"
     *
     * @param input double to be converted to Arabic double string like "١,٥٥٠"
     * @return Arabic String equivalent to input number in Arabic letters Or
     * return "" when no equivalent number in Arabic letters
     */
    public String ToDoubleArabic(double input) {
        String EnglishNum = new DecimalFormat("0.000").format(input);
        String Arab = "";
        for (char c : EnglishNum.toCharArray()) {
            Arab += ToNumArab(c);
        }
        return Arab;
    }

    /**
     * Takes a text and add style to it to be used in jOptionpane
     *
     * @param text String to be styled for jOptionpane
     * @return styled String to use in jOptionpane
     */
    public String addStyle(String text) {
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
    public File NewName(String fileName) {
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
    public Properties CheckConfigFileAndFolder() throws BusinessException {
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
    public byte[] generateQRcode(String data, int width, int height) throws BusinessException {
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

}
