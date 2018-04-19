package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {
    static Huffman huff;

    @FXML
    private TextArea textArea;
    @FXML
    private Label errorLabel;

    @FXML
    protected void handleDone() {
        if (textArea.getText().trim().isEmpty()) {
            errorLabel.setText("Input must contain something.");
            textArea.setText("");
        }
        else {
            errorLabel.setText("");
            huff = new Huffman();
            huff.setInputText(textArea.getText());
            huff.findText();

            try {
                textArea.setText("");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/outputScene.fxml"));

                Parent viewParent = loader.load();

                Controller2 controller2 = loader.getController();
                controller2.initData();

                Stage outputStage = new Stage();

                Main.stg[1] = outputStage;
                outputStage.setTitle("Huffman Coding");
                outputStage.setScene(new Scene(viewParent, 505, 400));
                outputStage.setResizable(false);
                outputStage.getIcons().add(Main.appIcon);

                outputStage.show();
                Main.stg[0].close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
