/* Scanner de MiniJava - Integrado com CUP */
package minijava;

import java_cup.runtime.*;

%%

%class MiniJavaLexer
%unicode
%public
%cup
%line
%column

%{
    /* Método auxiliar para criar símbolos */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

/* Definições de macros */
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Identifier     = [A-Za-z][A-Za-z0-9_]*
IntegerLiteral = [0-9]+

%%

/* Espaços em branco - ignorar */
{WhiteSpace}+          { /* ignorar */ }

/* Comentários de linha */
"//".*                 { /* ignorar */ }

/* Comentários de bloco */
"/*"([^*]|\*+[^*/])*\*+"/"   { /* ignorar */ }

/* Palavras reservadas da linguagem */
"class"                { return symbol(Sym.CLASS); }
"public"               { return symbol(Sym.PUBLIC); }
"static"               { return symbol(Sym.STATIC); }
"void"                 { return symbol(Sym.VOID); }
"main"                 { return symbol(Sym.MAIN); }
"String"               { return symbol(Sym.STRING); }
"extends"              { return symbol(Sym.EXTENDS); }
"return"               { return symbol(Sym.RETURN); }
"int"                  { return symbol(Sym.INT); }
"boolean"              { return symbol(Sym.BOOLEAN); }
"if"                   { return symbol(Sym.IF); }
"else"                 { return symbol(Sym.ELSE); }
"while"                { return symbol(Sym.WHILE); }
"true"                 { return symbol(Sym.TRUE); }
"false"                { return symbol(Sym.FALSE); }
"this"                 { return symbol(Sym.THIS); }
"new"                  { return symbol(Sym.NEW); }
"length"               { return symbol(Sym.LENGTH); }
"System.out.println"   { return symbol(Sym.PRINTLN); }

/* Delimitadores */
"{"                    { return symbol(Sym.LBRACE); }
"}"                    { return symbol(Sym.RBRACE); }
"("                    { return symbol(Sym.LPAREN); }
")"                    { return symbol(Sym.RPAREN); }
"["                    { return symbol(Sym.LBRACKET); }
"]"                    { return symbol(Sym.RBRACKET); }
";"                    { return symbol(Sym.SEMI); }
","                    { return symbol(Sym.COMMA); }
"."                    { return symbol(Sym.DOT); }
"="                    { return symbol(Sym.ASSIGN); }

/* Operadores */
"&&"                   { return symbol(Sym.AND); }
"<"                    { return symbol(Sym.LESS); }
"+"                    { return symbol(Sym.PLUS); }
"-"                    { return symbol(Sym.MINUS); }
"*"                    { return symbol(Sym.TIMES); }
"!"                    { return symbol(Sym.NOT); }

/* Literais inteiros */
{IntegerLiteral}       { return symbol(Sym.INTEGER_LITERAL, Integer.valueOf(yytext())); }

/* Identificadores */
{Identifier}           { return symbol(Sym.IDENTIFIER, yytext()); }
/* EOF */
<<EOF>>                { return symbol(Sym.EOF); }
/* Tratamento de erros */
.                      { 
                         System.err.println(
                           "Erro léxico: caractere ilegal '" + yytext() +
                           "' na linha " + (yyline + 1) + ", coluna " + (yycolumn + 1));
                       }