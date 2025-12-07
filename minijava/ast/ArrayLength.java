package minijava.ast;

public class ArrayLength extends Exp {
    public Exp array;
    
    public ArrayLength(Exp array) {
        this.array = array;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayLength");
        array.print(level + 1);
    }
}
