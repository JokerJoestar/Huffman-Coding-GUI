package sample;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.Map;

public class Controller3 {
    private int barsCount;

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private ScrollPane scrollChart = new ScrollPane();

    @FXML
    private void initialize() {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        xAxis.setLabel("character");
        yAxis.setLabel("count");

        for (Map.Entry<Character, Integer> entry : Controller.huff.getCharsMap().entrySet()) {
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

            XYChart.Data<String, Number> data = new XYChart.Data<>(key, entry.getValue());
            series1.getData().add(data);
            barsCount++;
        }

        barChart.setTitle("Character Count Graph");
        barChart.getData().addAll(series1);

        Stage statsStage = new Stage();

        Main.stg[2] = statsStage;
        statsStage.setTitle("Huffman Coding");
        statsStage.setScene(new Scene(scrollChart, 505, 400));
        statsStage.setResizable(false);
        statsStage.getIcons().add(Main.appIcon);
        barChart.setPrefWidth(barsCount*25);
        barChart.setMinWidth(barsCount*25);
        scrollChart.setContent(barChart);

        for (Node n:barChart.lookupAll(".default-color0.chart-bar"))
            n.setStyle("-fx-bar-fill: #648c67");

        Main.stg[2].show();
    }

}
