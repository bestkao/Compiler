/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

/**
 * Creates a StepOver object flag.
 * @author James
 */
public class StepOver {
    
    int stepSize;
    
    public int getStepSize() {
        return stepSize; 
    }
    
    public StepOver(int size) {
        stepSize = size;
    }
    
}

