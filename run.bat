@echo off
REM =============================================================================
REM Script para Executar o Compilador MiniJava (Windows)
REM =============================================================================

if "%1"=="" (
    echo Uso: run.bat arquivo.java
    echo Exemplo: run.bat exemplo_minijava.java
    exit /b 1
)

REM Verificar se o build foi feito
if not exist "build\minijava" (
    echo ERRO: Build nao encontrado. Execute build.bat primeiro.
    exit /b 1
)

REM Executar o compilador
java -cp "build;build\java-cup-11b-runtime.jar" minijava.Main %1
