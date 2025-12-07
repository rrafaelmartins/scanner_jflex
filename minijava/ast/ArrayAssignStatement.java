package minijava.ast;

public class ArrayAssignStatement extends Statement {
    public String id;
    public Exp index;
    public Exp exp;
    
    public ArrayAssignStatement(String id, Exp index, Exp exp) {
        this.id = id;
        this.index = index;
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayAssign: " + id);
        printIndent(level + 1);
        System.out.println("Index:");
        index.print(level + 2);
        printIndent(level + 1);
        System.out.println("Value:");
        exp.print(level + 2);
    }
}
