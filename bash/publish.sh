#!/bin/bash

# Name of file with api
file="apiary.apib"

# Can be founded here: https://login.apiary.io/tokens
token="token_here"

# Name of project http://docs.<project>.apiary.io/
project="name"

echo "Caution! Current version of '$file' will be deployed to apiary. Proceed (y/n)?"

read -n 1 -s answer;
case "$answer" in
  y|Y) echo "Continue...";;
  *) echo "Stoping and Exit."; exit 0;;
esac

curl --include \
     --request POST \
     --header "Accept: text/html" \
     --header "Accept-Encoding: gzip, deflate" \
     --header "Authentication: Token $token" \
     --data-urlencode code@$file \
     --data-urlencode 'messageToSave=Saving blueprint from apiary-client' \
     "https://api.apiary.io/blueprint/publish/$project"

echo ""
echo "Publishing done"