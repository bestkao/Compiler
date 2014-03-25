package interpreter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * In charge of loading the code from the source file. It also has a method that
 * loads bytecode objects into an instance of the program class. This is done
 * with the help of a Hashtable that matches the bytecode to their equivalent
 * classname.
 * @author James Kao
 */
public class ByteCodeLoader extends Object{
    // reads in the next bytecode
    // builds an instance of the class corresponding to the bytecode
    // the bytecode instance is added to the Program
    // after all the bytecodes are loaded, symbolic addresses are resolved
    public ByteCodeLoader(String programFile) throws IOException {
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
            String line = in.readLine();
        } catch( java.io.IOException ex ) {
            System.out.println("**** " + ex);
        }
    }
    
    public Program loadCodes() {
        return new Program();
    }

    // reades in the codes from the file
    // and creates the appropriate ByteCode class
    // ex: reads in "LIT 2"
    //     checks CodeTable for what ByteCode to create
    //     using 'Java reflection'
    //  -> lit = new LitCode();
    //     lit.init(2);
}
