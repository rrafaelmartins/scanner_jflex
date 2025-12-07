package minijava.ast;

import java.util.ArrayList;
import java.util.List;

public class Call extends Exp {
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
