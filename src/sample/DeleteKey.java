package sample;

import java.io.File;

public class DeleteKey {
    public static void main(String nameOfKey){
        File file = new File(nameOfKey);
        file.delete();

    }
}
// Дописать чтобы было без пути!!!!! а тут прописать папку