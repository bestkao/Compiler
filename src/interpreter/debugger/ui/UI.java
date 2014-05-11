/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

import interpreter.debugger.DebuggerVM;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author James
 */
public class UI {

    int breakAt;
    boolean breakMore;
    ArrayList breakPoint;
    DebuggerVM dvm;
    Scanner scanner;
    String userInput;
    
    public UI(DebuggerVM newdvm) throws FileNotFoundException, IOException{
        this.dvm = newdvm;
        scanner = new Scanner(System.in);
        breakPoint = new ArrayList();
    }

    public void printSourcefile() {
        System.out.println("\nSource:\n");
        for(int i = 0; i < dvm.getSourceCodeSize(); i++) {
            if(breakPoint.contains(i + 1)) {
                System.out.printf("*%2d." + dvm.getSourceString(i), (i+1));
                if(dvm.environmentStack.peek().getCurrentLine() == (i + 1)) {
                    System.out.print("<=======");
                } else if(dvm.environmentStack.peek().getCurrentLine() < 0 &&
                          dvm.getCurrentLine() == (i + 1)) {
                    System.out.print("<=======");
                }
                System.out.println();
            } else {
                System.out.printf("%3d.  " + dvm.getSourceString(i),(i + 1));
                if(dvm.environmentStack.peek().getCurrentLine() == (i + 1)) {
                    System.out.print("<=======");
                } else if(dvm.environmentStack.peek().getCurrentLine() < 0 &&
                          dvm.getCurrentLine() == (i + 1)) {
                    System.out.print("<=======");
                }
                System.out.println();
            }
        }
    }    
    
    public void helpMenu() {
        while (true) {
            System.out.println();
            System.out.println("Type ? for help");
            System.out.print(">");
            userInput = scanner.next();
            if (userInput.equals("?")) {
                System.out.print("\n" +
                    "[break]  Set/Clear breakpoints\n" +
                    "[fct]    Display the current function\n" +
                    "[cont]   Continue execution\n" +
                    "[var]    Display variables\n" +
                    "[stpout] Step out of current activation of a function\n" +
                    "[stpovr] Step over a line\n" +
                    "[stpin]  Step into a function\n" +
                    "[list]   List breakpoints\n" +
                    "[call]   Print Call Stack\n" +
                    "[trace]  Set function tracing\n" +
                    "[src]    Display source code (with breakpoints)\n" +
                    "[q]      Quit\n\n");
                    /*                      


                    */
                        
                System.out.print(">");
                userInput = scanner.next();
            }
            if (userInput.equals("break")) {
                setBreakPoint();
            } else if (userInput.equals("fct")) {
                displayFunction();
            } else if (userInput.equals("cont")) {
                continueEx();
                printSourcefile();
                break;
            } else if (userInput.equals("var")) {
                displayVariables();
            } else if (userInput.equals("stpout")) {
                stepOut();
                printSourcefile();
            } else if (userInput.equals("stpovr")) {
                stepOver();
                printSourcefile();
            } else if (userInput.equals("stpin")) {
                stepIn();
                printSourcefile();
            } else if (userInput.equals("list")) {
                printBreakPoints();
            } else if (userInput.equals("call")) {
                printCallStack();
            } else if (userInput.equals("trace")) {
                trace();
            }else if (userInput.equals("src")) {
                printSourcefile();
            } else if (userInput.equals("q")) {
                quit();
            } else {
                System.out.println("\nInvalid command, try again.\n");
            }
            /*
            
            */
        }
    }

