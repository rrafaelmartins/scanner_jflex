package minijava.ast;

public class ArrayLookup extends Exp {
    public Exp array, index;
    
    public ArrayLookup(Exp array, Exp index) {
        this.array = array;
        this.index = index;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayLookup");
        printIndent(level + 1);
        System.out.println("Array:");
        array.print(level + 2);
        printIndent(level + 1);
        System.out.println("Index:");
        index.print(level + 2);
    }
}
