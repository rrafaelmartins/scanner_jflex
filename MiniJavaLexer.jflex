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
"class"                { return symbol(sym.CLASS); }
"public"               { return symbol(sym.PUBLIC); }
"static"               { return symbol(sym.STATIC); }
"void"                 { return symbol(sym.VOID); }
"main"                 { return symbol(sym.MAIN); }
"String"               { return symbol(sym.STRING); }
"extends"              { return symbol(sym.EXTENDS); }
"return"               { return symbol(sym.RETURN); }
"int"                  { return symbol(sym.INT); }
"boolean"              { return symbol(sym.BOOLEAN); }
"if"                   { return symbol(sym.IF); }
"else"                 { return symbol(sym.ELSE); }
"while"                { return symbol(sym.WHILE); }
"true"                 { return symbol(sym.TRUE); }
"false"                { return symbol(sym.FALSE); }
"this"                 { return symbol(sym.THIS); }
"new"                  { return symbol(sym.NEW); }
"length"               { return symbol(sym.LENGTH); }
"System.out.println"   { return symbol(sym.PRINTLN); }

/* Delimitadores */
"{"                    { return symbol(sym.LBRACE); }
"}"                    { return symbol(sym.RBRACE); }
"("                    { return symbol(sym.LPAREN); }
")"                    { return symbol(sym.RPAREN); }
"["                    { return symbol(sym.LBRACKET); }
"]"                    { return symbol(sym.RBRACKET); }
";"                    { return symbol(sym.SEMI); }
","                    { return symbol(sym.COMMA); }
"."                    { return symbol(sym.DOT); }
"="                    { return symbol(sym.ASSIGN); }

/* Operadores */
"&&"                   { return symbol(sym.AND); }
"<"                    { return symbol(sym.LESS); }
"+"                    { return symbol(sym.PLUS); }
"-"                    { return symbol(sym.MINUS); }
"*"                    { return symbol(sym.TIMES); }
"!"                    { return symbol(sym.NOT); }

/* Literais inteiros */
{IntegerLiteral}       { return symbol(sym.INTEGER_LITERAL, Integer.valueOf(yytext())); }

/* Identificadores */
{Identifier}           { return symbol(sym.IDENTIFIER, yytext()); }

/* Tratamento de erros */
.                      { 
                         System.err.println(
                           "Erro léxico: caractere ilegal '" + yytext() +
                           "' na linha " + (yyline + 1) + ", coluna " + (yycolumn + 1));
                       }
