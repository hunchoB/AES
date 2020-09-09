package sample;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class KeyGeneration implements Serializable {
    public static Map<String, SecretKey> keysMap = new HashMap<>();


    public static SecretKey generation(String keyName, int keySize) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
//        int keyBitSize = 256;

        keyGenerator.init(keySize, secureRandom);
        System.out.println("keyGen" + keySize);
        SecretKey secretKey = keyGenerator.generateKey();
        keysMap.put(keyName, secretKey);
        return secretKey;
    }

}
