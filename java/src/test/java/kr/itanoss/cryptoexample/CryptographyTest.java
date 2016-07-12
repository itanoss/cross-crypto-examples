package kr.itanoss.cryptoexample;

import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;

import static org.assertj.core.api.Assertions.assertThat;

public class CryptographyTest {

    private Cryptography cryptography;
    private KeyPair keyPair;

    @Before
    public void setUp() throws Exception {
        cryptography = new Cryptography();
        keyPair = cryptography.generateKeyPair();
    }

    @Test
    public void testSignAndVerify() throws Exception {
        String signature = cryptography.sign("Joonhyeok Im", keyPair.getPrivate());
        System.out.println("Signature: " + signature);

        boolean verification = cryptography.verify("Joonhyeok Im", signature, keyPair.getPublic());
        assertThat(verification).isTrue();
    }

    @Test
    public void testEncryptAndDecrypt() throws Exception {
        String encrypted = cryptography.encrypt("Joonhyeok Im", keyPair.getPublic());
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cryptography.decrypt(encrypted, keyPair.getPrivate());
        assertThat(decrypted).isEqualTo("Joonhyeok Im");
    }
}