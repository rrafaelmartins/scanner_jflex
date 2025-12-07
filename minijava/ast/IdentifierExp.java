package minijava.ast;

public class IdentifierExp extends Exp {
    public String name;
    
    public IdentifierExp(String name) {
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Identifier: " + name);
    }
}
