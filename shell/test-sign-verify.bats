#!/usr/bin/env bats

source ./environment
./clean.sh

@test "A verification should be succeeded with signature generated from pubKey" {
  run ./generate.sh
  run ./sign.sh
  [ "$status" -eq 0 ]
  [ "$output" = "Verified OK" ]
}
