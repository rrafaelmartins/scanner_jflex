package minijava.ast;

public class IdentifierType extends Type {
    public String name;
    
    public IdentifierType(String name) {
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: " + name);
    }
    
    public String toString() { 
        return name; 
    }
}
