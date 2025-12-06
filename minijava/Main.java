package minijava;

import java.io.*;
import java_cup.runtime.*;
import minijava.ast.*;

/**
 * Classe principal do compilador MiniJava
 * Executa análise léxica e sintática e mostra a AST
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
            System.out.println("==================================================");
            System.out.println("Compilador MiniJava");
            System.out.println("==================================================");
            System.out.println("Analisando arquivo: " + filename);
            System.out.println("--------------------------------------------------");
            
            // Criar o scanner (analisador léxico)
            FileReader reader = new FileReader(filename);
            MiniJavaLexer lexer = new MiniJavaLexer(reader);
            
            // Criar o parser (analisador sintático)
            Parser p = new Parser(lexer);
            
            // Executar a análise
            System.out.println("\n[FASE 1] Analise Lexica e Sintatica\n");
            p.parse();
            
            // Mostrar a árvore sintática
            System.out.println("\n--------------------------------------------------");
            System.out.println("[FASE 2] Arvore Sintatica Abstrata (AST)");
            System.out.println("--------------------------------------------------\n");
            
            if (p.astRoot != null) {
                p.astRoot.print(0);
            } else {
                System.out.println("Erro: AST nao foi construida.");
            }
            
            System.out.println("\n--------------------------------------------------");
            System.out.println("Analise concluida com sucesso!");
            System.out.println("==================================================");
            
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo nao encontrado - " + filename);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Erro durante a analise: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
