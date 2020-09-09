package sample;

import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

public class WriteObject {
    public static void main(SecretKey secretKey, String nameKey) {
        try {

            FileOutputStream fos = new FileOutputStream("keys/" + nameKey);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(secretKey);
            oos.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
