package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Controller2 implements Initializable {
    private TreeMap<Character, String> codes;

    private ObservableList<Map.Entry<Character, String>> items;
    private Text text;

    @FXML
    private TableView<Map.Entry<Character, String>> table = new TableView<>(items);
    @FXML
    private TableColumn<Map.Entry<Character, String>, String> column1;
    @FXML
    private TableColumn<Map.Entry<Character, String>, String> column2;

    @FXML
    private ScrollPane encodeTextScroll = new ScrollPane();
    @FXML
    private ScrollPane decodeTextScroll = new ScrollPane();

    public void initData() {
        text = new Text(Controller.huff.getEncodedText());
        text.wrappingWidthProperty().bind(Bindings.add(-15, encodeTextScroll.widthProperty()));
        encodeTextScroll.setFitToWidth(true);
        encodeTextScroll.setContent(text);

        text = new Text(Controller.huff.getDecodedText());
        text.wrappingWidthProperty().bind(Bindings.add(-15, decodeTextScroll.widthProperty()));
        decodeTextScroll.setFitToWidth(true);
        decodeTextScroll.setContent(text);

        codes = Controller.huff.getCodesMap();

        table.getColumns().clear();

        column1.setCellValueFactory(
                param -> {
                    String key = String.valueOf(param.getValue().getKey());

                    // Eye candy for whitespaces management.
                    switch(key){
                        case " ":
                            key = " ";
                            break;
                        case "\t":
                            key = "\\t";
                            break;
                        case "\n":
                            key = "\\n";
                            break;
                    }

                    return new SimpleStringProperty(key);
                });

        column2.setCellValueFactory(
                param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));

        items = FXCollections.observableArrayList(codes.entrySet());
        table.setItems(items);
        table.getColumns().addAll(column1, column2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleNewInput() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/mainScene.fxml"));

        Main.stg[0].show();

        Main.stg[1].close();
    }

    public void handleExportTable() {
        FileChooser fileChooser = new FileChooser();
        FileWriter mapOut;

        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(textFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(Main.stg[1]);

        if (file != null) {
            try {
                mapOut = new FileWriter(file);
                mapOut.write("    Character   |\t  Code" + System.getProperty("line.separator"));

                for (Map.Entry<Character, String> entry : codes.entrySet()) {
                    String key = entry.getKey().toString();

                    // Eye candy for whitespaces management.
                    switch(key){
                        case " ":
                            key = " ";
                            break;
                        case "\t":
                            key = "\\t";
                            break;
                        case "\n":
                            key = "\\n";
                            break;
                    }

                    mapOut.write("\t" + key + " \t|\t" + entry.getValue() + System.getProperty("line.separator"));
                }

                mapOut.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleCharCountChart() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/statsScene.fxml"));

        loader.load();
    }
}
