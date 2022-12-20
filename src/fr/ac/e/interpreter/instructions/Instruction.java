package fr.ac.e.interpreter.instructions;

import fr.ac.e.interpreter.Evaluable;
import fr.ac.e.interpreter.values.Variable;

public abstract class Instruction implements Evaluable {
    public Variable left;

    public abstract boolean contains(Variable l);
}
