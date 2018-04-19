package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage stg[] = new Stage[3];
    static Image appIcon = new Image(Main.class.getResourceAsStream("appicon.png"));

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg[0] = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainScene.fxml"));

        primaryStage.setTitle("Huffman Coding");
        primaryStage.setScene(new Scene(root, 505, 362));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(appIcon);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
