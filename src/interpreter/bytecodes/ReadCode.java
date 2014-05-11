/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/**
 *
 * @author James
 */
public class ReadCode extends ByteCode {
    
    Scanner scanner = new Scanner(System.in);
    
    @Override
    public void init(Vector<String> args) {}
    
    @Override
    public void execute(VirtualMachine vm) {
        System.out.print("Enter a number: ");
        int userInputer = scanner.nextInt();
        vm.vmPush(userInputer);
    }
    
    @Override
    public String toString() {
        return "READ";
    }

}
