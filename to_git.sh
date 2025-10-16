#!/bin/bash

# compacta antes de subir
ARQUIVO="lib"
zip -r $ARQUIVO.zip $ARQUIVO
rm -r $ARQUIVO

# username e commit
read -p "Username GitHub: " NOME
read -p "Mensagem do commit: " COMMIT

# commit + push
git config --global user.email ""
git config --global user.name $NOME
git add .
git commit -m $COMMIT
git push

