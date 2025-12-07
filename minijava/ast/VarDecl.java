package minijava.ast;

public class VarDecl extends ASTNode {
    public Type type;
    public String name;
    
    public VarDecl(Type type, String name) {
        this.type = type;
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("VarDecl: " + type + " " + name);
    }
}
