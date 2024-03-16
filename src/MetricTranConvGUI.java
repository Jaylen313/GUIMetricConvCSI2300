
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MetricTranConvGUI extends Application {

    private TextField inputField;
    private Label outputLabel;
    private ChoiceBox<String> choiceBox; 

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/MetricConvLogo.jpg")));

       
        ImageView iconImageView = new ImageView(new Image(getClass().getResourceAsStream("/MetricConvLogo.jpg")));
        Label titleLabel = new Label("MetricTran");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");

        VBox titleBox = new VBox(iconImageView, titleLabel);
        titleBox.setAlignment(Pos.CENTER); 

        
        choiceBox = new ChoiceBox<>(); 
        choiceBox.getItems().addAll(
                "Centimetres to Inches",
                "Inches to Centimetres",
                "Inches to Feet",
                "Feet to Inches",
                "Feet to Meters",
                "Meters to Feet"
        );
        choiceBox.setValue("Centimetres to Inches");

        inputField = new TextField();
        inputField.setPromptText("Enter value");
        inputField.setMaxWidth(100);

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        
        outputLabel = new Label();

        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        GridPane.setConstraints(titleBox, 0, 0, 3, 1);
        GridPane.setConstraints(choiceBox, 0, 1);
        GridPane.setConstraints(inputField, 1, 1);
        GridPane.setConstraints(convertButton, 2, 1);
        GridPane.setConstraints(outputLabel, 0, 2, 3, 1);

        gridPane.getChildren().addAll(titleBox, choiceBox, inputField, convertButton, outputLabel);

        Scene scene = new Scene(gridPane, 500, 500); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convert() {
        try {
            String conversion = inputField.getText();
            float value = Float.parseFloat(conversion);

            String selectedConversion = choiceBox.getValue();
            String result = "";

            switch (selectedConversion) {
                case "Centimetres to Inches":
                    result = String.format("%.2f inches", value * 0.3937f);
                    break;
                case "Inches to Centimetres":
                    result = String.format("%.2f centimetres", value * 2.54f);
                    break;
                case "Inches to Feet":
                    result = String.format("%.2f feet", value * 0.0833f);
                    break;
                case "Feet to Inches":
                    result = String.format("%.2f inches", value * 12f);
                    break;
                case "Feet to Meters":
                    result = String.format("%.2f meters", value * 0.3048f);
                    break;
                case "Meters to Feet":
                    result = String.format("%.2f feet", value * 3.28084f);
                    break;
            }

            outputLabel.setText(result);
        } catch (NumberFormatException e) {
            outputLabel.setText("Invalid input. Please enter a number.");
        }
    }
}
