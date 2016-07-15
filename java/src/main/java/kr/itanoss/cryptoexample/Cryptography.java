package kr.itanoss.cryptoexample;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static kr.itanoss.cryptoexample.Utility.readBytes;

public class Cryptography {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Cryptography cryptography = new Cryptography();

        if(args.length == 0 || "".equals(args[0])) {
            // TODO Show usage
        } else if ("generate".equals(args[0])) {
                // Java Cryptography generate -pubout <filename> -privout <filename>
                final File publicKeyFile = extractFileName("-pubout", args);
                final File privateKeyFile = extractFileName("-privout", args);

            KeyPair keyPair = cryptography.generateKeyPair();
//            Files.write()

        }
    }

    private static File extractFileName(String option, String[] args) {
        for (int i = 1; i < args.length; i++) {
            if(option.equals(args[i])) {
                return new File(args[i+1]);
            }
        }
        throw new InvalidParameterException();
    }




    private KeyPairGenerator initializeGenerator() throws NoSuchAlgorithmException {
        final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator;
    }

    PublicKey getPublicKey(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = readBytes(filepath);

        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
    }

    private Signature initializeSignature() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA256withRSA");
    }

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = initializeGenerator();
        return generator.generateKeyPair();
    }

    public String sign(String data, PrivateKey privateKey) throws InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchAlgorithmException {
        final Signature signature = initializeSignature();

        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public boolean verify(String original, PublicKey publicKey, byte[] signatureBytes) throws SecurityException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        final Signature signature = initializeSignature();

        signature.initVerify(publicKey);
        signature.update(original.getBytes(StandardCharsets.UTF_8));
        return signature.verify(signatureBytes);
    }

    public String encrypt(String original, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = initializeCipher();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(original.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String base64EncodedEncrypted, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] encryptedBytes = Base64.getDecoder().decode(base64EncodedEncrypted);

        Cipher cipher = initializeCipher();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedBytes));
    }

    private Cipher initializeCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        return cipher;
    }
}
