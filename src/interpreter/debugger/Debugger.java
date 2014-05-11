/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.Interpreter;
import java.io.IOException;
import interpreter.debugger.ui.UI;

/**
 *
 * @author James
 */
public class Debugger {
    
    Interpreter interpreter;
    DebuggerVM dvm;
    UI userInter;
    String file;
    String file2;
    static boolean debugging = true;
    
    public Debugger(String codeFile, String sourceFile)
            throws ClassNotFoundException, InstantiationException, 
            IllegalAccessException, IllegalAccessException, 
            IOException, IOException {
        
        file = codeFile;
        file2 = sourceFile;

    }
    /*
     * Executes the debugging process for the Debug object.
     */
    public void runDebugger() throws ClassNotFoundException, IOException,
            InstantiationException, IllegalAccessException {
        
        Interpreter interpreter = new Interpreter(file);
        dvm = new DebuggerVM(interpreter.getProgram(), file2);
        userInter = new UI(dvm);
        userInter.printSourcefile();
        while(debugging) {
            userInter.helpMenu();
        }
        System.out.println("Debugging complete.");
        
    }
    
    public static void isDebugging(boolean set) {
        debugging = set;
    }
        
}
