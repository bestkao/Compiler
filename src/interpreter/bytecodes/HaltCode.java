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
public class HaltCode extends ByteCode {
    
    @Override
    public void init(Vector<String> args) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.setRunning(false);
    }
    
    @Override
    public String toString() {
        return "HALT";
    }
    
}
