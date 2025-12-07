package minijava.ast;

public class TrueExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("True");
    }
}
