/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import java.util.*;
import java.io.*;
import interpreter.*;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.ui.*;

/**
 *
 * @author James
 */
public class DebuggerVM extends VirtualMachine {
    
    public Stack<FunctionEnvironmentRecord> environmentStack;
    protected Vector<SourceLines> breakPoints;
    protected ArrayList<String> sourceCode;
    public ArrayList okayLine;
    boolean pause;
    boolean dvmIsRunning;
    private String tracePrint;
    protected BufferedReader sourceCodeReader;
    Scanner scanner = new Scanner(System.in);
    String userInput;
    public StepOut stepOut;
    public StepIn stepIn;
    public StepOver stepOver;
    public Trace trace;

    public DebuggerVM(Program debugprogram, String sourceFile) throws IOException {
        super(debugprogram);
        environmentStack = new Stack();
        breakPoints = new Vector();
        sourceCode = new ArrayList();
        okayLine = new ArrayList();
        this.runStack = newRunTimeStack();
        this.returnAddrs = this.newReturnAddrsStack();
        dvmIsRunning = true;
        pause = false;
        environmentStack.push(new FunctionEnvironmentRecord());
        sourceCodeReader = new BufferedReader(new FileReader(sourceFile));
        String readLine = sourceCodeReader.readLine();
        while(readLine != null) {
            sourceCode.add(readLine);
            readLine = sourceCodeReader.readLine();
            breakPoints.add(new SourceLines(sourceCode.get(sourceCode.size() - 1), false));
        }
    }
    
    @Override
    public void executeProgram() {    
        while(this.getRunning())
        {
            if(pause) {
                setIsRunning(false);
            } else {
                ByteCode code = this.returnProgram().getCode(getPC());
                code.execute(this);
                this.setPC(getPC()+1);
                if(tracePrint != null) {
                    setRunning(false);
                }
                if(getPC() == this.returnProgram().codeListSize()) {
                    System.out.println("Debugging finished");
                    this.setPC(0);
                    if(trace != null) {
                        trace.setDoneTrace(true);
                    }
                    this.environmentStack = new Stack();
                    environmentStack.push(new FunctionEnvironmentRecord());
                }
            }
        }
    }
    
    public void lineCanBreak(int setBreak) {
        if(setBreak >= 0) {
            okayLine.add(setBreak);
        }
    }
    
    public boolean canBreak(int line){
        boolean breaking = false;
            if(okayLine.contains(line)) {
                breaking = true;
            }
        return breaking;
    }
    
    public String getSourceString(int lineNum) {
        return sourceCode.get(lineNum);
    }
    
    public int getSourceCodeSize() {
        return sourceCode.size();
    }
    
    public int envRecSize() {
        return environmentStack.size();
    }

    public FunctionEnvironmentRecord envRecPeek() {
        return environmentStack.peek();
    }
    
    public void envRecPop(int pop) {
        environmentStack.peek().popScope(pop);
    }
    
    public void envRecPush(FunctionEnvironmentRecord funcEnvRec) {
        environmentStack.push(funcEnvRec);
    }
    
    public void envRecEndFrame(){
        environmentStack.pop();
    }
    
    public void envRecVariables(){
        System.out.println();
        FunctionEnvironmentRecord variables = environmentStack.peek();
        Set<String> s = variables.getVariables().keys();
        for(String key : s) {
            System.out.print(key + "=");
            int offset = (Integer)variables.getBinder().get(key)+ vmPeekFrame();
            System.out.println(runStack.getValue(offset));
        }
        System.out.println();
    }
        
    public Program returnProgram() {
        return program;
    }
    
    public void setPause(boolean isPaused) {
        pause = isPaused;
    }
    
    public void setIsRunning(boolean dvmRunning) {
        dvmIsRunning = dvmRunning;
    }

    public int getCurrentLine(){
        int currentLine;
        FunctionEnvironmentRecord funcEnvRec;
        funcEnvRec = environmentStack.pop();
        currentLine = environmentStack.peek().getCurrentLine();
        environmentStack.push(funcEnvRec);
        return currentLine;
    }

    public void setBreakPoint(int lineNum, boolean lineBreak) {
        breakPoints.get(lineNum - 1).setBreakPt(lineBreak);
    }
    
    public void resetBreakPoints() {
        breakPoints.clear();
        for(int i = 0; i < sourceCode.size(); i++){
            breakPoints.add(new SourceLines(sourceCode.get(sourceCode.size() - 1), false));
        }
    }
    
    public StepOut getStep() {
        return stepOut;
    }

    public String getTraceString() {
        return tracePrint;
    }

    public void setTraceString(String setTrace) {
        tracePrint = setTrace;
    }
    
    public void resetTrace() {
        tracePrint = null;
    }
    
    public String getCallStack() {
        String callStack = "";
        ListIterator listIterator = environmentStack.listIterator(environmentStack.size());
        while(listIterator.hasPrevious()) {
            FunctionEnvironmentRecord funcEnvRec = (FunctionEnvironmentRecord)listIterator.previous();
            callStack += funcEnvRec.getName() + ":" + funcEnvRec.getCurrentLine()+"\n";
        }
        return callStack;
    }
    
}
