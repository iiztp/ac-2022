package fr.ac.e.utils;

import java.io.*;

public class Utils
{
    public static String FileToString(String filename) {
        StringBuilder fileString = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            for(String line = br.readLine(); line != null; line = br.readLine())
                fileString.append(line).append("\n");
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileString.toString();
    }
}
