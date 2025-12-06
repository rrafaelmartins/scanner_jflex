package minijava.ast;

import java.util.ArrayList;
import java.util.List;

// Classe base para Expressões
public abstract class Exp extends ASTNode {
}

// Operações binárias
class AndExp extends Exp {
    public Exp left, right;
    
    public AndExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("And (&&)");
        left.print(level + 1);
        right.print(level + 1);
    }
}

class LessThanExp extends Exp {
    public Exp left, right;
    
    public LessThanExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("LessThan (<)");
        left.print(level + 1);
        right.print(level + 1);
    }
}

class PlusExp extends Exp {
    public Exp left, right;
    
    public PlusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Plus (+)");
        left.print(level + 1);
        right.print(level + 1);
    }
}

class MinusExp extends Exp {
    public Exp left, right;
    
    public MinusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Minus (-)");
        left.print(level + 1);
        right.print(level + 1);
    }
}

class TimesExp extends Exp {
    public Exp left, right;
    
    public TimesExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Times (*)");
        left.print(level + 1);
        right.print(level + 1);
    }
}

// Acesso a array: exp[exp]
class ArrayLookup extends Exp {
    public Exp array, index;
    
    public ArrayLookup(Exp array, Exp index) {
        this.array = array;
        this.index = index;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayLookup");
        printIndent(level + 1);
        System.out.println("Array:");
        array.print(level + 2);
        printIndent(level + 1);
        System.out.println("Index:");
        index.print(level + 2);
    }
}

// exp.length
class ArrayLength extends Exp {
    public Exp array;
    
    public ArrayLength(Exp array) {
        this.array = array;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("ArrayLength");
        array.print(level + 1);
    }
}

// Chamada de método: exp.id(expList)
class Call extends Exp {
    public Exp object;
    public String method;
    public List<Exp> args;
    
    public Call(Exp object, String method, List<Exp> args) {
        this.object = object;
        this.method = method;
        this.args = args != null ? args : new ArrayList<>();
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Call: " + method);
        printIndent(level + 1);
        System.out.println("Object:");
        object.print(level + 2);
        if (!args.isEmpty()) {
            printIndent(level + 1);
            System.out.println("Arguments:");
            for (Exp e : args) {
                e.print(level + 2);
            }
        }
    }
}

// Literal inteiro
class IntegerLiteral extends Exp {
    public int value;
    
    public IntegerLiteral(int value) {
        this.value = value;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("IntLiteral: " + value);
    }
}

// true
class TrueExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("True");
    }
}

// false
class FalseExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("False");
    }
}

// Identificador
class IdentifierExp extends Exp {
    public String name;
    
    public IdentifierExp(String name) {
        this.name = name;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Identifier: " + name);
    }
}

// this
class ThisExp extends Exp {
    public void print(int level) {
        printIndent(level);
        System.out.println("This");
    }
}

// new int[exp]
class NewArrayExp extends Exp {
    public Exp size;
    
    public NewArrayExp(Exp size) {
        this.size = size;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("NewArray (int[])");
        printIndent(level + 1);
        System.out.println("Size:");
        size.print(level + 2);
    }
}

// new id()
class NewObjectExp extends Exp {
    public String className;
    
    public NewObjectExp(String className) {
        this.className = className;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("NewObject: " + className);
    }
}

// !exp
class NotExp extends Exp {
    public Exp exp;
    
    public NotExp(Exp exp) {
        this.exp = exp;
    }
    
    public void print(int level) {
        printIndent(level);
        System.out.println("Not (!)");
        exp.print(level + 1);
    }
}
