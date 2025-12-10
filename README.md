# Compiladores - Analisador Léxico e Sintático

Este projeto implementa analisadores léxicos e sintáticos usando **JFlex** e **CUP** para duas linguagens:
1. **MiniJava** - Subconjunto da linguagem Java
2. **Calculadora** - Expressões aritméticas

---

## 📁 Estrutura do Projeto

```
scanner_jflex/
├── java-cup-11b.jar              # CUP - Gerador de Parser
├── java-cup-11b-runtime.jar      # Runtime do CUP
├── jflex-full-1.9.1.jar          # JFlex - Gerador de Scanner
│
├── MiniJavaLexer.jflex           # Scanner do MiniJava
├── MiniJavaParser.cup            # Parser do MiniJava
├── minijava/
│   └── Main.java                 # Classe principal MiniJava
│
├── CalcLexer.jflex               # Scanner da Calculadora
├── CalcParser.cup                # Parser da Calculadora
├── calc/
│   └── Main.java                 # Classe principal Calculadora
│
├── build/                        # Arquivos compilados (.class)
│
├── exemplo_simples.txt           # Exemplo MiniJava simples
├── exemplo_certo.txt             # Exemplo MiniJava completo
├── exemplo_calc_ok.txt           # Exemplo Calculadora
└── teste_calc.txt                # Teste Calculadora
```

---

## 🔧 Pré-requisitos

- **Java JDK** (versão 8 ou superior)
- **JFlex** (`jflex-full-1.9.1.jar`)
- **CUP** (`java-cup-11b.jar` e `java-cup-11b-runtime.jar`)

---

## 🧮 CALCULADORA

### Comandos para Compilar

```batch
REM 1. Criar pasta calc (se não existir)
mkdir calc

REM 2. Gerar o Parser com CUP
java -jar java-cup-11b.jar -destdir calc -parser Parser -symbols Sym CalcParser.cup

REM 3. Gerar o Scanner com JFlex
java -jar jflex-full-1.9.1.jar -d calc CalcLexer.jflex

REM 4. Corrigir o import no arquivo gerado (bug do JFlex)
powershell -Command "(Get-Content calc\CalcLexer.java) -replace 'import java_cup.runtime\.;', 'import java_cup.runtime.*;' | Set-Content calc\CalcLexer.java"

REM 5. Criar pasta build (se não existir)
mkdir build

REM 6. Compilar todos os arquivos Java
javac -cp ".\java-cup-11b-runtime.jar;." -d build calc\Main.java calc\Parser.java calc\Sym.java calc\CalcLexer.java
```

### Comando para Executar

```batch
java -cp "build;java-cup-11b-runtime.jar" calc.Main exemplo_calc_ok.txt
```

### Exemplo de Entrada (`exemplo_calc_ok.txt`)

```
(3 + 4) * 2
10 / 2
5 // 2
2 ** 3
3.14 * 2
```

### Exemplo de Saída

```
==================================================
Calculadora - Analisador Lexico e Sintatico
==================================================
Analisando arquivo: exemplo_calc_ok.txt
--------------------------------------------------

Calculando...

TOKEN: LPAREN (linha 1, coluna 1)
TOKEN: INT(3) (linha 1, coluna 2)
TOKEN: PLUS (linha 1, coluna 4)
TOKEN: INT(4) (linha 1, coluna 6)
TOKEN: RPAREN (linha 1, coluna 7)
TOKEN: TIMES (linha 1, coluna 9)
TOKEN: INT(2) (linha 1, coluna 11)
  3.0 + 4.0 = 7.0
  7.0 * 2.0 = 14.0
Resultado: 14.0
...
```

### Tokens Reconhecidos (Calculadora)

| Token | Descrição | Exemplo |
|-------|-----------|---------|
| `INT` | Número inteiro | `42` |
| `FLOAT` | Número decimal | `3.14` |
| `PLUS` | Adição | `+` |
| `MINUS` | Subtração | `-` |
| `TIMES` | Multiplicação | `*` |
| `DIV` | Divisão | `/` |
| `DIV_INT` | Divisão inteira | `//` |
| `POW` | Potência | `**` |
| `LPAREN` | Parêntese esquerdo | `(` |
| `RPAREN` | Parêntese direito | `)` |

---

## ☕ MINIJAVA

### Comandos para Compilar

