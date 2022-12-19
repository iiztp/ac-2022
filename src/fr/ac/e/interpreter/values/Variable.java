package fr.ac.e.interpreter.values;

import fr.ac.e.interpreter.SymbolTable;

public class Variable implements Value
{
    String var;
    public Variable(String s){
        var = s;
    }

    @Override
    public String toString(){
        return "Variable " + var;
    }

    @Override
    public int evaluate() {
        return SymbolTable.table.getValue(var);
    }
}
