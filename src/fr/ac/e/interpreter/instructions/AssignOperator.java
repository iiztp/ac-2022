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
    public int moduloEvaluate(int number) {
        int i = switch (op) {
            case "+" -> (t0.evaluate() + t1.evaluate())%number;
            case "*" -> multiply(t0.evaluate(),t1.evaluate(), number);
            case "-" -> (t0.evaluate() - t1.evaluate())%number;
            default -> 0;
        };
        SymbolTable.table.addCorrespondance(left, i);
        return i;
    }

    private int multiply(int x, int y, int p) {
        int result = 0;
        while(y != 0) {
            if((y & 1) != 0)
                result = (result+x)%p;
            y >>= 1;
            x = (2*x)%p;
        }
        return result;
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
