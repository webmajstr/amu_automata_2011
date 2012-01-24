package pl.edu.amu.wmi.daut.base;

class PositionOutOfStringBordersException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

/**
 * Przejście działające jak epsilon-przejście pod warunkiem, że automat znajduje się na końcu
 * napisu lub na końcu wiersza. Odpowiada to operatorowi $ z wyrażeń regularnych, jeśli włączono
 * tryb multiline.
 */
public class EndOfTextOrLineTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {
        if (s.length() < position || position < 0) {
            throw new PositionOutOfStringBordersException();
        }
        if (position == s.length() || s.charAt(position) == '\n') {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "EndOfTextOrLine";
    }

}
