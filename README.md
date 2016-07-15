# Cryptogrpahic example for various languages

[![Build Status](https://travis-ci.org/itanoss/cross-crypto-examples.svg)](https://travis-ci.org/itanoss/cross-crypto-examples)

Since security become to hot potato, many developers including me should consider about cryptography. Cryptograhpic technology is very popular these days such as SSL, TLS, asymmetric key pair and so on.

You can see basic usage of cryptography in this repository:

  - Generate key pair
  - Encrypt with public key
  - Decrypt the encrypted one with private key
  - Generate a signture with private key
  - Verify the signautre with public key

And you can see a variety of examples like the following languages:

  - Bash script
  - Java
  - _To be more_

## Test

For each language base, there are elborate unit tests to cover the following cases.
  - Create a signature within the language self and also verify within itself.
  - Create a signature within the language self and verify it with all of other language bases.
  - Encrypt within the language self and also decrypt within itself.
  - Encrypt within the language self and decrypt it with all of other language bases.

## License
MIT
