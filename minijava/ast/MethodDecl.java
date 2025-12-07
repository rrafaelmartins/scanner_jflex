package minijava.ast;

import java.util.ArrayList;
import java.util.List;

public class MethodDecl extends ASTNode {
    public Type returnType;
    public String name;
    public List<Formal> params;
    public List<VarDecl> vars;
    public List<Statement> statements;
    public Exp returnExp;
    
    public MethodDecl(Type returnType, String name, List<Formal> params, 
                      List<VarDecl> vars, List<Statement> statements, Exp returnExp) {
        this.returnType = returnType;
        this.name = name;
        this.params = params != null ? params : new ArrayList<>();
        this.vars = vars != null ? vars : new ArrayList<>();
        this.statements = statements != null ? statements : new ArrayList<>();
        this.returnExp = returnExp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("MethodDecl: " + returnType + " " + name);
        if (!params.isEmpty()) {
            printIndent(level + 1);
            System.out.println("Parameters:");
            for (Formal f : params) {
                f.print(level + 2);
            }
        }
        if (!vars.isEmpty()) {
            printIndent(level + 1);
            System.out.println("LocalVars:");
            for (VarDecl v : vars) {
                v.print(level + 2);
            }
        }
        if (!statements.isEmpty()) {
            printIndent(level + 1);
            System.out.println("Statements:");
            for (Statement s : statements) {
                s.print(level + 2);
            }
        }
        printIndent(level + 1);
        System.out.println("Return:");
        returnExp.print(level + 2);
    }
}
