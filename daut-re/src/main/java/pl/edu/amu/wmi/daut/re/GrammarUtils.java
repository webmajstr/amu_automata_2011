package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * metoda sprawdzająca, czy podana gramatyka jest gramatyką liniową
 */
public class GrammarUtils {
   public boolean isLinear(Grammar g) {
      for (GrammarRule rule : g.allRules()) {
          
         if (!rule.getRhsFirstSymbol().isTerminalSymbol() && !rule.getRhsSecondSymbol().isTerminalSymbol()) {
            return false;
            
         }
      }
      return true;
   }
}

