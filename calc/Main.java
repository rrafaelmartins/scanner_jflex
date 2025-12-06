package calc;

import java.io.*;


public class Main {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java calc.Main <arquivo.txt>");
            System.exit(1);
        }

        try {
            System.out.println("==================================================");
            System.out.println("Calculadora - Analisador Lexico e Sintatico");
            System.out.println("==================================================");
            System.out.println("Analisando arquivo: " + args[0]);
            System.out.println("--------------------------------------------------");
            
            FileReader reader = new FileReader(args[0]);
            CalcLexer lexer = new CalcLexer(reader);
            Parser parser = new Parser(lexer);
            
            System.out.println("\nCalculando...\n");
            parser.parse();
            
            System.out.println("\n--------------------------------------------------");
            System.out.println("Analise concluida com sucesso!");
            System.out.println("==================================================");
            
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo nao encontrado - " + args[0]);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Erro durante a analise: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}