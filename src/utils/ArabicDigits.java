package utils;

import java.text.DecimalFormat;

public final class ArabicDigits {

    private static final char[] ARABIC_INDIC = {
        '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'
    };

    public static final char ARABIC_DECIMAL_SEPARATOR = '\u066B';

    private ArabicDigits() {
    }

    public static String toArabicDigits(int s) {
        return toArabicDigits(s + "");
    }

    public static String toArabicDigits(double s) {
        return toArabicDigits(new DecimalFormat("0.00").format(s));
    }

    public static String toArabicDigits(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(s.length());
        boolean foundDigit = false;
        boolean foundDecimal = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int value = Character.digit(c, 10);
            if (value >= 0) {
                foundDigit = true;
                sb.append(ARABIC_INDIC[value]);
            } else {
                if (i + 1 < s.length() && (c == 'ز' || c == '.') && isDigit(s.charAt(i + 1)) && !foundDecimal && foundDigit) {
                    sb.append(ARABIC_DECIMAL_SEPARATOR);
                    foundDecimal = true;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static String toWesternDigits(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int value = Character.digit(c, 10);
            sb.append(value >= 0 ? Character.forDigit(value, 10) : c);
        }
        return sb.toString();
    }

    public static String normalizeForParsing(String s) {
        if (s == null) {
            return null;
        }
        return toWesternDigits(s).replace(ARABIC_DECIMAL_SEPARATOR, '.');
    }

    public static int parseInt(String s) {
        return Integer.parseInt(normalizeForParsing(s).trim());
    }

    public static double parseDouble(String s) {
        return Double.parseDouble(normalizeForParsing(s).trim());
    }

    public static boolean isDigit(char c) {

        return (c >= '0' && c <= '9') || (c >= '٠' && c <= '٩');
    }

    public static boolean isDecimalSeparator(char c) {
        return c == '.' || c == ARABIC_DECIMAL_SEPARATOR;
    }
}
