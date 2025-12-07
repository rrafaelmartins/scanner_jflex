package minijava.ast;

public class BooleanType extends Type {
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: boolean");
    }
    
    public String toString() { 
        return "boolean"; 
    }
}
