package minijava.ast;

public class NewArrayExp extends Exp {
    public Exp size;
    
    public NewArrayExp(Exp size) {
        this.size = size;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("NewArray (int[])");
        printIndent(level + 1);
        System.out.println("Size:");
        size.print(level + 2);
    }
}
