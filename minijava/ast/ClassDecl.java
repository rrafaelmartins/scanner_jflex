package minijava.ast;

import java.util.ArrayList;
import java.util.List;

public class ClassDecl extends ASTNode {
    public String name;
    public String parent; // null se não extends
    public List<VarDecl> vars;
    public List<MethodDecl> methods;
    
    public ClassDecl(String name, String parent, List<VarDecl> vars, List<MethodDecl> methods) {
        this.name = name;
        this.parent = parent;
        this.vars = vars != null ? vars : new ArrayList<>();
        this.methods = methods != null ? methods : new ArrayList<>();
    }
    
    public void print(int level) {
        printIndent(level);
        if (parent != null) {
            System.out.println("ClassDecl: " + name + " extends " + parent);
        } else {
            System.out.println("ClassDecl: " + name);
        }
        if (!vars.isEmpty()) {
            printIndent(level + 1);
            System.out.println("Variables:");
            for (VarDecl v : vars) {
                v.print(level + 2);
            }
        }
        if (!methods.isEmpty()) {
            printIndent(level + 1);
            System.out.println("Methods:");
            for (MethodDecl m : methods) {
                m.print(level + 2);
            }
        }
    }
}
