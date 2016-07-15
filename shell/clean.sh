#!/bin/bash

source $(dirname "$0")/environment

if [ -n "$1" ]; then
	TARGET_DIRECTORY=$1
fi

rm -rf $TARGET_DIRECTORY