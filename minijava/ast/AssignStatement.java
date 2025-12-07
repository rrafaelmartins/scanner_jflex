package minijava.ast;

public class AssignStatement extends Statement {
    public String id;
    public Exp exp;
    
    public AssignStatement(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Assign: " + id);
        exp.print(level + 1);
    }
}
