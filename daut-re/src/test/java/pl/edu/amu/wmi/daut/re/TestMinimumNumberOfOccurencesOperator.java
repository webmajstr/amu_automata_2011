package pl.edu.amu.wmi.daut.re;


import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.re.TestMinimumNumberOfOccurencesOperator;
import java.util.ArrayList;

/**
 * Test klasy MnimumNumberOfOccurrencesOperator.
 */

public class TestMinimumNumberOfOccurencesOperator extends TestCase {

    /**
    * Automat z jednym przejściem na dwóch stanach.
    */
    public final void TestTwoStatesOneTransitionLabel() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));

        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(3);

        NondeterministicAutomatonByThompsonApproach result =
            new NondeterministicAutomatonByThompsonApproach(
            oper.createAutomatonFromOneAutomaton(automaton));


        assertTrue(result.accepts("aaa"));
		assertTrue(result.accepts("aaaa"));
		assertTrue(result.accepts("aaaaa"));
		assertTrue(result.accepts("aaaaaaaaaaaa"));
        assertFalse(result.accepts("aa"));
		assertFalse(result.accepts("a"));
        assertFalse(result.accepts("LeagueOfLegends"));
    }

    /**
    * Automat akceptuje słowa a(b^n)a.
    */
    public final void testExemplaryAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q1, q2, new CharTransitionLabel('a'));
        automaton.addLoop(q1, new CharTransitionLabel('b'));


        automaton.markAsInitial(q0);
        automaton.markAsFinal(q2);

        MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(2);

        NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));

        assertTrue(result.accepts("aaaa"));
        assertTrue(result.accepts("abbaaba"));
        assertTrue(result.accepts("aaabaabba"));
        assertTrue(result.accepts("aaabaabbaabbbaabbbba"));
        assertFalse(result.accepts("abc"));
        assertFalse(result.accepts("aa"));
        assertFalse(result.accepts("ManchesterUnited"));
    }


    /**
    * Pusty automat.
    */
public final void testEmptyAutomaton() {
         
          AutomatonSpecification automaton = new NaiveAutomatonSpecification();
         
          MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(10);
          
          NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));
          
          assertFalse(result.accepts("aaaaaaaaaaaaaaaaaaaaaaaa"));  
          assertFalse(result.accepts(""));  
          assertFalse(result.accepts("a"));  
          assertFalse(result.accepts("aaa")); 
          assertFalse(result.accepts("Zonk"));  
     }    

    /**
    * Test 4. Automat z trzema stanami. Od początkowego stanu q0 wychodzą dwa
    * przejścia do stanu do stanu q1 poprzez 'a' i do stanu q2 poprzez 'b'.
    * Stany q1 i q2 są stanami akceptowalnymi. 
    */

public final void testThreeStatesTwoFinalStates() {
    
     AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0, q2, new CharTransitionLabel('b'));
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        automaton.markAsFinal(q2);
        
        MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(3);
        
        NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));
        
        assertTrue(result.accepts("aaaa"));
        assertTrue(result.accepts("bbbbbb"));
        assertTrue(result.accepts("bbabab"));
        assertTrue(result.accepts("aababa"));
        assertFalse(result.accepts("ab"));
	assertFalse(result.accepts("aa"));
	assertFalse(result.accepts("a"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("ZlyTekst"));  
}


public final void testZeroRepeats() {
         
          AutomatonSpecification automaton = new NaiveAutomatonSpecification();
         
          MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(0);
          
          NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));
          
          assertTrue(result.accepts(""));  
          assertFalse(result.accepts("a"));  
          assertFalse(result.accepts("aaa")); 
          assertFalse(result.accepts("Zonk"));  
     }   

      //    (ac^nb)||(bd^na){3,}
public final void testAlternativeLoops() {
         
          AutomatonSpecification automaton = new NaiveAutomatonSpecification();
          
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        State q3 = automaton.addState();
        State q4 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0, q2, new CharTransitionLabel('b'));
        automaton.addTransition(q1, q3, new CharTransitionLabel('b'));
        automaton.addTransition(q2, q4, new CharTransitionLabel('a'));
        automaton.addLoop(q1, new CharTransitionLabel('c'));
        automaton.addLoop(q2, new CharTransitionLabel('d'));
        
       
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q3);
        automaton.markAsFinal(q4);
                
          MinimumNumberOfOccurencesOperator oper =
                new MinimumNumberOfOccurencesOperator(3);
          
          
          NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));
          
          assertTrue(result.accepts("acbbdabdda"));  
          assertTrue(result.accepts("ababab"));  
          assertTrue(result.accepts("abbaab"));   
          assertTrue(result.accepts("accccccbbdddaab"));
          assertFalse(result.accepts("aaa")); 
          assertFalse(result.accepts("abba")); 
          assertFalse(result.accepts("Zonk"));  
     }  
    /**
     * Test fabryki.
     *
  public void testFactory() {

        Factory factory = new Factory();
        RegexpOperator operator2 = 
                factory.createOperator(new ArrayList<String>());
        int arity = operator2.arity();
        assertEquals(arity, 1);
    }*/
}
