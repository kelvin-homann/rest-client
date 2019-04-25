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
    Stage window;

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
        window = primaryStage;
        window.setTitle("Cooka REST Client");
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
                // Getting ComboBox Value
//                System.out.println(comboBox.getValue());
            }
        });

        TextField urlTextField = new TextField();
        urlTextField.setText(RESTClient.BASE_URL);
        urlTextField.setPrefWidth(800);

        Button buttonSend = new Button("Send");
//        buttonSend.setPrefSize(100, 20);

        buttonSend.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // RESTClient
                RESTClient restClient = new RESTClient();

                // Getting ComboBox Value
                switch (comboBox.getValue().toString()) {
                    case "GET":
                        System.out.println("GET");
                        System.out.println(restClient.GET("recipes/1"));
                        break;
                    case "POST":
                        System.out.println("POST");
                        restClient.POST("recipes/22", "test");
                    case "PUT":
                        System.out.println("PUT");
                        restClient.PUT("recipes/1", "test");
                        break;
                    case "DELETE":
                        System.out.println("DELETE");
                        restClient.DELETE("recipes/1");
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            }
        });

        haupt.getChildren().addAll(comboBox, urlTextField, buttonSend);

        Scene scene = new Scene(haupt, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
