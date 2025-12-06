#!/bin/bash

# =============================================================================
# Script de Build para o Compilador MiniJava
# Utiliza JFlex (análise léxica) e CUP (análise sintática)
# =============================================================================

echo "=============================================="
echo "Build do Compilador MiniJava"
echo "=============================================="

# Diretório base
BASE_DIR=$(dirname "$0")
cd "$BASE_DIR"

# Criar diretórios necessários
mkdir -p build
mkdir -p minijava

# Verificar se as bibliotecas existem
if [ ! -f "lib/jflex-full-1.9.1.jar" ]; then
    echo "ERRO: JFlex não encontrado em lib/jflex-full-1.9.1.jar"
    echo "Baixe de: https://jflex.de/download.html"
    exit 1
fi

if [ ! -f "lib/java-cup-11b.jar" ]; then
    echo "ERRO: CUP não encontrado em lib/java-cup-11b.jar"
    echo "Baixe de: http://www2.cs.tum.edu/projects/cup/"
    exit 1
fi

# Passo 1: Gerar o parser com CUP
echo ""
echo "[1/4] Gerando parser com CUP..."
java -jar lib/java-cup-11b.jar -destdir minijava -parser parser -symbols sym MiniJavaParser.cup

if [ $? -ne 0 ]; then
    echo "ERRO: Falha ao gerar o parser"
    exit 1
fi
echo "      Parser gerado: minijava/parser.java e minijava/sym.java"

# Passo 2: Gerar o scanner com JFlex
echo ""
echo "[2/4] Gerando scanner com JFlex..."
java -jar lib/jflex-full-1.9.1.jar -d minijava MiniJavaLexer.jflex

if [ $? -ne 0 ]; then
    echo "ERRO: Falha ao gerar o scanner"
    exit 1
fi
echo "      Scanner gerado: minijava/MiniJavaLexer.java"

# Passo 3: Compilar todos os arquivos Java
echo ""
echo "[3/4] Compilando arquivos Java..."
javac -cp "lib/java-cup-11b-runtime.jar:." -d build minijava/*.java

if [ $? -ne 0 ]; then
    echo "ERRO: Falha na compilação"
    exit 1
fi
echo "      Compilação concluída"

# Passo 4: Criar estrutura final
echo ""
echo "[4/4] Finalizando build..."
cp -r lib/java-cup-11b-runtime.jar build/
echo "      Build concluído com sucesso!"

echo ""
echo "=============================================="
echo "Para executar o compilador:"
echo "  ./run.sh <arquivo.java>"
echo ""
echo "Exemplo:"
echo "  ./run.sh exemplo_minijava.java"
echo "=============================================="
