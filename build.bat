@echo off
REM =============================================================================
REM Script de Build para o Compilador MiniJava (Windows)
REM Utiliza JFlex (análise léxica) e CUP (análise sintática)
REM =============================================================================

echo ==============================================
echo Build do Compilador MiniJava
echo ==============================================

REM Criar diretórios necessários
if not exist build mkdir build
if not exist minijava mkdir minijava

REM Verificar se as bibliotecas existem
if not exist "lib\jflex-full-1.9.1.jar" (
    echo ERRO: JFlex nao encontrado em lib\jflex-full-1.9.1.jar
    echo Baixe de: https://jflex.de/download.html
    exit /b 1
)

if not exist "lib\java-cup-11b.jar" (
    echo ERRO: CUP nao encontrado em lib\java-cup-11b.jar
    echo Baixe de: http://www2.cs.tum.edu/projects/cup/
    exit /b 1
)

REM Passo 1: Gerar o parser com CUP
echo.
echo [1/4] Gerando parser com CUP...
java -jar lib\java-cup-11b.jar -destdir minijava -parser parser -symbols sym MiniJavaParser.cup
if errorlevel 1 (
    echo ERRO: Falha ao gerar o parser
    exit /b 1
)
echo       Parser gerado: minijava\parser.java e minijava\sym.java

REM Passo 2: Gerar o scanner com JFlex
echo.
echo [2/4] Gerando scanner com JFlex...
java -jar lib\jflex-full-1.9.1.jar -d minijava MiniJavaLexer.jflex
if errorlevel 1 (
    echo ERRO: Falha ao gerar o scanner
    exit /b 1
)
echo       Scanner gerado: minijava\MiniJavaLexer.java

REM Passo 3: Compilar todos os arquivos Java
echo.
echo [3/4] Compilando arquivos Java...
javac -cp "lib\java-cup-11b-runtime.jar;." -d build minijava\*.java
if errorlevel 1 (
    echo ERRO: Falha na compilacao
    exit /b 1
)
echo       Compilacao concluida

REM Passo 4: Criar estrutura final
echo.
echo [4/4] Finalizando build...
copy lib\java-cup-11b-runtime.jar build\ >nul
echo       Build concluido com sucesso!

echo.
echo ==============================================
echo Para executar o compilador:
echo   run.bat arquivo.java
echo.
echo Exemplo:
echo   run.bat exemplo_minijava.java
echo ==============================================
