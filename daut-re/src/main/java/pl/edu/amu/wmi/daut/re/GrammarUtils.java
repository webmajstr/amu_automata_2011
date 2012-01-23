package pl.edu.amu.wmi.daut.re;

import java.util.List;

/**
 * @author dyskograf Klasa przechowująca narzędzia do obsługi gramatyk..
 */
public class GrammarUtils {

    /**
     * Metoda sprawdzająca, czy podana gramatyka jest w postaci normalnej
     * Chomsky'ego.
     */
    public boolean isChomsky(Grammar g) {

        List<GrammarRule> rules = g.allRules();
        boolean outcome = false;

        for (GrammarRule rule : rules) {
            if (rule.getArity() == 2) {
                if (!(rule.getRhsFirstSymbol().isTerminalSymbol())
                        && !(rule.getRhsSecondSymbol().isTerminalSymbol())) {
                    outcome = true;
                } else {
                    outcome = false;
                }
            } else if (rule.getArity() == 1) {
                if (rule.getRhsFirstSymbol().isTerminalSymbol()) {
                    outcome = true;
                } else {
                    outcome = false;
                }
            } else {
                outcome = false;
            }
        }
        return outcome;
    }
}
