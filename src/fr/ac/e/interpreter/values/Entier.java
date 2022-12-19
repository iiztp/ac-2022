package fr.ac.e.interpreter.values;

import fr.ac.e.interpreter.values.Value;

public class Entier implements Value
{
    int x;
    public Entier(int s){
        x = s;
    }

    @Override
    public String toString(){
        return "Entier " +  x;
    }

    @Override
    public int evaluate() {
        return x;
    }
}
