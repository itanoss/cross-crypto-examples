#!/bin/bash

source ./environment

# Check Key pair
if [ ! -e "$TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME" ] || \
	[ ! -e "$TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME" ]; then
	echo "Public key or private key does not exist."
  exit 1
fi

# Create a signature with private key
openssl dgst -sha256 -sign $TARGET_DIRECTORY/$PRIVATE_KEY_FILENAME \
	-out $TARGET_DIRECTORY/$DATA_FILE.sign.sha256 $DATA_DIRECTORY/$DATA_FILE

# Verify the signauture with public key
openssl dgst -sha256 -verify $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME \
	-signature $TARGET_DIRECTORY/$DATA_FILE.sign.sha256 $DATA_DIRECTORY/$DATA_FILE