    public void setBreakPoint() {
        
        System.out.println("\nEnter line number(s) that you wish to add/clear a breakpoint(s)");
        System.out.println("Enter 0 to exit");
        System.out.println("Enter -1 to clear all break points\n");
        System.out.print(">");
        
        breakMore = true;
        
        try {
            breakAt = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Sorry that's not a line, friend. Exiting breakpoint menu.\n");
        }
        
        while(breakMore) {
            if(breakAt == 0) {
                break;
            } else if(breakAt == -1) {
                dvm.resetBreakPoints();
                breakPoint.clear();
                dvm.okayLine.clear();
                System.out.println("All breakpoints cleared.");
            }
            if(breakAt > dvm.getSourceCodeSize() || breakAt < -1) {
                System.out.println("Sorry buddy. That line does not exist.");
            } else if(breakAt > 0) {
                if(breakPoint.contains(breakAt)) {
                    dvm.setBreakPoint(breakAt, false);
                    for(int i = 0; i < breakPoint.size(); i++) {
                        if(breakPoint.get(i) == (Integer) breakAt) {
                            breakPoint.remove(i);
                        }
                    }
                    System.out.println("Breakpoint at " + breakAt + " removed");
                } else if (dvm.getSourceString(breakAt - 1).contains("{") ||
                           dvm.getSourceString(breakAt - 1).contains("int") ||
                           dvm.getSourceString(breakAt - 1).contains("boolean") ||
                           dvm.getSourceString(breakAt - 1).contains("if") ||
                           dvm.getSourceString(breakAt - 1).contains("while") ||
                           dvm.getSourceString(breakAt - 1).contains("=")) {
                    
                    breakPoint.add(breakAt);
                    dvm.setBreakPoint(breakAt, true);
                    System.out.println("Breakpoint set at " + breakAt);
                    dvm.okayLine = breakPoint;
                
                } else {
                    System.out.println("Can't set break point on that line.");
                }
            }
            try {
                breakAt = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("Not a valid line, try again.");
                breakAt = 0;
            }
            if(breakAt == 0) {
                breakMore = false;
            }
        }
        
    }

    public void displayFunction() {
        System.out.println();
        int start = dvm.envRecPeek().getStart() - 1;
        int end = dvm.envRecPeek().getEnd() + 1;
        for(int i = start; i <= end - 2; i++) {
            System.out.println(dvm.getSourceString(i));
        }
        System.out.println();
    }

    public void continueEx() {
        if(dvm.trace != null) {
            while(!dvm.trace.getDoneTrace()) {
                dvm.setRunning(true);
                dvm.setPause(false);
                dvm.executeProgram();
                if (dvm.getTraceString() != null) {
                    System.out.println(dvm.getTraceString());
                }
                dvm.resetTrace();
            }
        } else {
        System.out.println();
        dvm.setRunning(true);
        dvm.setPause(false);
        dvm.executeProgram();
        }
    }

    public void displayVariables() {
        dvm.envRecVariables();
    }
    
    public void stepOut() {
        dvm.stepOut = new StepOut(dvm.envRecSize());
        dvm.setIsRunning(true);
        continueEx();
    }
    
    public void printBreakPoints() {
        System.out.println();
        System.out.println("Breakpoints on line(s): ");
        for(int i = 0; i < dvm.okayLine.size(); i++){
            System.out.print(" " + dvm.okayLine.get(i));
        }
        System.out.println();
    }

    public void quit() {
        System.out.println();
        System.out.println("****Execution Halted****");
        dvm.setRunning(false);
    }

    public void stepOver() {
        dvm.stepOver = new StepOver(dvm.envRecSize());
        dvm.setIsRunning(true);
        continueEx();
    }

    public void stepIn() {
        if(dvm.environmentStack.peek().getName() == null) {
            System.out.println("Program hasn't started yet.");
        } else {
            dvm.stepIn = new StepIn(dvm.envRecSize());
            dvm.setIsRunning(true);
            continueEx();
        }
    }
    
    public void trace() {
        if(dvm.trace == null){
            dvm.trace = new Trace();
            System.out.println("Trace on.\n");
            
        } else if( dvm.trace != null) {
            dvm.trace = null;
            System.out.println("Trace off.\n");
        }
    }
    
    public void printCallStack() {
        System.out.println(dvm.getCallStack());
    }

}
