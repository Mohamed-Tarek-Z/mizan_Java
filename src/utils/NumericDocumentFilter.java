package utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {

    private int maxLength;
    private final boolean isDigitsOnly;
    private final boolean isDouble;

    public NumericDocumentFilter(int maxLength, boolean isDigitsOnly, boolean isDouble) {
        this.maxLength = maxLength;
        this.isDigitsOnly = isDigitsOnly;
        this.isDouble = isDouble;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
            throws BadLocationException {
        replace(fb, offset, 0, text, attrs);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text == null || text.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }

        String outputText = applyLimits(text, isDigitsOnly);
        if (outputText.isEmpty()) {
            return;
        }

        int currentLength = fb.getDocument().getLength();
        int resultingLength = currentLength - length + outputText.length();
        if (resultingLength > maxLength) {
            int room = maxLength - (currentLength - length);
            if (room <= 0) {
                return;
            }
            outputText = outputText.substring(0, Math.min(room, outputText.length()));
        }

        outputText = ArabicDigits.toArabicDigits(outputText);

        super.replace(fb, offset, length, outputText, attrs);
    }

    private String applyLimits(String text, boolean numbersOnly) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (!numbersOnly || (isDouble && c == ArabicDigits.ARABIC_DECIMAL_SEPARATOR || c == '.')) {
                    sb.append(ArabicDigits.ARABIC_DECIMAL_SEPARATOR);
                }
            }
        }
        return sb.toString();
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

}
