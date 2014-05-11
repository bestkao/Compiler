/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import interpreter.bytecodes.ByteCode;
import java.util.*;
import java.io.*;

/**
 *
 * @author James
 */
public class ByteCodeLoader extends Object{
    
    private final BufferedReader byteCodeReader;
    
    public ByteCodeLoader(String programFile) throws IOException {
        byteCodeReader = new BufferedReader(new FileReader(programFile));
    }
    
    public Program loadCodes(boolean debug) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException {

        Program program = new Program();
        Vector<String> args = new Vector<String>();
        CodeTable.init(debug);
        String readLine = byteCodeReader.readLine();
        
        while(readLine != null) {
            StringTokenizer tok = new StringTokenizer(readLine);
            args.clear();
            String codeClass = CodeTable.get(tok.nextToken());
            while(tok.hasMoreTokens()) {
                args.add(tok.nextToken());
            }
            ByteCode byteCode = (ByteCode)(Class.forName("interpreter.bytecodes."
                                           + codeClass).newInstance());
            byteCode.init(args);
            program.addCode(byteCode);
            readLine = byteCodeReader.readLine();
        }
        program.resolveAddresses();
        return program;
    }
    
}
