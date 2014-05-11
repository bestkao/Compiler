package codegen;

/** The Code class records bytecode information
*/
public class Code {
    private Codes.ByteCodes bytecode;

    public Code(Codes.ByteCodes code) {
        bytecode = code;
    }

    public Codes.ByteCodes getBytecode() {
        return bytecode;
    }

    public String toString() {
        return bytecode.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}

