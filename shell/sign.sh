#!/bin/bash

source $(dirname "$0")/environment

# Check Key pair
if [ ! -e "$TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME" ] || \
	[ ! -e "$TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME" ]; then
	echo "Error: Public key or private key does not exist."
  exit 1
fi

# For Java execution environment
PUBLIC_KEY_OPTS=
if [ "$1" == "--java" ]; then
	PUBLIC_KEY_OPTS="-keyform DER"
fi

# Create a signature with private key
openssl dgst -sha256 -sign $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME \
	-out $TARGET_DIRECTORY/$DATA_FILE.signature $DATA_DIRECTORY/$DATA_FILE

# Verify the signauture with public key
openssl dgst -sha256 -verify $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME \
	-signature $TARGET_DIRECTORY/$DATA_FILE.signature $DATA_DIRECTORY/$DATA_FILE \
	$PUBLIC_KEY_OPTS