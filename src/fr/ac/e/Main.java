package fr.ac.e;

import fr.ac.e.interpreter.Program;
import fr.ac.e.interpreter.Simplifier;

public class Main {
    public static void main(String[] args) {
        Program p = new Program("question3_test5");
        System.out.println(p.evaluate());
        Simplifier s = new Simplifier(p);
        System.out.println(s.getSimplifiedP());
    }
}