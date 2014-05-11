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
public class LoadCode extends ByteCode{
    
    int n;
    String id;
    
    @Override
    public void init(Vector<String> args) {
        if(args.size() == 1) {
            n = Integer.parseInt(args.firstElement());
        } else {
            id = args.get(args.size() - 1);
            n = Integer.parseInt(args.firstElement());
        }
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.vmLoad(n);
    }
    
    @Override
    public String toString(){
        return "LOAD " + n + " " + id;
    }

}
