package minijava.ast;

// Classe base para todos os nós da AST
public abstract class ASTNode {
    protected int indent = 0;
    
    public abstract void print(int level);
    
    protected void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }
}
