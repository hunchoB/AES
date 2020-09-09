package sample;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AesCipher {

    private static final String INIT_VECTOR = "ItIsOurBigSecret";

    public static String encrypt (SecretKey secretKey, String plainText){
        try {
            if (secretKey.isDestroyed()) {
                throw new Exception("secret key's length must bw 128,192 or 256 bits");

            }

            IvParameterSpec ivParameterSpec =new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
//            SecretKeySpec secretKeySpec =new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Throwable cause) {
            System.out.println(cause.getMessage());
        }
        return null;
    }

    public static String decrypt (SecretKey secretKey, String cipherText){
        byte [] encrypted = Base64.getDecoder().decode(cipherText);

        try {
            if (secretKey.isDestroyed()) {
                throw new Exception("secret key's length must bw 128,192 or 256 bits");

            }

            IvParameterSpec ivParameterSpec =new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
//            SecretKeySpec secretKeySpec =new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);


//            byte[] decrypted = cipher.doFinal(cipherText.getBytes("UTF-8"));
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        return null;
    }

}
