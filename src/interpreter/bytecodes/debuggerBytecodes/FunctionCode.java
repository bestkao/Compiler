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
 * Updates the current function record with a functionName, startLine, and endLine.
 * @author James
 */
public class FunctionCode extends ByteCode {
    
    int startLine;
    int endLine;
    String functionName;
    
    @Override
    public void init(Vector<String> args) {
            functionName = args.firstElement();
            int index = functionName.indexOf("<");
            if (index != -1) {
                functionName = functionName.substring(0, index);
            }
            startLine = Integer.parseInt(args.get(1));
            endLine = Integer.parseInt(args.get(2));
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        
        DebuggerVM dvm = (DebuggerVM) vm;
        dvm.envRecPeek().setEverything(functionName, startLine, endLine);
        String temp = "";
        if(dvm.trace != null) {
            for(int i = 1; i < dvm.envRecSize(); i++) {
                temp += " ";
            }
            ByteCode bc = dvm.returnProgram().getCode(dvm.getPC() + 1);
            if (bc instanceof FormalCode) {
                temp = temp + functionName + " (";
                temp = temp + dvm.vmPeek();
                temp = temp + ")";
                dvm.setTraceString(temp);
            } else {
                temp = temp + functionName + "()";
                dvm.setTraceString(temp);
            }
        }
        if(dvm.stepIn != null){
            if(dvm.environmentStack.peek().getStart() == -1) {
                if(dvm.environmentStack.peek().getName().equals("Read")) {
                    System.out.println("***READING***");
                } else if(dvm.environmentStack.peek().getName().equals("Write")) {
                    System.out.println("***WRITING***");
                }
            }
            int pc = dvm.getPC();
            pc++;
            ByteCode bc = dvm.returnProgram().getCode(pc);
            while(bc instanceof FormalCode) {
                bc.execute(dvm);
                pc++;
                bc = dvm.returnProgram().getCode(pc);
            }
            dvm.stepIn = null;
            dvm.setRunning(false);
            pc--;
            dvm.setPC(pc);
        }

    }
    
}
