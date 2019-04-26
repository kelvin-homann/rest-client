package client;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainClient extends Application {
    private static final String BASE_URL = "https://www.sebastianzander.de/cookaweb/api/v1";

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cooka REST Client");
        primaryStage.getIcons().add(new Image("https://i.imgur.com/RFRjm31.png"));

        //Top Layout
        HBox haupt = new HBox();
        haupt.setAlignment(Pos.TOP_CENTER);
        haupt.setSpacing(5);
        haupt.setPadding(new Insets(20,50,8,50));

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("GET","POST","PUT","DELETE");
        comboBox.getSelectionModel().selectFirst();

        // Is called if the ComboBox changes
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                // TODO possibly adjust the UI Based on the Selection e.g Enabling/Disabling Object generation for PUT/POST and maybe Labels explaining stuff
            }
        });

        // TODO add TableView with JSON Data

        TextField urlTextField = new TextField();
        urlTextField.setText(BASE_URL);
        urlTextField.setPrefWidth(800);

        Button buttonSend = new Button("Send");

        buttonSend.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // RESTClient
                RESTClient restClient = new RESTClient();

                // Getting ComboBox Value
                switch (comboBox.getValue().toString()) {
                    case "GET":
                        System.out.println("GET");
                        System.out.println(restClient.GET(urlTextField.getText()));
                        break;
                    case "POST":
                        System.out.println("POST");
                        // TODO Input JSON
                        restClient.POST(urlTextField.getText(), "test");
                    case "PUT":
                        System.out.println("PUT");
                        // TODO Input JSON
                        restClient.PUT(urlTextField.getText(), "test");
                        break;
                    case "DELETE":
                        System.out.println("DELETE");
                        restClient.DELETE(urlTextField.getText());
                        break;
                    default:
                        break;
                }
            }
        });

        haupt.getChildren().addAll(comboBox, urlTextField, buttonSend);

        Scene scene = new Scene(haupt, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
