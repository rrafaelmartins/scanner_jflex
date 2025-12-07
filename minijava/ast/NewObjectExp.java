package minijava.ast;

public class NewObjectExp extends Exp {
    public String className;
    
    public NewObjectExp(String className) {
        this.className = className;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("NewObject: " + className);
    }
}
