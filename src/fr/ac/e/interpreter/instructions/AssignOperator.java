package fr.ac.e.interpreter.instructions;

import fr.ac.e.interpreter.SymbolTable;
import fr.ac.e.interpreter.values.Value;

public class AssignOperator extends Instruction{
    public String op;
    public Value t0;
    public Value t1;

    public AssignOperator(String s, String ope, Value x, Value y){
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
        return 1;
    }
}
