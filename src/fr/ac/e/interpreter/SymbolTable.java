package fr.ac.e.interpreter;

import fr.ac.e.interpreter.values.Variable;

import java.util.HashMap;

public class SymbolTable
{
    public static SymbolTable table = new SymbolTable();
    private HashMap<Variable, Integer> symbValues = new HashMap<>();

    private SymbolTable() {

    }

    public void addCorrespondance(Variable variable, int value) {
        symbValues.put(variable, value);
    }

    public Integer getValue(Variable variable) {
        return symbValues.get(variable);
    }
}
