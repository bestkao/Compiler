/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

/**
 * Creates a StepIn object flag.
 * @author James
 */
public class StepIn {
    
    int stepSize;
    
    public int getStepSize() {
        return stepSize; 
    }
    
    public StepIn(int size) {
        stepSize = size;
    }

}

