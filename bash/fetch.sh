#!/bin/bash

# Name of file with api
file="apiary.apib.fetched"

# Can be founded here: https://login.apiary.io/tokens
token="token_here"

# Name of project http://docs.<project>.apiary.io/
project="name"

curl --request GET \
     --header "Accept: text/html" \
     --header "Accept-Encoding: gzip, deflate" \
     --header "Authentication: Token $token" \
     "https://api.apiary.io/blueprint/get/$project" \
     > $file

echo ""
echo "Fetching done"