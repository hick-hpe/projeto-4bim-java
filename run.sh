#!/bin/bash

# Caminhos
JAVA_FX_LIB="lib"
JDBC_DRIVER="lib/mysql-connector-java-8.4.0.jar"
OUT_DIR="out"

# Criar pasta de saída se não existir
mkdir -p "$OUT_DIR"

echo "Compilando fontes..."

javac --module-path "$JAVA_FX_LIB" --add-modules javafx.controls \
      -cp "$JDBC_DRIVER" -d "$OUT_DIR" model/*.java src/*.java

if [ $? -ne 0 ]; then
    echo "Erro na compilação"
    exit 1
fi

echo "Executando aplicação..."

java --module-path "$JAVA_FX_LIB" --add-modules javafx.controls \
     -cp "$OUT_DIR:$JDBC_DRIVER" src.Main
