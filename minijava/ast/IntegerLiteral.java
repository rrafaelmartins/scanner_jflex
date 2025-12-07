package minijava.ast;

public class IntegerLiteral extends Exp {
    public int value;
    
    public IntegerLiteral(int value) {
        this.value = value;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("IntLiteral: " + value);
    }
}
