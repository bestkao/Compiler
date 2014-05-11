package codegen;

/** NumOpcode class used for bytecodes with a number op field
 *  e.g. lit 5
*/
public class NumOpcode extends Code {
    int num;
    
    public NumOpcode(Codes.ByteCodes code, int n) {
        super(code);
        num = n;
    }
    
    int getNum() {
        return num;
    }
    
    public String toString() {
        return super.toString() + " " + num;
    }
    
    public void print() {
        System.out.println(toString());
    }
}
