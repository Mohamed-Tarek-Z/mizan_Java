package docFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import utils.ArabicDigits;

public class StringDocumentFilter extends DocumentFilter {

    private int maxLength;

    public StringDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
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

        outputText = ArabicDigits.toArabicDigits(outputText);

        super.replace(fb, offset, length, outputText, attrs);
    }

    private String applyLimits(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ArabicDigits.ARABIC_DECIMAL_SEPARATOR || c == '.') {
                sb.append(ArabicDigits.ARABIC_DECIMAL_SEPARATOR);
                continue;
            }
            sb.append(c);
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
