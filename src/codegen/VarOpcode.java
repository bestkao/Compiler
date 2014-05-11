package codegen;

/**
 *  VarOpcode class used for bytecodes with addresses
 *  and string information - e.g. LOAD 0 x
*/
public class VarOpcode extends Code {
    int location;
    String varname;
    
/**
 *  @param code is the bytecode being created
 *  @param location is the offset from the start of the current frame
 *  @param varname is the name of the variable being loaded/stored
*/
    public VarOpcode(Codes.ByteCodes code, int location, String varname) {
        super(code);
        this.location = location;
        this.varname = varname;
    }
    
    public String toString() {
        return super.toString() + " " + location + " " + varname;
    }
    
    public void print() {
        System.out.println(toString());
    }
}