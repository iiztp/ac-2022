package fr.ac.e.interpreter;

import fr.ac.e.interpreter.instructions.Assign;
import fr.ac.e.interpreter.instructions.AssignOperator;
import fr.ac.e.interpreter.instructions.Instruction;
import fr.ac.e.interpreter.values.Entier;
import fr.ac.e.interpreter.values.Value;
import fr.ac.e.interpreter.values.Variable;

import java.util.Vector;
import java.util.regex.Pattern;

public class Parser {
    public static Vector<Instruction> parse(String fileString) {
        String[] instructions = fileString.replace(" ", "").split("[\n;]");
        Vector<Instruction> vInstruction = new Vector<>();
        for(String i : instructions) {
            if(i.startsWith("//")) continue; // Comments
            String[] split = i.split("=");
            String left = split[0];
            String right = split[1];

            if(right.contains("*") || right.contains("+") || right.contains("-")) {
                String operator = "";
                if (right.contains("*")) operator = "*";
                if (right.contains("+")) operator = "+";
                if (right.contains("-")) operator = "-";
                String[] terms = right.split(Pattern.quote(operator));
                Value t0, t1;
                if(terms[0].matches("[a-z]*")) t0 = new Variable(terms[0]); else t0 = new Entier(Integer.parseInt(terms[0]));
                if (terms[1].matches("[a-z]*"))  t1 = new Variable(terms[1]); else t1 = new Entier(Integer.parseInt(terms[1]));
                Instruction t = new AssignOperator(left,operator, t0, t1);
                vInstruction.add(t);
            } else {
                Value t0;
                if(right.matches("[a-z]*")) t0 = new Variable(right); else t0 = new Entier(Integer.parseInt(right));
                Instruction t = new Assign(left, t0);
                vInstruction.add(t);
            }
        }
        return vInstruction;
    }
}
