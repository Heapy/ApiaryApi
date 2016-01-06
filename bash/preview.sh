#!/bin/bash

# Name of file with api
file="apiary.apib"
tmp_file=$file"_tmp"

# Because Apiary doesn't accept files with CRLF line endings. Should use LF insted.
tr "\r\n" "\n" < $file > $tmp_file

curl --request POST \
     --header "Content-Type: text/plain; charset=utf-8" \
     --header "Accept: text/html" \
     --data-binary @$tmp_file \
     'http://api.apiary.io/blueprint/generate' \
     > preview.html

rm $tmp_file

echo ""
echo "Preview created"