package calc;

import java_cup.runtime.*;

%%

%class CalcLexer
%unicode
%public
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

%%

[ \t\r\n\f]+          { /* ignorar */ }

"("                   { return symbol(Sym.LPAREN); }
")"                   { return symbol(Sym.RPAREN); }

"**"                  { return symbol(Sym.POW); }
"//"                  { return symbol(Sym.DIV_INT); }
"*"                   { return symbol(Sym.TIMES); }
"/"                   { return symbol(Sym.DIV); }
"+"                   { return symbol(Sym.PLUS); }
"-"                   { return symbol(Sym.MINUS); }

[0-9]+"."[0-9]+       { return symbol(Sym.FLOAT, Double.valueOf(yytext())); }

[0-9]+                { return symbol(Sym.INT, Integer.valueOf(yytext())); }

<<EOF>>               { return symbol(Sym.EOF); }

.                     { System.err.println("Erro lexico: simbolo invalido '" + yytext() + "' na linha " + (yyline + 1) + ", coluna " + (yycolumn + 1)); }