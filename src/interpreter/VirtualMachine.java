package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.Stack;

/**
 * Executes each byte code that is loaded into the program. It keeps track of
 * the current position in the program; also, it holds a reference to the
 * runtime stack.
 * @author James Kao
 */
public class VirtualMachine {
    
    RunTimeStack runStack;
    int pc; // The program counter
    Stack returnAddrs; // Push/pop when call/return functions
    boolean isRunning; // True while the VM is running
    Program program; // The bytecode program
    
    public VirtualMachine() {
        
    }
    
    public int popRunStack() {
        return runStack.pop();
    }
    
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            // runStack.dump(); // Check that the operation is correct
            pc++;
        }
    }
    
    // hold the ByteCode instances
    // created by ByteCodeLoader
    // reader: Program program = bcl.loadCodes()

    // walks through bytecod program (ByteCodeLoader)
    // resolves symbolic addresses
    //   "GOTO addr" has symbolic address (addr) resolved
    //   to a 'numeric bytecode address of the target'
    //     ex: "GOTO 3" (if addr resolves to 3)
}