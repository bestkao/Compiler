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
public class ArgsCode extends ByteCode {
    
    int n;
    
    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.firstElement());
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.vmFrameAt(n);
    }
    
    @Override
    public String toString() {
        return "ARGS " + n;
    }
    
}
