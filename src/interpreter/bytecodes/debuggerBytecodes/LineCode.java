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
 * Updates the current function with it's current line.
 * @author James
 */
public class LineCode extends ByteCode {

    int lineNum;

    @Override
    public void init(Vector<String> arg) {
        lineNum = Integer.parseInt(arg.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {

        DebuggerVM dvm = (DebuggerVM)vm;
        dvm.environmentStack.peek().setCurrentLine(lineNum);
        if(dvm.okayLine.contains(lineNum)) {
            if(lineNum >0) {
                dvm.environmentStack.peek().setCurrentLine(lineNum);
            }
            dvm.setPause(true);
            vm.setRunning(false);
            dvm.stepIn = null;
            dvm.stepOver = null;
        }
        if(dvm.stepIn != null) {
            ByteCode bc = dvm.returnProgram().getCode(dvm.getPC() + 1);
            if (bc instanceof FunctionCode) {
                dvm.setPC(dvm.getPC() + 1);
                bc.execute(dvm);
            }
            dvm.setRunning(false);
        }
        if (dvm.stepOver != null) {
            if (dvm.envRecSize() == dvm.stepOver.getStepSize()) {
                dvm.stepOver = null;
                dvm.setRunning(false);
                System.out.println("Step Over Complete");
            }
        }
        
    }

}
