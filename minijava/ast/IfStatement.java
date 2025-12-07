package minijava.ast;

public class IfStatement extends Statement {
    public Exp condition;
    public Statement thenStmt;
    public Statement elseStmt;
    
    public IfStatement(Exp condition, Statement thenStmt, Statement elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("If");
        printIndent(level + 1);
        System.out.println("Condition:");
        condition.print(level + 2);
        printIndent(level + 1);
        System.out.println("Then:");
        thenStmt.print(level + 2);
        printIndent(level + 1);
        System.out.println("Else:");
        elseStmt.print(level + 2);
    }
}
