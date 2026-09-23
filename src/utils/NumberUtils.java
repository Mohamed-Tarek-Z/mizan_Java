package utils;

import java.text.DecimalFormat;

public final class NumberUtils {

    public static double ToDEng(String input) {
        if (input == null) {
            return 0.0;
        }
        return Double.parseDouble(ToEng(input));
    }

    public static String ToArb(double input) {
        return ToArb(new DecimalFormat("0.00").format(input));
    }

    public static String ToEng(String input) {

        if (input == null || input.isBlank()) {
            return " ";
        }

        StringBuilder text = new StringBuilder();
        boolean foundDigit = false;
        boolean foundDecimal = false;

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            if (isDigit(c)) {
                text.append(toEnglishDigit(c));
                foundDigit = true;
                continue;
            }
            if (isDecimalSeparator(c)) {
                if (!foundDecimal && foundDigit) {
                    text.append('.');
                    foundDecimal = true;
                }
                continue;
            }
            if (isThousandsSeparator(c)) {
                continue;
            }
            text.append(c);
        }
        return text.toString();
    }

    public static String ToArb(String input) {

        if (input == null || input.isBlank()) {
            return " ";
        }

        StringBuilder text = new StringBuilder();
        boolean foundDigit = false;
        boolean foundDecimal = false;

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            if (isDigit(c)) {
                text.append(toArabicDigit(c));
                foundDigit = true;
                continue;
            }
            if (isDecimalSeparator(c)) {
                if (!foundDecimal && foundDigit) {
                    text.append('٫');
                    foundDecimal = true;
                }
                continue;
            }
            if (isThousandsSeparator(c)) {
                continue;
            }
            if (i + 1 < input.length()) {
                if (c == 'ز' && isDigit(input.charAt(i + 1)) && !foundDecimal && foundDigit) {
                    text.append('٫');
                    foundDecimal = true;
                    continue;
                }
            }
            text.append(c);
        }
        return text.toString();
    }

    private static char toEnglishDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c;
        }
        if (c >= '٠' && c <= '٩') {
            return (char) ('0' + (c - '٠'));
        }

        throw new IllegalArgumentException("Not a supported digit");
    }

    private static char toArabicDigit(char c) {
        if (c >= '0' && c <= '9') {
            return (char) ('٠' + (c - '0'));
        }
        if (c >= '٠' && c <= '٩') {
            return c;
        }
        throw new IllegalArgumentException("Not a supported digit");
    }

    private static boolean isDigit(char c) {

        return (c >= '0' && c <= '9')
                || (c >= '٠' && c <= '٩')
                || (c >= '۰' && c <= '۹');
    }

    private static boolean isDecimalSeparator(char c) {
        return c == '.' || c == '٫';
    }

    private static boolean isThousandsSeparator(char c) {
        return c == ',' || c == '٬' || c == ' ';
    }
}
