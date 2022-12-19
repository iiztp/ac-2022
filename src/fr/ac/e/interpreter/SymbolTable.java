package fr.ac.e.interpreter;

import java.util.HashMap;

public class SymbolTable
{
    public static SymbolTable table = new SymbolTable();
    private HashMap<String, Integer> symbValues = new HashMap<>();

    private SymbolTable() {

    }

    public void addCorrespondance(String variable, int value) {
        symbValues.put(variable, value);
    }

    public Integer getValue(String variable) {
        return symbValues.get(variable);
    }
}
