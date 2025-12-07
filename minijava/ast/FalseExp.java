package minijava.ast;

public class FalseExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("False");
    }
}
