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

    public char ToNumArab(char eng) {
        return switch (eng) {
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
                eng;
        };
    }

    public char ToNumEng(char ArabC) {
        return switch (ArabC) {
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
                ArabC;
        };
    }

    public double ToDoubleEnglish(String ArabNum) {
        if (ArabNum == null) {
            return 0.0;
        }
        String eng = "";
        for (char c : ArabNum.toCharArray()) {

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

    public String ToStringEnglish(String ArabNum) {
        if (ArabNum == null) {
            return "";
        }
        String eng = "";
        for (char c : ArabNum.toCharArray()) {
            eng += ToNumEng(c);
        }
        return eng;
    }

    public String ToStringArabic(String EnglishNum) {
        if (EnglishNum == null) {
            return "";
        }
        String Arab = "";
        for (char c : EnglishNum.toCharArray()) {
            Arab += ToNumArab(c);
        }
        return Arab;
    }

    public String ToDoubleArabic(double English) {
        String EnglishNum = new DecimalFormat("0.000").format(English);
        String Arab = "";
        for (char c : EnglishNum.toCharArray()) {
            Arab += ToNumArab(c);
        }
        return Arab;
    }

    public String addStyle(String text) {
        return "<html><body><h1 style='font-family: Arial; font-size: 20pt; text-align: center; '>"
                + text.strip() + "</h1></body></html>";
    }

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

    public byte[] generateQRcode(String data, int SizeX, int SizeY) throws BusinessException {
        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 0);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, SizeX, SizeY, hintMap);
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
