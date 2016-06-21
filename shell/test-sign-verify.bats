#!/usr/bin/env bats

TARGET_DIRECTORY=target
PRIVATE_KEY_FILENAME=privKey.pem
PUBLIC_KEY_FILENAME=pubKey.pem
DATA_FILE=../helloworld.txt

@test "A verification should be succeeded with signature generated from pubKey" {
  run ./sign.sh $DATA_FILE $TARGET_DIRECTORY/$PUBLIC_KEY_FILENAME
  [ "$status" -eq 0]
}
