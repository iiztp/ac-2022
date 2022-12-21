package fr.ac.e.interpreter.instructions;

import fr.ac.e.interpreter.SymbolTable;
import fr.ac.e.interpreter.values.Value;
import fr.ac.e.interpreter.values.Variable;

public class Assign extends Instruction
{
    public Value right;

    public Assign(Variable s, Value x) {
        left = s;
        right = x;
    }

    @Override
    public int evaluate() {
        int r = right.evaluate();
        SymbolTable.table.addCorrespondance(left, r);
        return r;
    }

    @Override
    public int moduloEvaluate(int number) {
        // Modulo déjà fait par Entier (donc variable lors de l'assignement)
        return evaluate();
    }

    @Override
    public boolean contains(Variable l) {
        return right.equals(l);
    }

    @Override
    public String toString() {
        return left + " = " + right + "\n";
    }


}
