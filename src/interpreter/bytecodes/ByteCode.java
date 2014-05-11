/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;
import java.util.Vector;
import interpreter.*;

/**
 *
 * @author James
 */
public abstract class ByteCode {

    public abstract void init(Vector<String> arg);
    public abstract void execute(VirtualMachine vm);
    
}
