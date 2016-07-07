#!/usr/bin/env bats

source ./environment
./clean.sh

@test "verify self-signed with self-generated key pair" {
  run ./generate.sh
  run ./sign.sh
  [ "$status" -eq 0 ]
  [ "$output" = "Verified OK" ]
}
