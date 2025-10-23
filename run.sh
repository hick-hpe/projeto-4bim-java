#!/bin/bash

# ==========================
# Configurações
# ==========================
JAVA_FX_LIB="lib"
JDBC_DRIVER="lib/mysql-connector-java-9.4.0.jar"
OUT_DIR="out"

# ==========================
# Criar pasta de saída
# ==========================
mkdir -p "$OUT_DIR"

# ==========================
# Compilação
# ==========================
echo "Compilando fontes..."

javac --module-path "$JAVA_FX_LIB" --add-modules javafx.controls \
      -cp "$JDBC_DRIVER" -d "$OUT_DIR" src/model/*.java src/*.java

if [ $? -ne 0 ]; then
    echo "Erro na compilação!"
    exit 1
fi

echo "Compilação concluída."

# ==========================
# Execução
# ==========================
echo "Executando aplicação..."

java --module-path "$JAVA_FX_LIB" --add-modules javafx.controls \
     -cp "$OUT_DIR:$JDBC_DRIVER" Main

if [ $? -ne 0 ]; then
    echo "Erro na execução!"
    exit 1
fi

echo "Aplicação finalizada."
