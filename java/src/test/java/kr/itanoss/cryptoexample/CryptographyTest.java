package kr.itanoss.cryptoexample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;

import static kr.itanoss.cryptoexample.Utility.execShell;
import static kr.itanoss.cryptoexample.Utility.readBytes;
import static org.assertj.core.api.Assertions.assertThat;

public class CryptographyTest {

    private Cryptography cryptography;
    private KeyPair keyPair;

    @Before
    public void setUp() throws Exception {
        cryptography = new Cryptography();
        keyPair = cryptography.generateKeyPair();
    }

    @Before
    @After
    public void cleanUp() throws Exception {
        execShell("../shell/clean.sh");
    }

    @Test
    public void testSignInJavaAndVerifyInJava() throws Exception {
        String signature = cryptography.sign("Joonhyeok Im", keyPair.getPrivate());
        System.out.println("Signature: " + signature);

        boolean verification = cryptography.verify("Joonhyeok Im", keyPair.getPublic(), Base64.getDecoder().decode(signature));
        assertThat(verification).isTrue();
    }

    @Test
    public void testEncryptInJavaAndDecryptInJava() throws Exception {
        String encrypted = cryptography.encrypt("Joonhyeok Im", keyPair.getPublic());
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cryptography.decrypt(encrypted, keyPair.getPrivate());
        assertThat(decrypted).isEqualTo("Joonhyeok Im");
    }

    @Test
    public void testSignInCommandLineAndVerifyInJava() throws Exception {
        execShell("../shell/generate.sh --java");
        execShell("../shell/sign.sh --java");
        PublicKey publicKey = cryptography.getPublicKey("target/pubKey.pem");

        boolean verification = cryptography.verify(new String(readBytes("../helloworld.txt")), publicKey, readBytes("target/helloworld.txt.signature"));
        assertThat(verification).isTrue();
    }
}