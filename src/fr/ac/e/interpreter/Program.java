package fr.ac.e.interpreter;

import fr.ac.e.interpreter.instructions.Instruction;
import fr.ac.e.interpreter.values.Variable;
import fr.ac.e.utils.Utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

public class Program implements Evaluable, Iterable<Instruction>
{
    Vector<Instruction> instructions;

    public Program(String file) {
        instructions = Parser.parse(Objects.requireNonNull(Utils.FileToString(file)));
    }

    public Program(Program p) {
        instructions = (Vector<Instruction>) p.instructions.clone();
    }

    public void addInstructions(Instruction ... i) {
        instructions.addAll(Arrays.asList(i));
    }

    public void removeInstructions(Instruction ... i) {
        instructions.removeAll(Arrays.asList(i));
    }

    @Override
    public int evaluate() {
        for(Instruction i : instructions) {
            i.evaluate();
        }
        return SymbolTable.table.getValue(new Variable("x"));
    }

    @Override
    public Iterator<Instruction> iterator() {
        return instructions.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Instruction i : instructions) {
            sb.append(i);
        }
        return sb.toString();
    }
}
