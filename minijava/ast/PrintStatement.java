package minijava.ast;

public class PrintStatement extends Statement {
    public Exp exp;
    
    public PrintStatement(Exp exp) {
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Print");
        exp.print(level + 1);
    }
}
