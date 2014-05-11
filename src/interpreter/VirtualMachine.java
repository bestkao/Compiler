/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.DumpCode;
import interpreter.bytecodes.ByteCode;
import java.util.*;

/**
 *
 * @author James
 */
public class VirtualMachine {
    
    protected int pc = 0;
    protected boolean isRunning;
    protected boolean dump = false;
    protected Stack<Integer> returnAddrs;
    protected Program program;
    protected RunTimeStack runStack;
    
    public VirtualMachine(Program prog) {
        program = prog;
    }
    
    public void executeProgram() {
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (dump && !(code instanceof DumpCode)) {
                System.out.println(code.toString());
                runStack.dump();
            }
            pc++;
        }
    }
    
    public void setPC(int target) {
        pc = target;
    }
    
    public int getPC() {
        return pc;
    }
    
    public void setRunning(boolean running) {
        isRunning = running;
    }
    
    public boolean getRunning() {
        return isRunning;
    }
    
    public void setDumpOn() {
        dump = true;
    }
    
    public void setDumpOff() {
        dump = false;
    }
    
    public int popRetAddrs() {
        return returnAddrs.pop();
    }
    
    public void pushRetAddrs(int addrs) {
        returnAddrs.push(addrs);
    }
    
    public int vmPeek() {
        return runStack.peek();
    }
    
    public int vmPop() {
        return runStack.pop();
    }
    
    public void vmPush(int n) {
         runStack.push(n);
    }
    
    public Integer vmPush(Integer i) {
        return runStack.push(i);
    }
    
    public void vmFrameAt(int offset) {
        runStack.newFrameAt(offset);
    }
    
    public void vmPopFrame() {
        runStack.popFrame();
    }
    
    public int vmPeekFrame() {
        return runStack.peekFrame();
    }
    
    public int vmStore(int offset) {
        return runStack.store(offset);
    }
    
    public int vmLoad(int offset) {
        return runStack.load(offset);
    }
    
    public int getRunStackSize() {
        return runStack.getRunStackSize();
    }
    
    public RunTimeStack newRunTimeStack() {
       RunTimeStack runTimeStack = new RunTimeStack();
       return runTimeStack;
    }
    
    public Stack newReturnAddrsStack() {
        Stack returnStack = new Stack();
        return returnStack;
    }
    
}
