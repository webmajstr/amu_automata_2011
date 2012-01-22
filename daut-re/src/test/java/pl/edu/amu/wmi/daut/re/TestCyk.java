package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;

/**
 * Test klasy Cyl.
 */
public class TestCyk extends TestCase {
    private HashMap<String, GrammarNonterminalSymbol> 
            nonterminalSymbol = new HashMap<String, GrammarNonterminalSymbol>();
    private HashMap<String, GrammarTerminalSymbol> 
            terminalSymbol = new HashMap<String, GrammarTerminalSymbol>();
    private List<GrammarRule> rules = new ArrayList<GrammarRule>();
    private List<GrammarTerminalSymbol> word = new ArrayList<GrammarTerminalSymbol>();
    /**
     * Generowanie symobli terminalnych i nieterminalnych.
     */
    private final void generateSymobols() {
        nonterminalSymbol.put("S", new GrammarNonterminalSymbol('S'));
        nonterminalSymbol.put("A", new GrammarNonterminalSymbol('A'));
        nonterminalSymbol.put("B", new GrammarNonterminalSymbol('B'));
        nonterminalSymbol.put("C", new GrammarNonterminalSymbol('C'));
        terminalSymbol.put("a", new GrammarTerminalSymbol('a'));
        terminalSymbol.put("b", new GrammarTerminalSymbol('b'));
    }
    /**
     * Pierwszy test klasy Cyk.
     */
    public final void testCyk1() {
        generateSymobols();
        rules.clear();
        word.clear();
        rules.add(new GrammarRule(nonterminalSymbol.get("S"), 
                nonterminalSymbol.get("A"), nonterminalSymbol.get("C")));
        rules.add(new GrammarRule(nonterminalSymbol.get("C"), 
                nonterminalSymbol.get("S"), nonterminalSymbol.get("B")));
        rules.add(new GrammarRule(nonterminalSymbol.get("S"), 
                nonterminalSymbol.get("A"), nonterminalSymbol.get("B")));
        rules.add(new GrammarRule(nonterminalSymbol.get("A"), terminalSymbol.get("a")));
        rules.add(new GrammarRule(nonterminalSymbol.get("B"), terminalSymbol.get("b")));
        Cyk cyk = new Cyk(new Grammar(rules, nonterminalSymbol.get("S")));
        word.add(terminalSymbol.get("a"));
        word.add(terminalSymbol.get("a"));
        word.add(terminalSymbol.get("b"));
        word.add(terminalSymbol.get("b"));
        assertTrue(cyk.accepts(word));
    }
    /**
     * Drugi test klasy Cyk.
     */
    public final void testCyk2() {
        generateSymobols();
        rules.clear();
        word.clear();
        rules.add(new GrammarRule(nonterminalSymbol.get("S"), 
                nonterminalSymbol.get("A"), nonterminalSymbol.get("B")));
        rules.add(new GrammarRule(nonterminalSymbol.get("S"), 
                nonterminalSymbol.get("B"), nonterminalSymbol.get("C")));
        rules.add(new GrammarRule(nonterminalSymbol.get("A"), 
                nonterminalSymbol.get("B"), nonterminalSymbol.get("A")));
        rules.add(new GrammarRule(nonterminalSymbol.get("A"), terminalSymbol.get("a")));
        rules.add(new GrammarRule(nonterminalSymbol.get("B"), 
                nonterminalSymbol.get("C"), nonterminalSymbol.get("C")));
        rules.add(new GrammarRule(nonterminalSymbol.get("B"), terminalSymbol.get("b")));
        rules.add(new GrammarRule(nonterminalSymbol.get("C"), 
                nonterminalSymbol.get("A"), nonterminalSymbol.get("B")));
        rules.add(new GrammarRule(nonterminalSymbol.get("C"), terminalSymbol.get("a")));
        Cyk cyk = new Cyk(new Grammar(rules, nonterminalSymbol.get("S")));
        word.add(terminalSymbol.get("a"));
        word.add(terminalSymbol.get("b"));
        word.add(terminalSymbol.get("a"));
        word.add(terminalSymbol.get("b"));
        assertTrue(cyk.accepts(word));
    }
}
