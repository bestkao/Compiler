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
public class LitCode extends ByteCode {
    
    int n;
    String id = "";
    
    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.firstElement());
        if(args.size() > 1){
            id = args.get(args.size() - 1);
        }
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        vm.vmPush(n);
    }
    
    @Override
    public String toString() {
        return "LIT " + n + " " + id;
    }

    public int getNum() {
        return n;
    }
    
    public String getID() {
        return id;
    }
    
}
