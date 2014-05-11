package codegen;

/**
 * LabelOpcode class records bytecodes with associated labels
 * e.g. LABEL xyz
*/
public class LabelOpcode extends Code {
    String label;
    
/**
 *  @param code is the bytecode being created
 *  @param label is the string representation of the label of interest
*/
    public LabelOpcode(Codes.ByteCodes code, String label) {
        super(code);
        this.label = label;
    }
    
    public void print() {
        System.out.println(toString());
    }
    
    public String toString() {
        return super.toString() + " " + label;
    }
}
