package org.easystructure.domain.valueobject.system;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.easystructure.domain.valueobject.ValueObjectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe que manipula a senha, encriptando/desencriptando a senha.
 * 
 * @author Fernando Romulo da Silva
 */
// https://stackoverflow.com/questions/43101699/is-this-the-best-secure-method-for-encryption-in-java
// https://www.owasp.org/index.php/Using_the_Java_Cryptographic_Extensions#AES_Encryption_and_Decryption
// https://commons.apache.org/proper/commons-crypto/xref-test/org/apache/commons/crypto/examples/CipherByteBufferExample.html
// https://shiro.apache.org/get-started.html
// https://www.owasp.org/index.php/Using_the_Java_Cryptographic_Extensions
// http://commons.apache.org/proper/commons-crypto/xref-test/org/apache/commons/crypto/examples/CipherByteArrayExample.html
// https://www.google.com.br/search?q=best+java+encryption+library&oq=best+java+encr&aqs=chrome.1.69i57j0l4.6911j0j7&sourceid=chrome&ie=UTF-8#q=best+java+encryption+library&tbs=qdr:y
public class PasswordHandler implements ValueObjectHandler<String> {

    public static final String ALGORITHM_PASSWORD_PROPERTY = "encryption.password.algorithm";

    public static final String ALGORITHM_KEY_PROPERTY = "encryption.key.algorithm";