```batch
REM 1. Criar pasta minijava (se não existir)
mkdir minijava

REM 2. Gerar o Parser com CUP
java -jar java-cup-11b.jar -expect 1 -parser Parser -symbols Sym MiniJavaParser.cup

REM 3. Mover arquivos gerados para pasta minijava
move Parser.java minijava\
move Sym.java minijava\

REM 4. Gerar o Scanner com JFlex
java -jar jflex-full-1.9.1.jar -d minijava MiniJavaLexer.jflex

REM 5. Corrigir o import no arquivo gerado (bug do JFlex)
powershell -Command "(Get-Content minijava\MiniJavaLexer.java) -replace 'import java_cup.runtime\.;', 'import java_cup.runtime.*;' | Set-Content minijava\MiniJavaLexer.java"

REM 6. Criar pasta build (se não existir)
mkdir build

REM 7. Compilar todos os arquivos Java
javac -cp ".\java-cup-11b-runtime.jar;." -d build minijava\Main.java minijava\Parser.java minijava\Sym.java minijava\MiniJavaLexer.java
```

### Comando para Executar

```batch
java -cp "build;java-cup-11b-runtime.jar" minijava.Main exemplo_certo.txt
```

### Exemplo de Entrada (`exemplo_certo.txt`)

```java
class Main {
    public static void main(String[] args) {
        System.out.println(new Calc().compute(10));
    }
}

class Calc {
    public int compute(int n) {
        int result;
        if (n < 20) {
            result = n + 1;
        } else {
            result = n - 1;
        }
        return result;
    }
}
```

### Exemplo de Saída

```
==================================================
Compilador MiniJava
==================================================
Analisando arquivo: exemplo_certo.txt
--------------------------------------------------

Iniciando análise...

    Criação de objeto 'Calc'
    Chamada de método 'compute'
Classe principal 'Main' reconhecida
  Variável 'result' declarada
  Método 'compute' reconhecido
Classe 'Calc' reconhecida
Programa MiniJava reconhecido com sucesso!

--------------------------------------------------
Análise concluída com sucesso!
==================================================
```

### Tokens Reconhecidos (MiniJava)

| Categoria | Tokens |
|-----------|--------|
| Palavras Reservadas | `class`, `public`, `static`, `void`, `main`, `String`, `extends`, `return`, `int`, `boolean`, `if`, `else`, `while`, `true`, `false`, `this`, `new`, `length` |
| Delimitadores | `{`, `}`, `(`, `)`, `[`, `]`, `;`, `,`, `.`, `=` |
| Operadores | `&&`, `<`, `+`, `-`, `*`, `!` |
| Literais | Inteiros (`[0-9]+`) |
| Identificadores | `[A-Za-z][A-Za-z0-9_]*` |
| Especial | `System.out.println` |

---

## 📜 Gramática MiniJava

```
Program        → MainClass ClassDecl*
MainClass      → class id { public static void main(String[] id) { Statement } }
ClassDecl      → class id { VarDecl* MethodDecl* }
               | class id extends id { VarDecl* MethodDecl* }
VarDecl        → Type id ;
MethodDecl     → public Type id ( FormalList ) { VarDecl* Statement* return Exp ; }
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
```

---

## 📜 Gramática da Calculadora

```
expr_list → expr_list expr | expr
expr      → expr + expr
          | expr - expr
          | expr * expr
          | expr / expr
          | expr // expr    (divisão inteira)
          | expr ** expr    (potência)
          | - expr
          | ( expr )
          | INT
          | FLOAT
```

### Precedência de Operadores (menor para maior)

1. `+`, `-` (adição, subtração)
2. `*`, `/`, `//` (multiplicação, divisão)
3. `**` (potência - associativo à direita)
4. `-` unário

---

## ❓ Problemas Comuns

### Erro: `import java_cup.runtime.;`
O JFlex às vezes gera o import incorreto. Corrigir com:
```batch
powershell -Command "(Get-Content arquivo.java) -replace 'import java_cup.runtime\.;', 'import java_cup.runtime.*;' | Set-Content arquivo.java"
```

### Erro: `cannot find symbol: class Symbol`
O classpath não está correto. Verificar se o JAR está no caminho:
```batch
javac -cp ".\java-cup-11b-runtime.jar;." ...
```

### Erro: `Shift/Reduce conflict`
Usar a flag `-expect N` no CUP:
```batch
java -jar java-cup-11b.jar -expect 1 ...
```

---

- **JFlex**: https://jflex.de/
- **CUP**: http://www2.cs.tum.edu/projects/cup/
- **MiniJava**: https://www.cambridge.org/resources/052182060X/
