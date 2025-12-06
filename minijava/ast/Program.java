package minijava.ast;

import java.util.ArrayList;
import java.util.List;

// Programa completo
public class Program extends ASTNode {
    public MainClass mainClass;
    public List<ClassDecl> classes;
    
    public Program(MainClass mainClass, List<ClassDecl> classes) {
        this.mainClass = mainClass;
        this.classes = classes != null ? classes : new ArrayList<>();
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Program");
        mainClass.print(level + 1);
        for (ClassDecl c : classes) {
            c.print(level + 1);
        }
    }
}

// Classe principal (main)
class MainClass extends ASTNode {
    public String name;
    public String argsName;
    public Statement statement;
    
    public MainClass(String name, String argsName, Statement statement) {
        this.name = name;
        this.argsName = argsName;
        this.statement = statement;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("MainClass: " + name);
        printIndent(level + 1);
        System.out.println("Args: " + argsName);
        printIndent(level + 1);
        System.out.println("Body:");
        statement.print(level + 2);
    }
}

// Declaração de classe
class ClassDecl extends ASTNode {
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

// Declaração de variável
class VarDecl extends ASTNode {
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

// Declaração de método
class MethodDecl extends ASTNode {
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

// Parâmetro formal
class Formal extends ASTNode {
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

// Tipos
abstract class Type extends ASTNode {
}

class IntArrayType extends Type {
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: int[]");
    }
    public String toString() { return "int[]"; }
}

class BooleanType extends Type {
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: boolean");
    }
    public String toString() { return "boolean"; }
}

class IntType extends Type {
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: int");
    }
    public String toString() { return "int"; }
}

class IdentifierType extends Type {
    public String name;
    
    public IdentifierType(String name) {
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Type: " + name);
    }
    public String toString() { return name; }
}
