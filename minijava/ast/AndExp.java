package minijava.ast;

public class AndExp extends Exp {
    public Exp left, right;
    
    public AndExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("And (&&)");
        left.print(level + 1);
        right.print(level + 1);
    }
}
