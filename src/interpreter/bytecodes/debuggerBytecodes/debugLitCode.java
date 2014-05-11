/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerBytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.LitCode;
import interpreter.debugger.DebuggerVM;

/**
 * Puts new variables into the current function environment.
 * @author James
 */
public class debugLitCode extends LitCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVM dvm = (DebuggerVM) vm;
        super.execute(vm);
        int offset = (vm.getRunStackSize() - vm.vmPeekFrame()) - 1;
        if(!getID().equals("")) {
            dvm.envRecPeek().put(getID(), offset);
        }        
    }
    
}
