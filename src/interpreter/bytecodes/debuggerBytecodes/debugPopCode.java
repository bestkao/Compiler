/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerBytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.PopCode;
import interpreter.debugger.DebuggerVM;

/**
 * Pops the most recent variable off the function environment.
 * @author James
 */
public class debugPopCode extends PopCode {

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVM dvm = (DebuggerVM)vm;
        super.execute(vm);
        dvm.envRecPop(getNumPop());
    }
    
}
