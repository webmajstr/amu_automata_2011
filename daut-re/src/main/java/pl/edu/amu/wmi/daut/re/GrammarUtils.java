package pl.edu.amu.wmi.daut.re;

/**
 * metoda sprawdzająca, czy podana gramatyka jest gramatyką liniową
 */
public class GrammarUtils {
    public boolean isLinear(Grammar g) {
        for (GrammarRule rule : g.allRules()) {
          
            int terminalSymbols = 0;
         
                for(GrammarSymbol symbol : rule.getRhsSymbols())
                    if(symbol.isTerminalSymbol())
                        terminalSymbols++;
          
            if(terminalSymbols > 1)
                return false;
        }
        return true;
    }
}

