#!/bin/bash

source ./environment

# Target directory creation
if [ ! -d "$TARGET_DIRECTORY" ]; then
  mkdir -p $TARGET_DIRECTORY
fi

# Generate key pair
openssl genrsa -out $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME 2048
openssl rsa -in $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME -pubout -out $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME
