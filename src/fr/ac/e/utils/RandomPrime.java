package fr.ac.e.utils;

import java.math.BigInteger;
import java.util.Random;

public class RandomPrime {
    public static int randomPrime() {
        Random r = new Random();
        return BigInteger.valueOf(r.nextInt(1073741823)).nextProbablePrime().intValue();
    }
}
