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
public class ReturnCode extends ByteCode {

    String funcname = "";
    int topValue;
    
    @Override
    public void init(Vector<String> args) {
        if(!args.isEmpty()) {
            funcname = args.firstElement();
        }
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.vmPopFrame();
        vm.setPC(vm.popRetAddrs());
        topValue = vm.vmPeek();
    }
    
    @Override
    public String toString() {
        return "RETURN " + funcname;
    }

}
