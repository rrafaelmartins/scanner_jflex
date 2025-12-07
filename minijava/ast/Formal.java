package minijava.ast;

public class Formal extends ASTNode {
    public Type type;
    public String name;
    
    public Formal(Type type, String name) {
        this.type = type;
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Formal: " + type + " " + name);
    }
}
