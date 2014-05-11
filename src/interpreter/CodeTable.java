/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import java.util.HashMap;

/**
 *
 * @author James
 */
public class CodeTable extends Object{

    static HashMap byteCode = new HashMap();
    
    public static String get(String code) {
        return byteCode.get(code).toString();
    }
    
    public static void init(boolean debug) {
        String mode = "";
        if(debug == true) {
            mode = "debuggerBytecodes.debug";
        }
        byteCode.put("ARGS", "ArgsCode");
        byteCode.put("BOP", "BopCode");
        byteCode.put("CALL", mode + "CallCode");
        byteCode.put("FALSEBRANCH", "FalseBranchCode");
        byteCode.put("GOTO", "GoToCode");
        byteCode.put("HALT", "HaltCode");
        byteCode.put("LABEL", "LabelCode");
        byteCode.put("LIT", mode + "LitCode");
        byteCode.put("LOAD", "LoadCode");
        byteCode.put("POP", mode + "PopCode");
        byteCode.put("READ", "ReadCode");
        byteCode.put("RETURN", mode + "ReturnCode");
        byteCode.put("STORE", "StoreCode");
        byteCode.put("WRITE", "WriteCode");
        byteCode.put("DUMP", "DumpCode");
        
        byteCode.put("FUNCTION", "debuggerBytecodes.FunctionCode");
        byteCode.put("LINE", "debuggerBytecodes.LineCode");
        byteCode.put("FORMAL", "debuggerBytecodes.FormalCode");
        
    }
    
}
