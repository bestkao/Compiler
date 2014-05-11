/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerBytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.ReturnCode;
import interpreter.debugger.DebuggerVM;

/**
 * Pops the current function environment off the function stack.
 * @author James
 */
public class debugReturnCode extends ReturnCode {
    
    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVM dvm = (DebuggerVM)vm;
        String temp = "";
        if(dvm.trace != null){
            for(int i = 1 ; i < dvm.envRecSize(); i++) {
                System.out.print(" ");
            }
            temp = temp + "exit: " + dvm.envRecPeek().getName() + ": ";
        }
        super.execute(vm);
        dvm.envRecEndFrame();
        if(dvm.trace != null) {
            temp = temp + dvm.vmPeek();
            dvm.setTraceString(temp);
        }
        if (dvm.stepOut != null) {
            if (dvm.envRecSize() < dvm.stepOut.getStepSize()) {
                dvm.stepOut = null;
                dvm.setRunning(false);
                ByteCode bc = dvm.returnProgram().getCode(dvm.getPC() + 1);
                if (bc instanceof FunctionCode) {
                    bc.execute(dvm);
                }
            }
        }
    }
    
}
