package utils;

import exceptions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {

    /**
     * Takes a text and add style to it to be used in jOptionpane
     *
     * @param text String to be styled for jOptionpane
     * @return styled String to use in jOptionpane
     */
    public static String addStyle(String text) {
        return "<html><body style='width: 300px'><h1 style='font-family: Arial; font-size: 17pt; text-align: center; '>"
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
}
