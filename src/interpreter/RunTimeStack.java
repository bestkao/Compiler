package interpreter;

/**
 * Holds the runtime stack as well as the frame pointers. It has many methods to
 * facilitate looking up values, storing them, and so on.
 * @author James Kao
 */
public class RunTimeStack {
    public RunTimeStack() {
        int[] framePointers = {0};
    }
    // contains Vector and Stack
    // example:
    //   runStack: // Vectors
    //     0 1 4 2 9 8 7 // actual stored data
    //   framePointers: // Stack
    //     0 3 // indices where frames begin
    // so theres 2 frames: "0 1 4" and "2 9 8 7"
    public void dump() {
        // dump contents of RunTimeStack itself
    }
    
    public int peek() {
        return -1;
    }
    public int pop() {
        return -1;
    }
    
    public int push(int i) {
        return i;
    }
    
    public void newFrameAt(int offset) {
        
    }
    
    public void popFrame() {
        
    }
    
    public int store(int offset) {
        return -1;
    }
    
    public int load(int offset) {
        return -1;
    }
    
    public Integer push(Integer i) {
        return i;
    }
}