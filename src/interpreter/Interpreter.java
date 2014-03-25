package interpreter;

import java.io.*;

/**
 * <pre>
 * 
 *  refer to p. 127-147 of the course reader
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

	public Interpreter(String codeFile) {
		try {
			CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}

	void run() {
		Program program = bcl.loadCodes();
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}

	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		}
		(new Interpreter(args[0])).run();
	}
}