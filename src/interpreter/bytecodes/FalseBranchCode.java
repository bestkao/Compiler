/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *
 * @author James
 */
public class FalseBranchCode extends ByteCode {
    
    String label = "";
    int targetAddress;
    
    @Override
    public void init(Vector<String> args) {
        label = (String)args.firstElement();
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        int branch = vm.vmPop();
        if(branch == 0) {
            vm.setPC(targetAddress);
        }
    }
    
    @Override
    public String toString() {
        return "FALSE " + label;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String newLabel) {
        label = newLabel;
    }
    
    public int getTargetAddress() {
        return targetAddress;
    }
    
    public void setTargetAddress(int newAddress) {
        targetAddress = newAddress;
    }
    
}
