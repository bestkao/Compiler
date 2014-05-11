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
public class PopCode extends ByteCode {
    
    int n;
    
    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.firstElement());
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        for(int i = 0; i < n; i++) {
            vm.vmPop();
        }
    }
    
    @Override
    public String toString(){
        return "POP " + n;
    }

    public int getNumPop() {
        return n;
    }
    
}
