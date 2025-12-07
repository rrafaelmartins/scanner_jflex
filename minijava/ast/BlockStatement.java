package minijava.ast;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends Statement {
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
