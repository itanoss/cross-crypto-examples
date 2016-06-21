package kr.itanoss.cryptoexample;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Main {
    private byte[] privateKeyBytes;
    private byte[] publicKeyBytes;

    public Main() throws NoSuchAlgorithmException {
        generateKeyPair();
    }

    private void generateKeyPair() throws NoSuchAlgorithmException {
        final KeyPairGenerator generator = initializeGenerator();

        final KeyPair keyPair = generator.generateKeyPair();

        this.privateKeyBytes = keyPair.getPrivate().getEncoded();
        this.publicKeyBytes = keyPair.getPublic().getEncoded();
    }

    private KeyPairGenerator initializeGenerator() throws NoSuchAlgorithmException {
        final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator;
    }

    private Signature initializeSignature() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA256withRSA");
    }

    private PrivateKey getPrivateKey() throws SecurityException, NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    private PublicKey getPublicKey() throws SecurityException, NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    public byte[] sign(String data) throws InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchAlgorithmException {
        final PrivateKey privateKey = getPrivateKey();
        final Signature signature = initializeSignature();

        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));

        return signature.sign();
    }

    public boolean verify(String original, byte[] signatureBytes) throws SecurityException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        final PublicKey publicKey = getPublicKey();
        final Signature signature = initializeSignature();

        signature.initVerify(publicKey);
        signature.update(original.getBytes(StandardCharsets.UTF_8));
        return signature.verify(signatureBytes);
    }
}

