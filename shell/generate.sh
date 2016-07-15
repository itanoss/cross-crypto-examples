#!/bin/bash

source $(dirname "$0")/environment

# For Java execution environment
PUBLIC_KEY_OPTS=
if [ "$1" == "--java" ]; then
	PUBLIC_KEY_OPTS="-outform DER"
fi

function check_error() {
	if [ ! $? -eq 0 ]; then
		echo "Error"
		exit $?
	fi
}

# Target directory creation
if [ ! -d "$TARGET_DIRECTORY" ]; then
  mkdir -p $TARGET_DIRECTORY
fi

# Generate key pair
openssl genrsa -out $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME 2048
check_error
echo "Private key generated: $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME"

openssl rsa -in $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME -pubout \
	-out $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME $PUBLIC_KEY_OPTS
check_error
echo "Public key generated: $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME"
