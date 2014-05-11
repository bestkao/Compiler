/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

/**
 * Objects to record source code and if they have breakpoints.
 * @author James
 */
public class SourceLines {
    
    private String breakLine;
    private Boolean isBreakptSet;
    
    public SourceLines(String sourceLine, Boolean isBreakptSet){
        breakLine = sourceLine;
        this.isBreakptSet = isBreakptSet;
    }
    
    public void setBreakPt(Boolean breakPt){
        isBreakptSet = breakPt;
    }
    
    public Boolean getBreakPt() {
        return isBreakptSet;
    }
    
}
