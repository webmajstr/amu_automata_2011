package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
/**
 *
 * @author adrian
 */
public class TestNegatedAsciiCharacter extends TestCase {
    /**
     * Test konstruktora AsciiCharacterClassOperator dla złych wartości parametru.
     */
    public void testAsciiCharacterClassOperatorWrongParams() {

        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add("zlyTest1");
            (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
            fail();
        } catch (UnknownAsciiCharacterClassException e) {
            assertTrue(true);
        }
    }

    /**
     * Test klasy znaków alnum.
     */
    public void testAlnumNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("alnum");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Z"));
        assertFalse(automaton.accepts("9"));
        assertFalse(automaton.accepts(""));
    }

    /**
     * Test klasy znaków alpha.
     */
    public void testAlphaNegatedAsciiCHaracter() {
       ArrayList<String> params = new ArrayList<String>();
        params.add("alpha");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Z"));
    }

    /**
     * Test klasy znaków blank.
     */
    public void testBlankNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("blank");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\t"));
    }

    /**
     * Test klasy znaków cntrl.
     */
    public void testCntrlNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("cntrl");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\u0000"));
        assertFalse(automaton.accepts("\u0001"));
    }

    /**
     * Test klasy znaków digit.
     */
    public void testDigitNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("digit");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("3"));
        assertFalse(automaton.accepts("4"));
        assertFalse(automaton.accepts("1"));
        assertFalse(automaton.accepts("9"));
    }

    /**
     * Test klasy znaków graph.
     */
    public void testGraphNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("graph");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("~"));
        assertFalse(automaton.accepts("-"));
    }

    /**
     * Test klasy znaków lower.
     */
    public void testLowerNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("lower");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("z"));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("g"));
    }

    /**
     * Test klasy znaków print.
     */
    public void testPrintNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("print");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertTrue(!automaton.accepts(" "));
        assertTrue(!automaton.accepts("a"));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("*"));
        assertFalse(automaton.accepts("$"));
        assertFalse(automaton.accepts("\""));
        assertFalse(automaton.accepts("("));
        assertFalse(automaton.accepts("0"));
    }

    /**
     * Test klasy znaków punct.
     */
    public void testPunctNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("punct");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("/"));
        assertFalse(automaton.accepts("@"));
        assertFalse(automaton.accepts("["));
    }

    /**
     * Test klasy znaków space.
     */
    public void testSpaceNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("space");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\u000B"));
        assertFalse(automaton.accepts("\t"));
        assertFalse(automaton.accepts("\n"));
        assertFalse(automaton.accepts("\f"));
    }

    /**
     * Test klasy znaków upper.
     */
    public void testUpperNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("upper");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("A"));
        assertFalse(automaton.accepts("C"));
        assertFalse(automaton.accepts("G"));
        assertFalse(automaton.accepts("Z"));
    }

    /**
     * Test klasy znaków word.
     */
    public void testWordNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("word");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("B"));
        assertFalse(automaton.accepts("_"));
        assertFalse(automaton.accepts("9"));
    }

    /**
     * Test klasy znaków xdigit.
     */
    public void testXdigitNegatedAsciiCHaracter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("xdigit");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("8"));
        assertFalse(automaton.accepts("A"));
        assertFalse(automaton.accepts("f"));
        assertFalse(automaton.accepts("F"));
        assertFalse(automaton.accepts("a"));
    }
}
