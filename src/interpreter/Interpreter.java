package interpreter;

import interpreter.debugger.Debugger;
import java.io.*;

/**
 * <pre>
 * 
 *  
 *   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *     
 *   
 *  
 * </pre>
 */
public class Interpreter {

    ByteCodeLoader bcl;
    
    public Program getProgram() throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, IOException {
        Program debugProgram = bcl.loadCodes(true);
        return debugProgram;
    }

    public Interpreter(String codeFile) {
        try {
            //CodeTable.init();
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
	    System.out.println("**** " + e);
	}
    }

    void run() throws ClassNotFoundException, InstantiationException, 
                      IllegalAccessException, IllegalAccessException,
                      IOException, IOException {
        
        Program program = bcl.loadCodes(false);
        VirtualMachine vm = new VirtualMachine(program);
	vm.executeProgram();
        
    }

    public static void main(String args[]) throws ClassNotFoundException, 
            ClassNotFoundException, InstantiationException, 
            InstantiationException, IllegalAccessException, 
            IllegalAccessException, IOException, IOException {
                
        if (args.length == 1) {
            (new Interpreter(args[0])).run();
        } else if (args.length == 2 && args[0].equals("-d")) {
            (new Debugger(args[1] + ".x.cod", args[1] + ".x")).runDebugger();
        } else {
            System.out.println("***Incorrect usage, try: java -jar Interpreter <filename>");
            System.out.println("***              Or try: java -jar Interpreter -d <filename no extension>");
        }

    }
    
}
