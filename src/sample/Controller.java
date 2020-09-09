package sample;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.crypto.SecretKey;

public class Controller {


    public static ObservableList<String> langs = FXCollections.observableArrayList();


    public int keySize;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonDeleteKey;

    @FXML
    private Button encryptText;

    @FXML
    private Button decryptText;

    @FXML
    private Button keyGenerationButton;

    @FXML
    private RadioButton keySize1;

    @FXML
    private ToggleGroup keyGenerationGroup;

    @FXML
    private RadioButton keySize3;

    @FXML
    private RadioButton keySize2;

    @FXML
    private ComboBox<String> chooseKey;

    @FXML
    public TextArea areaForFileName;

    @FXML
    private TextField nameOfGeneratedKey;

    @FXML
    void initialize() {
        // Подгрузка ключей в комбо бокс
        Path path = Path.of("keys");

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path))
        {
            for (Path temp : files)

                langs.add(temp.toString());

//                    System.out.println(temp.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
//Конец подгрузки




        keySize1.setOnAction(actionEvent -> {
            keySize = 128;
            //System.out.println("128 chozen");
        });

        keySize2.setOnAction(actionEvent -> {
            keySize = 192;
           // System.out.println("192 chosen");
        });

        keySize3.setOnAction(actionEvent -> {
            keySize = 256;
            //System.out.println("256 chozen");
        });

        keyGenerationButton.setOnAction(actionEvent -> {
            try {
                SecretKey secretKey = KeyGeneration.generation(nameOfGeneratedKey.getText(), keySize);
                langs.add(nameOfGeneratedKey.getText());
                chooseKey.setItems(langs);
                String nameKey = nameOfGeneratedKey.getText();
                System.out.println(nameKey);
                try {

                    FileOutputStream fos = new FileOutputStream("keys/" + nameKey);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    oos.writeObject(secretKey);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        encryptText.setOnAction(actionEvent -> {
            try {
                if (KeyGeneration.keysMap.containsKey(chooseKey.getValue())) {
                    SecretKey secretKey = KeyGeneration.keysMap.get(chooseKey.getValue());
                    String encryptedText = AesCipher.encrypt(secretKey, OpenFile.main());

                    try (FileWriter writer = new FileWriter("data/encryptedText.txt", false)) {
                        // запись всей строки
                        writer.write(encryptedText);

                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            areaForFileName.setText(OpenFile.getPath());
        });

        decryptText.setOnAction(actionEvent -> {
            try {
                if (KeyGeneration.keysMap.containsKey(chooseKey.getValue())) {
                    SecretKey secretKey = KeyGeneration.keysMap.get(chooseKey.getValue());
                    String decryptedText = AesCipher.decrypt(secretKey, OpenFile.main());

                    try (FileWriter writer = new FileWriter("data/decryptedText.txt", false)) {
                        // запись всей строки
                        writer.write(decryptedText);

                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            areaForFileName.setText(OpenFile.getPath());
        });

        buttonDeleteKey.setOnAction(actionEvent -> {
            langs.remove(chooseKey.getValue());
            KeyGeneration.keysMap.remove(chooseKey.getValue());
        });


        chooseKey.setItems(langs);
    }
}
