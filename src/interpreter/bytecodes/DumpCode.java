/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author James
 */
public class DumpCode extends ByteCode {
    
    String label;
    
    @Override
    public void init(Vector<String> args) {
        label = (String)args.firstElement();
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        if(label.equals("ON")) {
            vm.setDumpOn();
        } else {
            vm.setDumpOff();
        }
    }
    
    @Override
    public String toString() {        
        return "DUMP " + label;
    }
    
    public String getLabel() {
        return label;
    }
    
}
