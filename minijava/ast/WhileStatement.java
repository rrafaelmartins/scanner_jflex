package minijava.ast;

public class WhileStatement extends Statement {
    public Exp condition;
    public Statement body;
    
    public WhileStatement(Exp condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("While");
        printIndent(level + 1);
        System.out.println("Condition:");
        condition.print(level + 2);
        printIndent(level + 1);
        System.out.println("Body:");
        body.print(level + 2);
    }
}
