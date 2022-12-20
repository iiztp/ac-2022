package fr.ac.e.interpreter;

import fr.ac.e.interpreter.instructions.Assign;
import fr.ac.e.interpreter.instructions.AssignOperator;
import fr.ac.e.interpreter.instructions.Instruction;
import fr.ac.e.interpreter.values.Entier;
import fr.ac.e.interpreter.values.Value;
import fr.ac.e.interpreter.values.Variable;

import java.util.*;

public class Simplifier {
    private Program p;
    private Program simplifiedP;

    public Simplifier(Program p) {
        this.p = p;
        this.simplifiedP = new Program(p);
        removeKnownValues();
        removeUselessAssigns();
    }

    public Program getSimplifiedP() {
        return simplifiedP;
    }

    private void removeUselessAssigns() {
        Set<Instruction> instructionsToRemove = new HashSet<>();
        Map<Variable, Value> tempValues = new HashMap<>();
        // x = x
        for(Instruction i : simplifiedP.instructions) {
            if(i instanceof Assign a) {
                if(a.left.equals(a.right) || tempValues.getOrDefault(a.left, new Entier(-1)).equals(a.right)) {
                    instructionsToRemove.add(i);
                } else
                    tempValues.put(a.left, a.right);
            }
        }

        simplifiedP.instructions.removeAll(instructionsToRemove);
        instructionsToRemove.clear();

        for(int i = 0; i < simplifiedP.instructions.size(); i++) {
            Instruction ins = simplifiedP.instructions.get(i);
            boolean remove = true;
            Variable x = new Variable("x");
            // If it's not x we don't care
            boolean isLastX = ins.left.equals(x);
            for(int j = i+1; j < simplifiedP.instructions.size(); j++) {
                Instruction jns = simplifiedP.instructions.get(j);
                // If left ins variable is used, it isn't meant to be removed
                if(jns.contains(ins.left)) {
                    System.out.println(ins);
                    remove = false;
                }
                // If we find another x
                if(jns.left.equals(x)) {
                    isLastX = false;
                }
                // Redefinition of value without use
                if(ins instanceof Assign) {
                    if(remove && ins.left.equals(jns.left)) {
                        break;
                    }
                }
            }
            if (remove && !isLastX) {
                instructionsToRemove.add(ins);
            }
        }
        simplifiedP.instructions.removeAll(instructionsToRemove);
    }

    private void removeKnownValues() {
        Vector<Instruction> instructions = simplifiedP.instructions;
        Map<Variable, Integer> tempValues = new HashMap<>();
        for(int i = 0; i < instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if(ins instanceof AssignOperator op) {
                // x = 0, y = 0
                if(tempValues.getOrDefault(op.t0, -1).equals(tempValues.getOrDefault(op.t1, -2)) && tempValues.getOrDefault(op.t0, -1).equals(0)) {
                    instructions.set(i, new Assign(op.left, new Entier(0)));
                    continue;
                }
                // 0 * x or 0 + x
                if((op.t0.equals(new Entier(0)) || tempValues.getOrDefault(op.t0, -1).equals(0)) && (op.op.equals("+") || op.op.equals("*"))) {
                    if(op.op.equals("+"))
                        instructions.set(i, new Assign(op.left, op.t1));
                    if(op.op.equals("*"))
                        instructions.set(i, new Assign(op.left, new Entier(0)));
                    continue;
                }
                // x * 0 or x + 0 or x - 0
                if(op.t1.equals(new Entier(0)) || tempValues.getOrDefault(op.t1, -1).equals(0)) {
                    if(op.op.equals("+") || op.op.equals("-"))
                        instructions.set(i, new Assign(op.left, op.t0));
                    if(op.op.equals("*"))
                        instructions.set(i, new Assign(op.left, new Entier(0)));
                    continue;
                }
                // 1 * x
                if((op.t0.equals(new Entier(1)) || tempValues.getOrDefault(op.t0, -1).equals(1)) && op.op.equals("*")) {
                    instructions.set(i, new Assign(op.left, op.t1));
                }
                // x * 1
                if((op.t1.equals(new Entier(1)) || tempValues.getOrDefault(op.t1, -1).equals(1)) && op.op.equals("*")) {
                    instructions.set(i, new Assign(op.left, op.t0));
                }
                // x - x
                if((op.t0.equals(op.t1) || tempValues.getOrDefault(op.t0, -1).equals(tempValues.getOrDefault(op.t1, -2))) && op.op.equals("-")){
                    instructions.set(i, new Assign(op.left, new Entier(0)));
                }
            }
            // Mise à jour des values temporaires
            if(instructions.get(i) instanceof Assign a){
                if(a.right.equals(new Entier(0)) || a.right.equals(new Entier(1))) {
                    if (a.right.equals(new Entier(0))) {
                        tempValues.put(a.left, 0);
                    }
                    if (a.right.equals(new Entier(1))) {
                        tempValues.put(a.left, 1);
                    }
                } else {
                    int rightVal = tempValues.getOrDefault(a.right, -1);
                    if(a.right instanceof Entier || (rightVal != 0 && rightVal != 1)){
                        tempValues.remove(a.left);
                    }
                }
            }
        }
    }
}
