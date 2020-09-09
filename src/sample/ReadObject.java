package sample;

import javax.crypto.SecretKey;
import java.io.*;

public class ReadObject {
    public static SecretKey main (String path){
        try {

            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            SecretKey secretKey = (SecretKey) ois.readObject();
            ois.close();
            return secretKey;
        } catch (
                IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
