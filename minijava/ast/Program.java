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
