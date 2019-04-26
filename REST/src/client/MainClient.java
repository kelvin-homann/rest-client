package client;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

        //Root Node
        BorderPane root = new BorderPane();

        //Top Layout
        GridPane top = new GridPane();
        HBox center = new HBox();

        center.setSpacing(10);

        top.setAlignment(Pos.TOP_CENTER);
        top.setVgap(5);
        top.setHgap(2);
        top.setPadding(new Insets(20,50,8,50));

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("GET","POST","PUT","DELETE");
        comboBox.getSelectionModel().selectFirst();

        // Is called if the ComboBox changes
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                // TODO possibly adjust the UI Based on the Selection e.g Enabling/Disabling Object generation for PUT/POST and maybe Labels explaining stuff
                System.out.println(ov.getValue());
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

        final TableView<String[]> tableView = new TableView<String[]>();
        tableView.setMinSize(500, 300);

        top.add(comboBox, 0, 0);
        top.add(urlTextField, 1, 0);
        top.add(buttonSend, 2, 0);
        top.add(tableView, 0, 2);

        center.getChildren().add(tableView);

        root.setTop(top);
        root.setCenter(center);

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
