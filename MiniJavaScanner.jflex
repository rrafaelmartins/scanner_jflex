/* Scanner de MiniJava */
%%

%class MiniJavaScanner
%unicode
%public
%standalone  
%line
%column

%%


[ \t\r\n\f]+          { /* ignorar */ }


"//".*                 { /* ignorar */ }


"/*"([^*]|\*+[^*/])*\*+"/"   { /* ignorar */ }

/* palavaras reservadas da lingaugem */
"class"                { System.out.println("CLASS"); }
"public"               { System.out.println("PUBLIC"); }
"static"               { System.out.println("STATIC"); }
"void"                 { System.out.println("VOID"); }
"main"                 { System.out.println("MAIN"); }
"String"               { System.out.println("STRING"); }
"extends"              { System.out.println("EXTENDS"); }
"return"               { System.out.println("RETURN"); }
"int"                  { System.out.println("INT"); }
"boolean"              { System.out.println("BOOLEAN"); }
"if"                   { System.out.println("IF"); }
"else"                 { System.out.println("ELSE"); }
"while"                { System.out.println("WHILE"); }
"true"                 { System.out.println("TRUE"); }
"false"                { System.out.println("FALSE"); }
"this"                 { System.out.println("THIS"); }
"new"                  { System.out.println("NEW"); }
"length"               { System.out.println("LENGTH"); }


"{"                    { System.out.println("LBRACE"); }
"}"                    { System.out.println("RBRACE"); }
"("                    { System.out.println("LPAREN"); }
")"                    { System.out.println("RPAREN"); }
"["                    { System.out.println("LBRACKET"); }
"]"                    { System.out.println("RBRACKET"); }
";"                    { System.out.println("SEMI"); }
","                    { System.out.println("COMMA"); }
"."                    { System.out.println("DOT"); }
"="                    { System.out.println("ASSIGN"); }


"&&"                   { System.out.println("AND"); }
"<"                    { System.out.println("LESS"); }
"+"                    { System.out.println("PLUS"); }
"-"                    { System.out.println("MINUS"); }
"*"                    { System.out.println("TIMES"); }
"!"                    { System.out.println("NOT"); }


[0-9]+                 { System.out.println("INTEGER_LITERAL(" + yytext() + ")"); }


[A-Za-z][A-Za-z0-9_]*  { System.out.println("IDENTIFIER(" + yytext() + ")"); }

/* error handler */
.                      { System.err.println(
                           "Erro léxico: caractere ilegal '" + yytext() +
                           "' na linha " + yyline + ", coluna " + yycolumn);
                         }