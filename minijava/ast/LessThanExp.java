package minijava.ast;

public class LessThanExp extends Exp {
    public Exp left, right;
    
    public LessThanExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("LessThan (<)");
        left.print(level + 1);
        right.print(level + 1);
    }
}
