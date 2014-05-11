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
public class WriteCode extends ByteCode {
    
    @Override
    public void init(Vector<String> args) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        int n = vm.vmPeek();
        System.out.println(n);
    }
    
    @Override
    public String toString() {
        return "WRITE";
    }

}
