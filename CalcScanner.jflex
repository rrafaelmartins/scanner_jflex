/* Scanner da Calculadora */
%%

%class CalcScanner
%unicode
%public
%standalone
%line
%column

%%


[ \t\r\n\f]+          { /* ignorar */ }


"("                   { System.out.println("LPAREN"); }
")"                   { System.out.println("RPAREN"); }


"**"                  { System.out.println("POW"); }
"//"                  { System.out.println("DIV_INT"); }
"*"                   { System.out.println("TIMES"); }
"/"                   { System.out.println("DIV"); }
"+"                   { System.out.println("PLUS"); }
"-"                   { System.out.println("MINUS"); }

/* numeros float */
[0-9]+"."[0-9]+       { System.out.println("FLOAT(" + yytext() + ")"); }

/* numeros ints */
[0-9]+                { System.out.println("INT(" + yytext() + ")"); }

/* error handler */
.                     { System.err.println(
                          "Erro léxico: símbolo inválido '" + yytext() +
                          "' na linha " + yyline + ", coluna " + yycolumn);
                        }