package minijava.ast;

public class PlusExp extends Exp {
    public Exp left, right;
    
    public PlusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Plus (+)");
        left.print(level + 1);
        right.print(level + 1);
    }
}
