package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
* Klasa reprezentuj¹c¹ operator '{n,}' z wyra¿eñ regularnych.
*/
public class MinimumNumberOfOccurrencesOperator extends UnaryRegexpOperator {

    private int numberOfOccurrences;

    /**
     * Konstruktor klasy.
     */
    public MinimumNumberOfOccurrencesOperator(int x) {
        numberOfOccurrences = x;
    }

    /**
     * G³ówna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {

        AutomatonSpecification newAutomaton = new NaiveAutomatonSpecification();

        if (numberOfOccurrences == 0) {
            State state = newAutomaton.addState();
            newAutomaton.markAsInitial(state);
            newAutomaton.markAsFinal(state);
        }

        if (numberOfOccurrences > 0) {
            newAutomaton = subautomaton.clone();
            for (int i = 1; i < numberOfOccurrences; i++) {
                State newState = newAutomaton.addState();

                for (State state : newAutomaton.allStates()) {
                    if (newAutomaton.isFinal(state)) {
                        newAutomaton.addTransition(state, newState, new EpsilonTransitionLabel());
                        newAutomaton.unmarkAsFinalState(state);
                    }
                }
                newAutomaton.insert(newState, subautomaton);
            }
            State newState=newAutomaton.addState();
            newAutomaton.insert(newState, subautomaton);
            for (State state : newAutomaton.allStates()) {
                if (newAutomaton.isFinal(state)) {
                    newAutomaton.addTransition(state, newState, new EpsilonTransitionLabel());
                }
            }
        }
        return newAutomaton;
    }

     /**
     * Fabryka operatora.
     */
    public static class Factory extends UnaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new MinimumNumberOfOccurrencesOperator(Integer.parseInt(params.get(0)));
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "MINIMUM_" + numberOfOccurrences + "_TIMES";
    }

}
