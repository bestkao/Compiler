/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerBytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.CallCode;
import interpreter.debugger.DebuggerVM;
import interpreter.debugger.FunctionEnvironmentRecord;

/**
 * Pushes a new Function Environment Record onto the function stack.
 * @author James
 */
public class debugCallCode extends CallCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVM dvm = (DebuggerVM) vm;
        super.execute(vm);
        dvm.envRecPush(new FunctionEnvironmentRecord());
    }
    
}
