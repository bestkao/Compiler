/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerBytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DebuggerVM;
import java.util.Vector;

/**
 * Executes the original actions of FormalCode and then updates the variables of
 * the current Function Environment Record with the correct offsets of its values.
 * @author James
 */
public class FormalCode extends ByteCode {

    String formal;
    int value;
    
    @Override
    public void init(Vector<String> args){
        formal = args.firstElement();
        value = Integer.parseInt(args.lastElement());
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVM dvm = (DebuggerVM)vm;
        dvm.envRecPeek().put(formal,value);        
    }
    
}
