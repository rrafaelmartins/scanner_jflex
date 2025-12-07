package minijava.ast;

public class IntType extends Type {
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: int");
    }
    
    public String toString() { 
        return "int"; 
    }
}
