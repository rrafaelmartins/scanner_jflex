#!/bin/bash

# =============================================================================
# Script para Executar o Compilador MiniJava
# =============================================================================

if [ $# -eq 0 ]; then
    echo "Uso: ./run.sh <arquivo.java>"
    echo "Exemplo: ./run.sh exemplo_minijava.java"
    exit 1
fi

# Diretório base
BASE_DIR=$(dirname "$0")
cd "$BASE_DIR"

# Verificar se o build foi feito
if [ ! -d "build/minijava" ]; then
    echo "ERRO: Build não encontrado. Execute ./build.sh primeiro."
    exit 1
fi

# Executar o compilador
java -cp "build:build/java-cup-11b-runtime.jar" minijava.Main "$1"
