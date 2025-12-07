package minijava.ast;

public class MainClass extends ASTNode {
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
