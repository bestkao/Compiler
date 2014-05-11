/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.*;


/**
 *
 * @author James
 */
public class RunTimeStack {
    
    private final Stack<Integer> framePointers;
    private final Vector<Integer> runStack;
    
    public RunTimeStack() {
        framePointers = new Stack<Integer>();
        runStack = new Vector<Integer>();
        framePointers.push(0);
    }
    
    public void dump() {
        Iterator iterator = framePointers.iterator();
        int frameCount = (Integer)iterator.next();
        
        if(iterator.hasNext()) {
            frameCount = (Integer)iterator.next();
        }
        
        System.out.print("[");
        if(!runStack.isEmpty()) {
            System.out.print(runStack.get(0));
        }
        
        for(int i = 1; i < runStack.size(); i++) {
            if(i == frameCount) {
                System.out.print("] [" + runStack.get(i));
                if(iterator.hasNext()){
                    frameCount = (Integer)iterator.next();
                }
            }else
                System.out.print("," + runStack.get(i));
        }
        System.out.println("]");
    }
    
    public int getValue(int n) {
        return runStack.get(n);
    }
    
    public int peek() {
        return runStack.lastElement();
    }
    
    public int peekFrame() {
        return framePointers.peek();
    }
    
    public int pop() {
        int pop = runStack.lastElement();
        runStack.remove(runStack.size() - 1);
        return pop;
    }
    
    public int push(int i) {
        runStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset) {
        framePointers.push(runStack.size() - offset);
    }
    
    public void popFrame() {
        int returnValue = runStack.lastElement();
        while(runStack.size() != framePointers.peek()) {
            runStack.remove(runStack.size() - 1 );
        }
        framePointers.pop();
        runStack.add(returnValue);
    }
    
    public int store(int offset) {
        int storeValue = runStack.get(runStack.size() - 1);
        runStack.remove(runStack.size() - 1);
        runStack.set(framePointers.peek() + offset, storeValue);
        return storeValue;
    }
    
    public int load(int offset) {
        int loadValue = runStack.get(framePointers.peek() + offset);
        runStack.add(loadValue);
        return loadValue;
    }
    
    public Integer push(Integer i) {
        runStack.add(i);
        return i;
    }
    
    public int getRunStackSize() {
        return runStack.size();
    }
    
}
