package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 * Klasa reprezentujaca operator {n,} z wyrazen regularnych
 * element wystepuje co najmniej n razy
 */
public abstract class MinimumNumberOfOccurencesOperator extends UnaryRegexpOperator {
    private int n;
    public MinimumNumberOfOccurencesOperator(int x) {
		this.n = x;
	}

    public AutomatonSpecification createAutomatonFromOneAutomaton(AutomatonSpecification subautomaton) {
        AutomatonSpecification inputautomat = subautomaton.clone();
        AutomatonSpecification newautomat = subautomaton.clone();
        for (int i = 0; i < this.n - 1; i++) {
            for (State stan : newautomat.allStates()) {
                if (newautomat.isFinal(stan)) {
                    newautomat.addTransition(stan, inputaut.getInitialState(), new EpsilonTransitionLabel());
                    newautomat.unmarkAsFinalState(stan);
                }
            }
        }
        for (State stan : inputautomat.allStates()) {
            if (inputautomat.isFinal(stan)) {
                inputautomat.addTransition(stan, inputaut.getInitialState(), new EpsilonTransitionLabel());
            }
        }
        for (State stan : newautomat.allStates()) {
            if (newautomat.isFinal(stan)) {
                    newautomat.addTransition(stan, inputautomat.getInitialState(), new EpsilonTransitionLabel());
            }
        }
        return newautomat;
    }
}