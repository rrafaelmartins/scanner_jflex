package minijava;

import java.io.*;
import java_cup.runtime.*;

/**
 * Classe principal do compilador MiniJava
 * Executa análise léxica e sintática
 */
public class Main {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java minijava.Main <arquivo.java>");
            System.out.println("Exemplo: java minijava.Main exemplo.java");
            System.exit(1);
        }

        String filename = args[0];
        
        try {
            System.out.println("=".repeat(50));
            System.out.println("Compilador MiniJava");
            System.out.println("=".repeat(50));
            System.out.println("Analisando arquivo: " + filename);
            System.out.println("-".repeat(50));
            
            // Criar o scanner (analisador léxico)
            FileReader reader = new FileReader(filename);
            MiniJavaLexer lexer = new MiniJavaLexer(reader);
            
            // Criar o parser (analisador sintático)
            Parser p = new Parser(lexer);
            
            // Executar a análise
            System.out.println("\nIniciando análise...\n");
            p.parse();
            
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Análise concluída com sucesso!");
            System.out.println("=".repeat(50));
            
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado - " + filename);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Erro durante a análise: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
