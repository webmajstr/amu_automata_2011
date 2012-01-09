package pl.edu.amu.wmi.daut.re;

import java.util.Stack;
import pl.edu.amu.wmi.daut.base.Acceptor;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import pl.edu.amu.wmi.daut.base.ComplementCharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.base.State;

/**
 * Klasa reprezentująca podstawowe wyrażenie regularne POSIX
 */
public class BasicPosixRegexp implements Acceptor{
    /**
     * Konsruktor, wczytuje z klawiatury String reprezentujący wyrażenie regularne.
     */
    public BasicPosixRegexp(String text) {
        regexp = text;
    }
    public static final String regexp;
    public static int arrowPointer = 0;
    public static final Stack<AutomatonSpecification> stackOfAutomatons;
    public static final AutomatonSpecification automaton = new NaiveAutomatonSpecification();
    /**
     * Właściwa metoda, zwracająca automat akceptujący wszystkie łańcuchy
     * generowane przez wyrażenie regularne.
     */
    private AutomatonSpecification makeAutomatonAcceptsRegexp() {
        stackOfAutomatons = new Stack<AutomatonSpecification>();
        while (arrowPointer < Regexp.length()) {
            if (commonChar(regexp)) {
                continue;
            }
            if (findKleeneStar(regexp)) {
                continue;
            }
            if (findFirstCharClass(regexp)) {
                continue;
            }
            if (findSecondCharClass(regexp)) {
                continue;
            }
            if (findAnyChar(regexp)) {
                continue;
            }
        }
        return mergeAutomatons(stackOfAutomatons);
    }
    /**
     * Odczytuje ze Stringa (o ile kursor ustawiony jest w odpowiednim miejscu)
     * zwyczajny znak (poprzedzony '\', np. '\*' to '*'), odkłada na
     * stos odpowiedni automat oraz zwraca 1 lub 0 (odpowiednio jeśli dokonano
     * zmian lub nie).
     */
    public static boolean commonChar(String text) {
        if (text.charAt(arrowPointer) == '\\') {
            stackOfAutomatons.push(new NaiveAutomatonSpecification().
                makeOneTransitionAutomaton(text.charAt(arrowPointer + 1)));
            arrowPointer = arrowPointer + 2;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Odczytuje ze Stringa (o ile kursor ustawiony jest w odpowiednim miejscu)
     * klasę znaków ujętą w nawiasy kwadratowe [..] - bez ^, odkłada na
     * stos odpowiedni automat oraz zwraca 1 lub 0 (odpowiednio jeśli dokonano
     * zmian lub nie).
     */
    public static boolean findFirstCharClass(String text) {
        if (text.charAt(arrowPointer) == '[' && text.charAt(arrowPointer + 1) != '^') {
            String charClass = new String();
            for (int k = arrowPointer + 1; k < text.length(); k++) {
                if (text.charAt(k) == ']') {
                    for (int m = arrowPointer + 1; m < k; m++) {
                        charClass += text.charAt(m);
                    }
                    arrowPointer += k - arrowPointer + 1;
                    break;
                }
            }
            //po opracowaniu CharClassOperator   stackOfAutomatons.push(
                   // new CharClassOperator(charClass).createFixedAutomaton());
            return true;
        } 
        return false;
    }
    /**
     * Odczytuje ze Stringa (o ile kursor ustawiony jest w odpowiednim miejscu)
     * klasę znaków ujętą w nawiasy kwadratowe [^..] - tzw. dopełnienie ,ignoruje znak nowej linii !
     * Odkłada na stos odpowiedni automat oraz zwraca 1 lub 0 (odpowiednio jeśli dokonano
     * zmian lub nie).
     */
    public static boolean findSecondCharClass(String text) {
        if (text.charAt(arrowPointer) == '[' && text.charAt(arrowPointer + 1) == '^') {
            String charClass = new String();
            for (int k = arrowPointer + 2; k < text.length(); k++) {
                if (text.charAt(k) == ']') {
                    for (int m = arrowPointer + 2; m < k; m++) {
                        charClass += text.charAt(m);
                    }
                    arrowPointer += k - arrowPointer + 1;
                    break;
                }
            }
            ComplementCharClassTransitionLabel c =
                new ComplementCharClassTransitionLabel(charClass);
            AutomatonSpecification automata = new NaiveAutomatonSpecification();
            State q0 = automata.addState();
            State q1 = automata.addState();
            automata.markAsInitial(q0);
            automata.markAsFinal(q1);
            for (int i = 0; i < 256; i++) {
                if (c.canAcceptCharacter((char) i) && i != 10) {
                    automata.addTransition(q0, q1, new CharTransitionLabel((char) i));
                }
            }
            stackOfAutomatons.push(automata);
            return true;    
        } else {
            return false;
        }
        //lub jeśli powstanie przy użyciu ComplementCharClassOperator
    }
    /**
     * Odczytuje ze Stringa (o ile kursor ustawiony jest w odpowiednim miejscu)
     * gwiazdkę *, reprezentującą domknięcie Kleen'ego
     * (znaki poprzedzone '\' są rozpatrywane w metodzie commonChar). Odkłada na
     * stos odpowiedni automat oraz zwraca 1 lub 0 (odpowiednio jeśli dokonano
     * zmian lub nie).
     */
    public static boolean findKleeneStar(String text) {
        if (text.charAt(arrowPointer) == '*') {
            AutomatonSpecification aut = stackOfAutomatons.pop();          
            stackOfAutomatons.push(AutomataOperations.getKleeneStar(aut));
            arrowPointer++;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Odczytuje ze Stringa (o ile kursor ustawiony jest w odpowiednim miejscu) znak
     * '.',reprezentującą dowolny znak (znaki poprzedzone '\' są rozpatrywane w metodzie commonChar)
     * Odkłada na stos odpowiedni automat oraz zwraca 1 lub 0 (odpowiednio jeśli dokonano
     * zmian lub nie).
     */
    public static boolean findAnyChar(String text) {
        if (text.charAt(arrowPointer) == '.') {
            stackOfAutomatons.push(new AnyCharOperator().createFixedAutomaton());
            arrowPointer++;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Metoda, scala automaty (na podstawie stosu automatów przekazywanego jako parametr)
     * i zwraca końcowy automat, akceptujący język wyrażany przez wyrażenie regularne.
     */
    public static AutomatonSpecification mergeAutomatons(
        Stack<AutomatonSpecification> automataStack) {
        boolean end = false;
        while (automataStack.size() >= 1) {
            Stack<AutomatonSpecification> tmp = new Stack<AutomatonSpecification>();
            if (end) {
                break;
            }
            while (!automataStack.isEmpty()) {
                AutomatonSpecification aut = automataStack.pop();
                tmp.push(aut);
            }
            while (tmp.size() >= 1) {
                if (tmp.size() == 1 && automataStack.size() == 0) {
                    AutomatonSpecification automaton = tmp.pop();
                    automataStack.push(automaton);
                    end = true;
                    break;
                } 
                if (tmp.size() == 1 && automataStack.size() != 0) {
                    AutomatonSpecification automata = tmp.pop();
                    automataStack.push(automata);
                    break;
                } else {
                    automataStack.push(new ConcatenationOperator().
                        createAutomatonFromTwoAutomata(tmp.pop(), tmp.pop()));
                }
            }
        }
        AutomatonSpecification aut = automataStack.pop();
        return aut;
    }   
    @Override
    public boolean accepts(String text) {
        NondeterministicAutomatonByThompsonApproach tmp
            = new NondeterministicAutomatonByThompsonApproach(this.makeAutomatonAcceptsRegexp());
        return tmp.accepts(text);
    }
}
