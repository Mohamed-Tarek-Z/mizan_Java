package docFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import utils.ArabicDigits;

public class IntDocumentFilter extends DocumentFilter {

    private int maxLength;
    private final boolean isEven;

    public IntDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
        this.isEven = false;
    }

    public IntDocumentFilter(int maxLength, boolean isEven) {
        this.maxLength = maxLength;
        this.isEven = isEven;
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

        String outputText = applyLimits(text);
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
        // we may add if here to allow even only 
        if (isEven && currentLength == maxLength - 1 && (text.length() == 1 && ("1".equals(text) || "3".equals(text) || "5".equals(text) || "7".equals(text) || "9".equals(text)))) {
            return;
        }

        outputText = ArabicDigits.toArabicDigits(outputText);

        super.replace(fb, offset, length, outputText, attrs);
    }

    private String applyLimits(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
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
