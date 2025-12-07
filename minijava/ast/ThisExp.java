package minijava.ast;

public class ThisExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("This");
    }
}
