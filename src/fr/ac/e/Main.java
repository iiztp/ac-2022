package fr.ac.e;

import fr.ac.e.interpreter.values.Program;

public class Main {
    public static void main(String[] args) {
        Program p = new Program("prog1");
        System.out.println(p.evaluate());
    }
}