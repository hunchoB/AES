package sample;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenFile {
    public static File file;

    public static String main() throws IOException {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        Path path = null;
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
            path = file.getAbsoluteFile().toPath();
            //Получили имя выбранного файла
            // System.out.println(file.getName());
            return new String(Files.readAllBytes(Paths.get(String.valueOf(path))), StandardCharsets.UTF_8);
        }
        return null;
    }

    public static String getPath(){
        return file.getName();
    }

}