    public static final String KEY_PROPERTY = "encryption.key";

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHandler.class);

    private static final String DEFAULT_ALGORITHM_PASSWORD_ENCRYPTION = "AES/ECB/PKCS5Padding"; // DES/CBC/PKCS5Padding

    private static final String DEFAULT_ALGORITHM_KEY_ENCRYPTION = "AES";

    private static final String DEFAULT_KEY = "MySuperSecretKey";

    private static final Properties PASSWORD_PROPERTIES = new Properties();

    private static String ALGORITHM_PASSWORD_ENCRYPTION;

    private static String ALGORITHM_KEY_ENCRYPTION;

    private static byte[] KEY;

    static {

        try {
            PASSWORD_PROPERTIES.load(PasswordHandler.class.getClassLoader().getResourceAsStream("security.properties"));
        } catch (final Exception ex) {
            LOGGER.warn("Could not load properties file 'security.properties' using unsecure encryption key.");

            PASSWORD_PROPERTIES.put(ALGORITHM_PASSWORD_PROPERTY, DEFAULT_ALGORITHM_PASSWORD_ENCRYPTION);
            PASSWORD_PROPERTIES.put(ALGORITHM_KEY_PROPERTY, DEFAULT_ALGORITHM_KEY_ENCRYPTION);
            PASSWORD_PROPERTIES.put(KEY_PROPERTY, DEFAULT_KEY);
        }

        ALGORITHM_PASSWORD_ENCRYPTION = (String) PASSWORD_PROPERTIES.get(ALGORITHM_PASSWORD_PROPERTY);
        ALGORITHM_KEY_ENCRYPTION = (String) PASSWORD_PROPERTIES.get(ALGORITHM_KEY_PROPERTY);
        KEY = ((String) PASSWORD_PROPERTIES.get(KEY_PROPERTY)).getBytes();
    }

    @Override
    public String handle(final String value) {
        return encrypt2(value);
    }
    
    public String unHandle(final String value){
        return descrypt2(value);
    }
    

    public String encrypt1(final String value) {

        try {
            final Key key = new SecretKeySpec(KEY, ALGORITHM_KEY_ENCRYPTION);

            final Cipher cipher = Cipher.getInstance(ALGORITHM_PASSWORD_ENCRYPTION);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return new String(Base64.encodeBase64(cipher.doFinal(value.getBytes())), "UTF-8");

        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        }
    }

    public String descrypt1(final String value) {

        try {
            final Key key = new SecretKeySpec(KEY, ALGORITHM_KEY_ENCRYPTION);

            final Cipher cipher = Cipher.getInstance(ALGORITHM_PASSWORD_ENCRYPTION);

            cipher.init(Cipher.DECRYPT_MODE, key);

            final String decrypted = new String(cipher.doFinal(Base64.decodeBase64(value.getBytes("UTF-8"))));

            return decrypted;
        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        }
    }

    public String encrypt2(final String value) {

        try {
            // Step 1. Generate a DES key using KeyGenerator
            final KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            final SecretKey secretKey = keyGen.generateKey();

            // Step2. Create a Cipher by specifying the following parameters
            // a. Algorithm name - here it is DES
            // b. Mode - here it is CBC
            // c. Padding - PKCS5Padding
            /* Must specify the mode explicitly as most JCE providers default to ECB mode!! */
            final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // Step 3. Initialize the Cipher for Encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Step 4. Encrypt the Data
            // 1. Declare / Initialize the Data. Here the data is of type String
            // 2. Convert the Input Text to Bytes 3. Encrypt the bytes using doFinal method
            final byte[] byteDataToEncrypt = value.getBytes("UTF-8");
            final byte[] byteCipherText = cipher.doFinal(byteDataToEncrypt);

            return Base64.encodeBase64String(byteCipherText);

        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        }
    }

    public String descrypt2(final String value) {

        try {
            // Step 1. Generate a DES key using KeyGenerator
            final KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            final SecretKey secretKey = keyGen.generateKey();

            // Step2. Create a Cipher by specifying the following parameters
            // a. Algorithm name - here it is DES
            // b. Mode - here it is CBC
            // c. Padding - PKCS5Padding
            /* Must specify the mode explicitly as most JCE providers default to ECB mode!! */
            final Cipher desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // // Step 3. Initialize the Cipher for Desencryption
            // desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //
            // // Step 3. Decrypt the Data
            // // 1. Initialize the Cipher for Decryption
            // // 2. Decrypt the cipher bytes using doFinal method
            desCipher.init(Cipher.DECRYPT_MODE, secretKey, desCipher.getParameters());
            final byte[] byteDecryptedText = desCipher.doFinal(value.getBytes("UTF-8"));
            return new String(byteDecryptedText);

        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        } catch (final InvalidAlgorithmParameterException ex) {
            throw new IllegalStateException("Illegal Algorithm Parameter", ex);
        }
    }

    public String encrypt3(final String value) {

        try {
            // Step 1. Generate an AES key using KeyGenerator Initialize the keysize to 128 bits (16 bytes)
            final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            final SecretKey secretKey = keyGen.generateKey();

            // Step 2. Generate an Initialization Vector (IV)
            // a. Use SecureRandom to generate random bits The size of the IV matches the blocksize of the cipher (128 bits for AES)
            // b. Construct the appropriate IvParameterSpec object for the data to pass to Cipher's init() method
            // change this as desired for the security level you want
            final int AES_KEYLENGTH = 128;
            // Save the IV bytes or send it in plaintext with the encrypted data so you can decrypt the data later
            final byte[] iv = new byte[AES_KEYLENGTH / 8];
            final SecureRandom prng = new SecureRandom();
            prng.nextBytes(iv);

            // Step 3. Decrypt the Data
            // a. Initialize a new instance of Cipher for Decryption (normally don't reuse the same object)
            // Be sure to obtain the same IV bytes for CBC mode.
            // b. Decrypt the cipher bytes using doFinal method
            // Must specify the mode explicitly as most JCE providers default to ECB mode!!
            final Cipher aesCipherForEncryption = Cipher.getInstance("AES/ECB/PKCS5Padding");

            aesCipherForEncryption.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            final byte[] byteDataToEncrypt = value.getBytes("UTF-8");
            final byte[] byteCipherText = aesCipherForEncryption.doFinal(byteDataToEncrypt);
            // b64 is done differently on Android
            return new String(Base64.decodeBase64(byteCipherText));

        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        } catch (final InvalidAlgorithmParameterException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public String descrypt3(final String value) {

        try {

            // Step 1. Generate an AES key using KeyGenerator Initialize the keysize to 128 bits (16 bytes)
            final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            final SecretKey secretKey = keyGen.generateKey();

            // Step 2. Generate an Initialization Vector (IV)
            // a. Use SecureRandom to generate random bits The size of the IV matches the blocksize of the cipher (128 bits for AES)
            // b. Construct the appropriate IvParameterSpec object for the data to pass to Cipher's init() method
            // change this as desired for the security level you want
            final int AES_KEYLENGTH = 128;
            // Save the IV bytes or send it in plaintext with the encrypted data so you can decrypt the data later
            final byte[] iv = new byte[AES_KEYLENGTH / 8];
            final SecureRandom prng = new SecureRandom();
            prng.nextBytes(iv);

            // Step 3. Create a Cipher by specifying the following parameters
            // a. Algorithm name - here it is AES
            // b. Mode - here it is CBC mode c. Padding - e.g. PKCS7 or PKCS5
            // Must specify the mode explicitly as most JCE providers default to ECB mode!!
            final Cipher aesCipherForEncryption = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Step 4. Initialize the Cipher for Encryption
//            aesCipherForEncryption.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
//
//            final byte[] byteDataToEncrypt = value.getBytes("UTF-8");
//            final byte[] byteCipherText = aesCipherForEncryption.doFinal(byteDataToEncrypt);

            // Decrypt the Data
            // a. Initialize a new instance of Cipher for Decryption (normally don't reuse the same object) Be sure to obtain the same IV bytes for CBC mode.
            // b. Decrypt the cipher bytes using doFinal method
            // Must specify the mode explicitly as most JCE providers default to ECB mode!!
            final Cipher aesCipherForDecryption = Cipher.getInstance("AES/ECB/PKCS5Padding");

            aesCipherForDecryption.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            final byte[] byteDecryptedText = aesCipherForDecryption.doFinal(value.getBytes("UTF-8"));

            return new String(byteDecryptedText);
        } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalStateException("No Such Algorithm exists", ex);
        } catch (final NoSuchPaddingException ex) {
            throw new IllegalStateException("No Such Padding exists", ex);
        } catch (final InvalidKeyException ex) {
            throw new IllegalStateException("Invalid Key", ex);
        } catch (final BadPaddingException ex) {
            throw new IllegalStateException("Bad Padding", ex);
        } catch (final IllegalBlockSizeException ex) {
            throw new IllegalStateException("Illegal Block Size", ex);
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException("Illegal Encoding", ex);
        } catch (final InvalidAlgorithmParameterException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
