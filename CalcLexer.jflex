package calc;

import java_cup.runtime.;

%%

%class CalcLexer
%unicode
%public
%cup
%line
%column

%{
    private Symbol symbol(int type, String name) {
        System.out.println("TOKEN: " + name + " (linha " + (yyline + 1) + ", coluna " + (yycolumn + 1) + ")");
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, String name, Object value) {
        System.out.println("TOKEN: " + name + "(" + value + ") (linha " + (yyline + 1) + ", coluna " + (yycolumn + 1) + ")");
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

%%

[ \t\r\n\f]+          {  }

"("                   { return symbol(Sym.LPAREN, "LPAREN"); }
")"                   { return symbol(Sym.RPAREN, "RPAREN"); }

"**"                  { return symbol(Sym.POW, "POW"); }
"//"                  { return symbol(Sym.DIV_INT, "DIV_INT"); }
"*"                   { return symbol(Sym.TIMES, "TIMES"); }
"/"                   { return symbol(Sym.DIV, "DIV"); }
"+"                   { return symbol(Sym.PLUS, "PLUS"); }
"-"                   { return symbol(Sym.MINUS, "MINUS"); }

[0-9]+"."[0-9]+       { return symbol(Sym.FLOAT, "FLOAT", Double.valueOf(yytext())); }

[0-9]+                { return symbol(Sym.INT, "INT", Integer.valueOf(yytext())); }

<<EOF>>               { System.out.println("TOKEN: EOF"); return new Symbol(Sym.EOF); }

.                     { System.err.println("ERRO LEXICO: simbolo invalido '" + yytext() + "' na linha " + (yyline + 1) + ", coluna " + (yycolumn + 1)); }