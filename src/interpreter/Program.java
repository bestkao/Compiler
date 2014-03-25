package interpreter;

import interpreter.bytecode.ByteCode;

/**
 * Holds the bytecode objects and has a method to resolve addresses for branch
 * instructions.
 * @author James Kao
 */
public class Program extends Object {
    
    public Program() {
        
    }
    
    public ByteCode getCode(int i) {
        return new ByteCode();
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