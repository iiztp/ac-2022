package fr.ac.e.interpreter.instructions;

import fr.ac.e.interpreter.SymbolTable;
import fr.ac.e.interpreter.values.Value;
import fr.ac.e.interpreter.values.Variable;

public class AssignOperator extends Instruction{
    public String op;
    public Value t0;
    public Value t1;

    public AssignOperator(Variable s, String ope, Value x, Value y){
        left = s;
        op = ope;
        t0 = x;
        t1 = y;
    }

    @Override
    public int evaluate() {
        int i = switch (op) {
            case "+" -> t0.evaluate() + t1.evaluate();
            case "*" -> t0.evaluate() * t1.evaluate();
            case "-" -> t0.evaluate() - t1.evaluate();
            default -> 0;
        };
        SymbolTable.table.addCorrespondance(left, i);
        return i;
    }

    @Override
    public boolean contains(Variable l) {
        return t0.equals(l) || t1.equals(l);
    }

    @Override
    public String toString() {
        return left + " = " + t0 + " " + op + " " + t1 + "\n";
    }
}
