package fr.ac.e.interpreter.values;

import fr.ac.e.interpreter.SymbolTable;

public class Variable implements Value
{
    String var;
    public Variable(String s){
        var = s;
    }

    @Override
    public int evaluate() {
        return SymbolTable.table.getValue(this);
    }

    @Override
    public int moduloEvaluate(int number) {
        // Le nombre est déjà modulo grâce à la classe Entier donc on renvoit evaluate
        return evaluate();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj instanceof Variable v) {
            return v.var.equals(this.var);
        }
        return false;
    }

    @Override
    public String toString() {
        return var;
    }
}
