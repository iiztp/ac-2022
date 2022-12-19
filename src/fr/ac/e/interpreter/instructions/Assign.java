package fr.ac.e.interpreter.instructions;

import fr.ac.e.interpreter.SymbolTable;
import fr.ac.e.interpreter.values.Value;

public class Assign extends Instruction
{
    public Value right;

    public Assign(String s, Value x) {
        left = s;
        right = x;
    }

    @Override
    public int evaluate() {
        SymbolTable.table.addCorrespondance(left, right.evaluate());
        return 1;
    }
}
