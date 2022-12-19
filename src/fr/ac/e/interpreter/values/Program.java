package fr.ac.e.interpreter.values;

import fr.ac.e.interpreter.Evaluable;
import fr.ac.e.interpreter.Parser;
import fr.ac.e.interpreter.SymbolTable;
import fr.ac.e.interpreter.instructions.Assign;
import fr.ac.e.interpreter.instructions.AssignOperator;
import fr.ac.e.interpreter.instructions.Instruction;
import fr.ac.e.utils.Utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Program implements Evaluable
{
    Vector<Instruction> instructions;

    public Program(String file) {
        instructions = Parser.parse(Objects.requireNonNull(Utils.FileToString(file)));
    }

    public Program() {
        instructions = new Vector<>();
    }

    public void addInstructions(Instruction ... i) {
        instructions.addAll(Arrays.asList(i));
    }

    @Override
    public int evaluate() {
        for(Instruction i : instructions) {
            i.evaluate();
        }
        return SymbolTable.table.getValue("x");
    }
}
