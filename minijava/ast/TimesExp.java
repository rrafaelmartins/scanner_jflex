package minijava.ast;

public class TimesExp extends Exp {
    public Exp left, right;
    
    public TimesExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Times (*)");
        left.print(level + 1);
        right.print(level + 1);
    }
}
