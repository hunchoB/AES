package sample;

import java.io.File;

public class DeleteKey {
    public static void main(String nameOfKey){
        File file = new File("keys/"+nameOfKey);
        file.delete();

    }
}
