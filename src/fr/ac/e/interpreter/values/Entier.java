package fr.ac.e.interpreter.values;

import fr.ac.e.interpreter.values.Value;

public class Entier implements Value
{
    int x;
    public Entier(int s){
        x = s;
    }

    @Override
    public int evaluate() {
        return x;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj instanceof Entier e) {
            return e.x == this.x;
        }
        return false;
    }

    @Override
    public String toString(){
        return "" + x;
    }
}
