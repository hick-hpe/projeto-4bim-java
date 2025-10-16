#!/bin/bash

ARQUIVO_ZIP="lib.zip"

# descompactar
if ls | grep -q $ARQUIVO_ZIP; then
    echo "Descompactando..."
    unzip $ARQUIVO_ZIP
    rm $ARQUIVO_ZIP
fi

# compilar    
javac --module-path lib --add-modules javafx.controls -d out src/*.java

# executar
java --module-path lib --add-modules javafx.controls -cp out Main


