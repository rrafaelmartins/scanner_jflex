package minijava.ast;

import java.util.ArrayList;
import java.util.List;

// Classe base para Statements
public abstract class Statement extends ASTNode {
}

// Bloco de statements { ... }
class BlockStatement extends Statement {
    public List<Statement> statements;
    
    public BlockStatement(List<Statement> statements) {
        this.statements = statements != null ? statements : new ArrayList<>();
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Block");
        for (Statement s : statements) {
            s.print(level + 1);
        }
    }
}

// if (exp) stmt else stmt
class IfStatement extends Statement {
    public Exp condition;
    public Statement thenStmt;
    public Statement elseStmt;
    
    public IfStatement(Exp condition, Statement thenStmt, Statement elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("If");
        printIndent(level + 1);
        System.out.println("Condition:");
        condition.print(level + 2);
        printIndent(level + 1);
        System.out.println("Then:");
        thenStmt.print(level + 2);
        printIndent(level + 1);
        System.out.println("Else:");
        elseStmt.print(level + 2);
    }
}

// while (exp) stmt
class WhileStatement extends Statement {
    public Exp condition;
    public Statement body;
    
    public WhileStatement(Exp condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("While");
        printIndent(level + 1);
        System.out.println("Condition:");
        condition.print(level + 2);
        printIndent(level + 1);
        System.out.println("Body:");
        body.print(level + 2);
    }
}

// System.out.println(exp)
class PrintStatement extends Statement {
    public Exp exp;
    
    public PrintStatement(Exp exp) {
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Print");
        exp.print(level + 1);
    }
}

// id = exp
class AssignStatement extends Statement {
    public String id;
    public Exp exp;
    
    public AssignStatement(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Assign: " + id);
        exp.print(level + 1);
    }
}

// id[exp] = exp
class ArrayAssignStatement extends Statement {
    public String id;
    public Exp index;
    public Exp exp;
    
    public ArrayAssignStatement(String id, Exp index, Exp exp) {
        this.id = id;
        this.index = index;
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayAssign: " + id);
        printIndent(level + 1);
        System.out.println("Index:");
        index.print(level + 2);
        printIndent(level + 1);
        System.out.println("Value:");
        exp.print(level + 2);
    }
}
