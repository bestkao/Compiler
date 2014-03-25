package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public abstract class ByteCode {
    protected Vector<String> kids;
    protected int nodeNum;
    protected ByteCode decoration;
    protected String label = "";
    
    static int NodeCount = 0;
    
    public ByteCode() {
        kids = new Vector<String>();
        NodeCount++;
        nodeNum = NodeCount;
    }
    // no instance variables related to args
    public void init(Vector codes) {
        // only subclasses can record args to instance variables
        // parse each line to a vector
    }
    
    public void execute(VirtualMachine VM) {
        int temp;
        temp = VM.popRunStack();
    }
}


/*


// all ByteCode's subclasses
WRITE() extends ByteCode {
    init(String *args) {
        // record args to instance variables
        // parse each line to a vector
    }
    execute() {
        // output interspersed with output from dumping
        // print 1 number per line
    }
}


Q. How should the Dump bytecode be implemented? 
Dumping is set to OFF by default. We need a method to set the dumping state to ON or OFF when we see DumpCode. 
The check if dumping is ON so that we print out debugger info will be done in another method. 


Q. When a Return bytecode is executed, should the program counter jump to the address where the label is?
The RETURN label is just a comment. We shouldn't jump to the address where the label is. We should jump back to the return address which we saved before we executed the CALL <<function>>. The label in RETURN bytecode just tells us we have finished the current function. 


Q. Is there a difference between RETURN and RETURN <<label>>?
Since we are saving return address on the stack it doesn't really matter if you have function name after RETURN bytecode or not. The program ignores the argument for the RETURN bytecode and just pops return address from return address stack. The only difference lies in how the RETURN bytecode is dumped. 


Q. Does the Args bytecode set up a new frame?
Yes. ARGS should set up the new frame - e.g. if prior to ARGS 1 the stack contains 0003 then after executing ARGS 1 the stack should look like 000 3. 


*/