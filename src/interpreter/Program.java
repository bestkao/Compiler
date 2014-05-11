/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.LabelCode;
import interpreter.bytecodes.FalseBranchCode;
import interpreter.bytecodes.CallCode;
import interpreter.bytecodes.GoToCode;
import interpreter.bytecodes.ByteCode;
import java.util.*;

/**
 *
 * @author James
 */
public class Program extends Object {
    
    static HashMap<String, Integer> byteCodeLabels;
    private final ArrayList<Object> byteCodeList;
    
    Program () {
        byteCodeList = new ArrayList();
        byteCodeLabels = new HashMap<String, Integer>();
    }
    
    public void addCode(ByteCode byteCode) {
        if(byteCode instanceof LabelCode) {
            LabelCode labelBranch = (LabelCode)byteCode;
            addLabel(labelBranch.getLabel(),byteCodeList.size());
        }
        byteCodeList.add(byteCode);
    }
    
    public void resolveAddresses() {
        for (Object byteCodeList1 : byteCodeList) {
            if (byteCodeList1 instanceof GoToCode) {
                GoToCode changeBranch = (GoToCode) byteCodeList1;
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
            } else if (byteCodeList1 instanceof FalseBranchCode) {
                FalseBranchCode changeBranch = (FalseBranchCode) byteCodeList1;
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
            } else if (byteCodeList1 instanceof CallCode) {
                CallCode changeBranch = (CallCode) byteCodeList1;
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
            }
        }
    }
    
    static public void addLabel(String key, int branch) {
        byteCodeLabels.put(key, branch);
    }
    
    static public String getBranch(String branch) {
        return byteCodeLabels.get(branch).toString();
    }
    
    public ByteCode getCode(int index) {
        return (ByteCode)byteCodeList.get(index);
    }
    
    public int codeListSize() {
        return byteCodeList.size();
    }

}
