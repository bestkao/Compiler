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
public class LabelCode extends ByteCode {
    
    String label;
    
    @Override
    public void init(Vector<String> args) {
        label = (String)args.firstElement();
    }
    
    @Override
    public void execute(VirtualMachine vm) {}
    
    @Override
    public String toString() {
        return "LABEL " + label;
    }
    
    public String getLabel() {
        return label;
    }
    
}
