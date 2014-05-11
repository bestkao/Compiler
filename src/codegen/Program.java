package codegen;

import java.util.*;
import java.io.*;

/**
 * This class will hold the generated program bytecodes
*/
public class Program {
    private ArrayList<Code> program = new ArrayList<Code>();

/**
 *  store the new bytecode in the program vector
 *  @param code is the bytecode to store
*/
    public void storeop(Code code) {
        program.add(code);
    }

/**
 *  print all of the bytecodes that have been generated
 *  @param outFile a String indicating where to print the bytecodes
*/
    public void printCodes(String outFile) {
        PrintWriter out = null;
        try {
             out = new PrintWriter(new FileOutputStream(outFile));
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(1);
        }

        for (Code nextCode : program) {
           	System.out.println(nextCode.toString());
            out.println(nextCode.toString());
        }
        out.close();
    }
}