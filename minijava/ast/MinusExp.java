package minijava.ast;

public class MinusExp extends Exp {
    public Exp left, right;
    
    public MinusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Minus (-)");
        left.print(level + 1);
        right.print(level + 1);
    }
}
