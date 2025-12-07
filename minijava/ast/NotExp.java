package minijava.ast;

public class NotExp extends Exp {
    public Exp exp;
    
    public NotExp(Exp exp) {
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Not (!)");
        exp.print(level + 1);
    }
}
