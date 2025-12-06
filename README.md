# Compilador MiniJava

Analisador Léxico e Sintático para a linguagem MiniJava utilizando **JFlex** e **CUP**.

## 📁 Estrutura do Projeto

```
scanner/
├── lib/                          # Bibliotecas necessárias
│   ├── jflex-full-1.9.1.jar     # JFlex (gerador de scanner)
│   ├── java-cup-11b.jar         # CUP (gerador de parser)
│   └── java-cup-11b-runtime.jar # Runtime do CUP
├── minijava/                     # Código fonte gerado e manual
│   └── Main.java                # Classe principal
├── build/                        # Arquivos compilados
├── MiniJavaLexer.jflex          # Especificação do Scanner (JFlex)
├── MiniJavaParser.cup           # Especificação do Parser (CUP)
├── build.sh                     # Script de build (Linux/Mac)
├── build.bat                    # Script de build (Windows)
├── run.sh                       # Script de execução (Linux/Mac)
├── run.bat                      # Script de execução (Windows)
├── exemplo_minijava.java        # Exemplo simples
└── exemplo_complexo.java        # Exemplo com mais recursos
```

## 🔧 Pré-requisitos

1. **Java JDK** (versão 8 ou superior)
2. **JFlex** - Gerador de analisador léxico
3. **CUP** - Gerador de analisador sintático

### Baixando as Bibliotecas

Se a pasta `lib/` estiver vazia, baixe os arquivos:

1. **JFlex**: https://jflex.de/download.html
   - Baixe `jflex-full-1.9.1.jar`

2. **CUP**: http://www2.cs.tum.edu/projects/cup/
   - Baixe `java-cup-11b.jar` e `java-cup-11b-runtime.jar`

Coloque os 3 arquivos JAR na pasta `lib/`.

## 🚀 Compilação e Execução

### Linux/Mac

```bash
# 1. Dar permissão de execução aos scripts
chmod +x build.sh run.sh

# 2. Compilar o projeto
./build.sh

# 3. Executar com um arquivo de teste
./run.sh exemplo_minijava.java
./run.sh exemplo_complexo.java
```

### Windows

```batch
# 1. Compilar o projeto
build.bat

# 2. Executar com um arquivo de teste
run.bat exemplo_minijava.java
run.bat exemplo_complexo.java
```

## 📝 Comandos Manuais (Passo a Passo)

Se preferir executar manualmente:

### 1. Gerar o Parser (CUP)
```bash
java -jar lib/java-cup-11b.jar -destdir minijava -parser parser -symbols sym MiniJavaParser.cup
```
Isso gera:
- `minijava/parser.java` - O analisador sintático
- `minijava/sym.java` - Símbolos/tokens

### 2. Gerar o Scanner (JFlex)
```bash
java -jar lib/jflex-full-1.9.1.jar -d minijava MiniJavaLexer.jflex
```
Isso gera:
- `minijava/MiniJavaLexer.java` - O analisador léxico

### 3. Compilar tudo
```bash
# Linux/Mac
javac -cp "lib/java-cup-11b-runtime.jar:." -d build minijava/*.java

# Windows
javac -cp "lib/java-cup-11b-runtime.jar;." -d build minijava/*.java
```

### 4. Executar
```bash
# Linux/Mac
java -cp "build:lib/java-cup-11b-runtime.jar" minijava.Main exemplo_minijava.java

# Windows
java -cp "build;lib/java-cup-11b-runtime.jar" minijava.Main exemplo_minijava.java
```

## 📖 Gramática MiniJava

A gramática implementada segue a especificação MiniJava:

```
Program        → MainClass ClassDecl*
MainClass      → class id { public static void main(String[] id) { Statement } }
ClassDecl      → class id { VarDecl* MethodDecl* }
               | class id extends id { VarDecl* MethodDecl* }
VarDecl        → Type id ;
MethodDecl     → public Type id ( FormalList ) { VarDecl* Statement* return Exp ; }
FormalList     → Type id FormalRest* | ε
FormalRest     → , Type id
Type           → int[] | boolean | int | id
Statement      → { Statement* }
               | if ( Exp ) Statement else Statement
               | while ( Exp ) Statement
               | System.out.println ( Exp ) ;
               | id = Exp ;
               | id [ Exp ] = Exp ;
Exp            → Exp op Exp | Exp [ Exp ] | Exp . length
               | Exp . id ( ExpList ) | INTEGER_LITERAL
               | true | false | id | this
               | new int [ Exp ] | new id ( )
               | ! Exp | ( Exp )
ExpList        → Exp ExpRest* | ε
ExpRest        → , Exp
```

### Tokens Reconhecidos

| Categoria | Tokens |
|-----------|--------|
| Palavras Reservadas | `class`, `public`, `static`, `void`, `main`, `String`, `extends`, `return`, `int`, `boolean`, `if`, `else`, `while`, `true`, `false`, `this`, `new`, `length` |
| Delimitadores | `{`, `}`, `(`, `)`, `[`, `]`, `;`, `,`, `.`, `=` |
| Operadores | `&&`, `<`, `+`, `-`, `*`, `!` |
| Literais | Inteiros (`[0-9]+`) |
| Identificadores | `[A-Za-z][A-Za-z0-9_]*` |

## 🧪 Exemplos de Saída

### Programa válido
```
==================================================
Compilador MiniJava
==================================================
Analisando arquivo: exemplo_minijava.java
--------------------------------------------------

Iniciando análise...

Classe principal 'Factorial' reconhecida
  Método 'ComputeFac' reconhecido
Classe 'Fac' reconhecida
Programa MiniJava reconhecido com sucesso!

--------------------------------------------------
Análise concluída com sucesso!
==================================================
```

### Programa com erro
```
Erro de sintaxe na linha 5, coluna 10 : ...
```

## 📚 Arquivos Importantes

### MiniJavaLexer.jflex
Especificação do analisador léxico. Define:
- Expressões regulares para tokens
- Ações para cada token reconhecido
- Tratamento de erros léxicos

### MiniJavaParser.cup
Especificação do analisador sintático. Define:
- Declaração de terminais e não-terminais
- Precedência de operadores
- Regras gramaticais
- Ações semânticas

### Main.java
Classe principal que:
- Lê o arquivo de entrada
- Cria o scanner (lexer)
- Cria o parser
- Executa a análise

## ❓ Problemas Comuns

1. **"JFlex não encontrado"**: Verifique se o arquivo JAR está em `lib/`
2. **"CUP não encontrado"**: Verifique se ambos os JARs do CUP estão em `lib/`
3. **Erro de classpath**: No Windows use `;` e no Linux/Mac use `:` como separador

## 👥 Autores

Desenvolvido para a disciplina de Compiladores.
