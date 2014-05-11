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
public class BopCode extends ByteCode {
    
    String binaryOP = "";
    
    @Override
    public void init(Vector<String> args) {
        binaryOP = (String)args.firstElement();
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        
        if(binaryOP.equals("+")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int sum = temp2 + temp1;
            vm.vmPush(sum);
        } else if(binaryOP.equals("-")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int dif = temp2 - temp1;
            vm.vmPush(dif);
        } else if(binaryOP.equals("*")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int product = temp2 * temp1;
            vm.vmPush(product);
        } else if(binaryOP.equals("/")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int div = temp2/temp1;
            vm.vmPush(div);
        } else if(binaryOP.equals("==")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int equal = 0;
            if(temp2 == temp1) {
                equal = 1;
            }
            vm.vmPush(equal);
        } else if(binaryOP.equals("!=")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int notEqual = 0;
            if(temp2 != temp1){
                notEqual = 1;
            }
            vm.vmPush(notEqual);
        } else if(binaryOP.equals("<")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int lessThan = 0;
            if(temp2 < temp1){
                lessThan = 1;
            }
            vm.vmPush(lessThan);
        } else if(binaryOP.equals("<=")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int lessThanEqual = 0;
            if(temp2 <= temp1) {
                lessThanEqual = 1;
            }
            vm.vmPush(lessThanEqual);
        } else if(binaryOP.equals(">")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int greaterThan = 0;
            if(temp2 > temp1){
                greaterThan = 1;
            }
            vm.vmPush(greaterThan);
        } else if(binaryOP.equals(">=")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int greaterThanEqual = 0;
            if(temp2 >= temp1){
                greaterThanEqual = 1;
            }
            vm.vmPush(greaterThanEqual);
        } else if(binaryOP.equals("|")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int or = 0;
            if(temp2 == 1 || temp1 == 1) {
                or = 1;
            }
            vm.vmPush(or);
        } else if(binaryOP.equals("&")) {
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int and = 0;
            if(temp2 == 1 && temp1 == 1) {
                and = 1;
            }
            vm.vmPush(and);
        }

    }
    
    @Override
    public String toString() {
        return "BOP " + binaryOP;
    }
    
}
