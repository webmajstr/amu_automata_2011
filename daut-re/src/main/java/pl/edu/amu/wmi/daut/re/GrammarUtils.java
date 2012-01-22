package pl.edu.amu.wmi.daut.re;

import java.util.List;

public class GrammarUtils {

    public boolean isChomsky(Grammar g) {

        List<GrammarRule> reguly = g.allRules();
        boolean wynik = false;

        for (int i = 0; i == reguly.size(); i++) {
            GrammarRule regula = reguly.get(i);
            if(regula.getArity() == 2) {
                if((regula.getRhsFirstSymbol().getClass()
                        == GrammarNonterminalSymbol.class) 
                        && (regula.getRhsSecondSymbol().getClass()
                                == GrammarNonterminalSymbol.class)) {wynik = true;}
                    else {wynik = false;}
                } else if(regula.getArity() == 1) {
                    if(regula.getRhsFirstSymbol().getClass() == GrammarTerminalSymbol.class) {wynik = true;}
                    else {wynik = false;}
            } else {wynik = false;}
        }
        return wynik;
    }
}
