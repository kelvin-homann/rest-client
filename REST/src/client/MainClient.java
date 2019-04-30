package client;

import client.Entitys.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h1>GUI</h1>
 * GUI for the REST Client
 *
 * @author Kelvin Homann
 */

public class MainClient extends Application {
    private static final String BASE_URL = "https://www.sebastianzander.de/cookaweb/api/v1/"; // Base URL
    private POSTTags tag; // Tag Object for POST method
    private String tagJSON; // JSON String to be displayed on Post Method, shows a POSTTags Object as JSON String

    public static void main(String[] args) {
        launch(args); // launch GUI
    }

    /**
     * Clearing the Table representing the results from GET so it doesnt stack up
     *
     * @param tableView TableView showing the GET Results
     */
    private void clearTable(TableView tableView) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
    }

    /**
     * Create User Object from local user.json using gson
     * The user.json contains the personal accesstoken and the userid
     *
     * @return user object
     */
    private User getUser() {
        Gson gson = new Gson();
        JsonReader json = null;
        try {
            File currentDirFile = new File(".");
            json = new JsonReader(new FileReader(currentDirFile.getAbsolutePath() + "/user.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert json != null;
        return gson.fromJson(json, User.class);
    }

    /**
     * Create List of Tag Strings from textfield with commas e.g tag1, tag2, etc.
     *
     * @param input string from textfield
     * @return list of trimmed strings representing tags
     */
    private ArrayList<String> getTagsList(String input) {
        ArrayList<String> list = new ArrayList<>(Collections.singletonList(input));
        if (input.contains(",")) {
            list = new ArrayList<>(Arrays.asList(input.split(",")));
            list.replaceAll(String::trim);
        }
        return list;
    }

    /** JavaFX start method. Is called at start
     * @param primaryStage main container
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cooka REST Client");
        primaryStage.getIcons().add(new Image("https://i.imgur.com/RFRjm31.png"));

        //Root Node
        BorderPane root = new BorderPane();

        //Top Layout
        GridPane top = new GridPane();
        top.setAlignment(Pos.TOP_CENTER);
        top.setVgap(5);
        top.setHgap(5);
        top.setPadding(new Insets(20, 50, 8, 50));

        // Combobox: changing between HTTP Request Methods
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("GET", "POST", "PUT", "DELETE");
        comboBox.getSelectionModel().selectFirst();

        // Send Button
        Button buttonSend = new Button("Send");

        // Textfield for URL Input
        TextField urlTextField = new TextField();
        urlTextField.setText(BASE_URL);
        urlTextField.setPrefWidth(900);

        // Adding nodes to top layout
        top.add(comboBox, 0, 0);
        top.add(urlTextField, 1, 0);
        top.add(buttonSend, 2, 0);

        // TableView to display GET Results
        TableView tableView = new TableView<>();
        tableView.setPlaceholder(new Label("Make a GET Request to fill the table")); // Changing the default text for the empty table
        tableView.setEditable(true);
        tableView.setPrefSize(300, 550);
        tableView.setPadding(new Insets(0, 20, 0, 20));

        // HTTP Label: Shows the http code and the server message
        Label httpLabel = new Label("");
        httpLabel.setTextFill(Color.WHITE);

        // Center Layout
        VBox getCenter = new VBox();
        getCenter.setSpacing(10);
        getCenter.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(urlTextField, new Insets(20, 20, 20, 20));
        VBox.setMargin(httpLabel, new Insets(20, 20, 20, 20));

        getCenter.getChildren().addAll(httpLabel, tableView); // Adding nodes to getCenter Layout

        // Creating Tag Labels
        Label descLabel = new Label("Create new Tag");
        Label uidLabel = new Label("User ID");
        Label tokenLabel = new Label("Accesstoken");
        Label nameLabel = new Label("Name");
        Label jsonLabel = new Label();

        // Style for the Labels
        String textStyle = " -fx-font-size: 16px;\n" +
                "    -fx-font-family: \"Roboto\";\n" +
                "    -fx-text-fill: #ffffff;";

        // Setting the Style
        descLabel.setStyle(textStyle);
        uidLabel.setStyle(textStyle);
        tokenLabel.setStyle(textStyle);
        nameLabel.setStyle(textStyle);
        jsonLabel.setStyle(textStyle);

        // Input Fields for new Tags
        TextField uidField = new TextField();
        TextField tokenField = new TextField();
        TextField nameField = new TextField();

        // Setting the width
        uidField.setPrefWidth(350);
        tokenField.setPrefWidth(350);
        nameField.setPrefWidth(350);

        // Tag buttons
        Button createButton = new Button("Create");
        Button userButton = new Button("User");

        // Root Layout of the Post Layout
        BorderPane postCenter = new BorderPane();
        postCenter.setVisible(false); // hiding it on default

        // Right side of the POST Layout
        GridPane postCenterRight = new GridPane();
        postCenterRight.add(jsonLabel, 0, 0);
        postCenterRight.setPadding(new Insets(60, 250, 0, 0));

        // Left side of the POST Layout
        GridPane postCenterLeft = new GridPane();

        // Adding left and right layout to POST root layout
        postCenter.setRight(postCenterRight);
        postCenter.setLeft(postCenterLeft);

        // Styling
        postCenterLeft.setHgap(10);
        postCenterLeft.setVgap(30);
        postCenterLeft.setPadding(new Insets(60, 0, 0, 75));

        // Adding Nodes to layout
        postCenterLeft.add(descLabel, 0, 0);
        postCenterLeft.add(uidLabel, 0, 1);
        postCenterLeft.add(uidField, 1, 1);
        postCenterLeft.add(tokenLabel, 0, 2);
        postCenterLeft.add(tokenField, 1, 2);
        postCenterLeft.add(nameLabel, 0, 3);
        postCenterLeft.add(nameField, 1, 3);
        postCenterLeft.add(createButton, 0, 4);
        postCenterLeft.add(userButton, 1, 4);

        // Layout for PUT and DELETE (Combined because both arent implemented
        HBox putanddelCenter = new HBox();
        Label notImplementedLabel = new Label("Not implemented");
        putanddelCenter.getChildren().add(notImplementedLabel);

        // Styling
        putanddelCenter.setAlignment(Pos.CENTER);
        putanddelCenter.setVisible(false);

        // StackPane to stack all Layouts
        StackPane stackPane = new StackPane();
        ObservableList<javafx.scene.Node> list = stackPane.getChildren();

        list.addAll(getCenter, postCenter, putanddelCenter);

        // Button to load User Data into input fields
        userButton.addEventHandler(ActionEvent.ACTION, event -> {
            uidField.setText(String.valueOf(getUser().getUserId()));
            tokenField.setText(String.valueOf(getUser().getAccesstoken()));
        });

        // Button to create Tag JSON string
        createButton.addEventHandler(ActionEvent.ACTION, event -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                ArrayList<String> tagList = getTagsList(nameField.getText());
                tag = new POSTTags(Integer.parseInt(uidField.getText()), tokenField.getText(), tagList); // new Tag Object
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            tagJSON = gson.toJson(tag); // Creating tag JSON string
            jsonLabel.setText(tagJSON); // Setting text on the right layout to Tag JSON
        });

        // Send Button for url
        buttonSend.addEventHandler(ActionEvent.ACTION, event -> {
            // RESTController
            RESTController restController = new RESTController();

            // switch cases between Combobox value
            switch (comboBox.getValue()) {
                case "GET":
                    // Getting the http Results
                    String httpResponse = restController.GET(urlTextField.getText());

                    // Get Result Objects
                    Type gettingHttpResponse = new TypeToken<List<Results>>() {
                    }.getType();
                    Gson httpGson = new GsonBuilder().registerTypeAdapter(gettingHttpResponse, new ResultsDeserializer()).create();
                    List<Results> httpResultsArrayList = httpGson.fromJson(httpResponse, gettingHttpResponse);

                    // Setting the results label
                    if (httpResultsArrayList != null)
                        httpLabel.setText(httpResultsArrayList.get(0).getMessage() + " | " + httpResultsArrayList.get(0).getResultCode());

                    if (urlTextField.getText().contains("recipes")
                            && !urlTextField.getText().contains("categories")
                                && !urlTextField.getText().contains("tags")
                                    && !urlTextField.getText().contains("steps")
                                        && !urlTextField.getText().contains("ingredients")) {
                        try {
                            // Clearing the TableView
                            clearTable(tableView);

                            // Create List with needed Objects
                            Type deserializedRecipeList = new TypeToken<List<Recipe>>() {
                            }.getType();
                            Gson recipeGson = new GsonBuilder().registerTypeAdapter(deserializedRecipeList, new RecipeDeserializer()).create();
                            List<Recipe> recipeList = recipeGson.fromJson(httpResponse, deserializedRecipeList);

                            // Creating the table
                            TableColumn<String, Recipe> idColumn = new TableColumn<>("ID");
                            idColumn.setCellValueFactory(new PropertyValueFactory<>("recipeId"));

                            TableColumn<String, Recipe> titleColumn = new TableColumn<>("Title");
                            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

                            TableColumn<String, Recipe> creatorColumn = new TableColumn<>("Creator");
                            creatorColumn.setCellValueFactory(new PropertyValueFactory<>("creatorName"));

                            TableColumn<String, Recipe> categoryColumn = new TableColumn<>("Category");
                            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("mainCategoryName"));

                            TableColumn<String, Recipe> difficultyColumn = new TableColumn<>("Difficulty");
                            difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyType"));

                            TableColumn<String, Recipe> prepTimeColumn = new TableColumn<>("Prep time");
                            prepTimeColumn.setCellValueFactory(new PropertyValueFactory<>("preparationTime"));

                            TableColumn<String, Recipe> cookedCountColumn = new TableColumn<>("Cooked count");
                            cookedCountColumn.setCellValueFactory(new PropertyValueFactory<>("cookedCount"));

                            TableColumn<String, Recipe> pinnedCountColumn = new TableColumn<>("Pinned count");
                            pinnedCountColumn.setCellValueFactory(new PropertyValueFactory<>("cookedCount"));

                            TableColumn<String, Recipe> ratingColumn = new TableColumn<>("Rating");
                            ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

                            tableView.getColumns().addAll(idColumn, titleColumn, creatorColumn, categoryColumn, difficultyColumn
                                    , prepTimeColumn, cookedCountColumn, pinnedCountColumn, ratingColumn);

                            for (Recipe recipe : recipeList) {
                                tableView.getItems().add(recipe);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    if (urlTextField.getText().contains("categories")) {
                        try {
                            // Clearing the TableView
                            clearTable(tableView);

                            // Create List with needed Objects
                            Type deserializedCategoryList = new TypeToken<List<Category>>() {
                            }.getType();
                            Gson gson = new GsonBuilder().registerTypeAdapter(deserializedCategoryList, new CategoryDeserializer()).create();
                            List<Category> categoryList = gson.fromJson(httpResponse, deserializedCategoryList);

                            // Creating the table
                            TableColumn<String, Category> idColumn = new TableColumn<>("ID");
                            idColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

                            TableColumn<String, Category> nameColumn = new TableColumn<>("Name");
                            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                            tableView.getColumns().addAll(idColumn, nameColumn);

                            for (Category category : categoryList) {
                                tableView.getItems().add(category);
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    if (urlTextField.getText().contains("tags")) {
                        try {
                            // Clearing the TableView
                            clearTable(tableView);

                            // Create List with needed Objects
                            Type deserializedTagsList = new TypeToken<List<Tags>>() {
                            }.getType();
                            Gson gson = new GsonBuilder().registerTypeAdapter(deserializedTagsList, new TagsDeserializer()).create();
                            List<Tags> tagList = gson.fromJson(httpResponse, deserializedTagsList);

                            // Creating the table
                            TableColumn<String, Tags> idColumn = new TableColumn<>("ID");
                            idColumn.setCellValueFactory(new PropertyValueFactory<>("tagId"));

                            TableColumn<String, Tags> nameColumn = new TableColumn<>("Name");
                            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                            TableColumn<String, Tags> activeColumn = new TableColumn<>("Last Activity");
                            activeColumn.setCellValueFactory(new PropertyValueFactory<>("lastActiveDateTime"));

                            TableColumn<String, Tags> followerColumn = new TableColumn<>("Follower");
                            followerColumn.setCellValueFactory(new PropertyValueFactory<>("followerCount"));

                            TableColumn<String, Tags> recipeColumn = new TableColumn<>("RecipeCount");
                            recipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeCount"));

                            tableView.getColumns().addAll(idColumn, nameColumn, activeColumn, followerColumn, recipeColumn);

                            for (Tags category : tagList) {
                                tableView.getItems().add(category);
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    if (urlTextField.getText().contains("steps")) {
                        try {
                            // Clearing the TableView
                            clearTable(tableView);

                            // Create List with needed Objects
                            Type deserializedStepsList = new TypeToken<List<Steps>>() {
                            }.getType();
                            Gson gson = new GsonBuilder().registerTypeAdapter(deserializedStepsList, new StepsDeserializer()).create();
                            List<Steps> stepsList = gson.fromJson(httpResponse, deserializedStepsList);

                            // Creating the table
                            TableColumn<String, Steps> idColumn = new TableColumn<>("Recipe ID");
                            idColumn.setCellValueFactory(new PropertyValueFactory<>("recipeId"));

                            TableColumn<String, Steps> stepColumn = new TableColumn<>("Step Number");
                            stepColumn.setCellValueFactory(new PropertyValueFactory<>("stepNumber"));

                            TableColumn<String, Steps> titleColumn = new TableColumn<>("Title");
                            titleColumn.setCellValueFactory(new PropertyValueFactory<>("stepTitle"));

                            TableColumn<String, Steps> descColumn = new TableColumn<>("Description");
                            descColumn.setCellValueFactory(new PropertyValueFactory<>("stepDescription"));

                            tableView.getColumns().addAll(idColumn, stepColumn, titleColumn, descColumn);

                            for (Steps category : stepsList) {
                                tableView.getItems().add(category);
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    if (urlTextField.getText().contains("ingredients")) {
                        try {
                            // Clearing the TableView
                            clearTable(tableView);

                            // Create List with needed Objects
                            Type deserializedIngredientsList = new TypeToken<List<Ingredient>>() {}.getType();
                            Gson gson = new GsonBuilder().registerTypeAdapter(deserializedIngredientsList, new IngredientsDeserializer()).create();
                            List<Ingredient> ingredientList = gson.fromJson(httpResponse, deserializedIngredientsList);

                            // Creating the table
                            TableColumn<String, Ingredient> idColumn = new TableColumn<>("ID");
                            idColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));

                            TableColumn<String, Ingredient> nameColumn = new TableColumn<>("Name");
                            nameColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));

                            TableColumn<String, Ingredient> descColumn = new TableColumn<>("Description");
                            descColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientDescription"));

                            TableColumn<String, Ingredient> titleColumn = new TableColumn<>("Amount");
                            titleColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientAmount"));

                            TableColumn<String, Ingredient> unitColumn = new TableColumn<>("Unit");
                            unitColumn.setCellValueFactory(new PropertyValueFactory<>("unitTypeAbbreviation"));

                            tableView.getColumns().addAll(idColumn, nameColumn, descColumn, titleColumn, unitColumn);

                            for (Ingredient ingredient : ingredientList) {
                                tableView.getItems().add(ingredient);
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case "POST":
                    // Create List with needed Objects
                    String httpResponsePost = restController.POST(urlTextField.getText(), tagJSON);
                    Type gettingHttpPostResponse = new TypeToken<List<Results>>() {}.getType();
                    Gson httpPostGson = new GsonBuilder().registerTypeAdapter(gettingHttpPostResponse, new ResultsDeserializer()).create();
                    List<Results> httpPostResultsArrayList = httpPostGson.fromJson(httpResponsePost, gettingHttpPostResponse);

                    if (httpPostResultsArrayList != null)
                        httpLabel.setText(httpPostResultsArrayList.get(0).getMessage() + " | " + httpPostResultsArrayList.get(0).getResultCode());
                    else
                        httpLabel.setText("");
                case "PUT":
                    restController.PUT(urlTextField.getText(), tagJSON);
                    break;
                case "DELETE":
                    httpLabel.setText(restController.DELETE(urlTextField.getText()));
                    break;
                default:
                    httpLabel.setText("Unexpected Error");
                    break;
            }
        });

        // comboBox Event handling: Is called if the ComboBox changes
        comboBox.valueProperty().addListener((ov, t, t1) -> {
            // Disable tableView if GET is not selected in the comboBox
            if (!ov.getValue().equals("GET")) {
                tableView.setVisible(false);
            } else {
                tableView.setVisible(true);
            }

            if (ov.getValue().equals("POST")) {
                postCenter.setVisible(true);
            } else {
                postCenter.setVisible(false);
            }

            if (ov.getValue().equals("PUT") || ov.getValue().equals("DELETE")) {
                putanddelCenter.setVisible(true);
            } else {
                putanddelCenter.setVisible(false);
            }
        });

        // adding layouts to the main layout
        root.setTop(top);
        root.setCenter(stackPane);

        Scene scene = new Scene(root, 1280, 720); // main layout + size
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()); // Adding css styling
        primaryStage.setScene(scene); //Adding scene to the stage
        primaryStage.setResizable(false); // Fixed Size
        primaryStage.show(); //Displaying the contents of the stage
    }

    public static class RecipeDeserializer implements JsonDeserializer<List<Recipe>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Recipe> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray recipes = je.getAsJsonObject().getAsJsonArray("recipes");
            ArrayList<Recipe> recipeEntities = new ArrayList<>();

            for (JsonElement e : recipes) {
                recipeEntities.add(jdc.deserialize(e, Recipe.class));
            }

            return recipeEntities;

        }
    }

    public static class ResultsDeserializer implements JsonDeserializer<List<Results>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Results> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray results = je.getAsJsonObject().getAsJsonArray("results");
            ArrayList<Results> resultsArrayList = new ArrayList<>();

            for (JsonElement e : results) {
                resultsArrayList.add(jdc.deserialize(e, Results.class));
            }

            return resultsArrayList;
        }
    }

    public static class CategoryDeserializer implements JsonDeserializer<List<Category>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Category> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("categories");
            ArrayList<Category> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories) {
                resultsArrayList.add(jdc.deserialize(e, Category.class));
            }

            return resultsArrayList;
        }
    }

    public static class TagsDeserializer implements JsonDeserializer<List<Tags>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Tags> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("tags");
            ArrayList<Tags> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories) {
                resultsArrayList.add(jdc.deserialize(e, Tags.class));
            }

            return resultsArrayList;
        }
    }

    public static class StepsDeserializer implements JsonDeserializer<List<Steps>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Steps> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("steps");
            ArrayList<Steps> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories) {
                resultsArrayList.add(jdc.deserialize(e, Steps.class));
            }

            return resultsArrayList;
        }
    }

    public static class IngredientsDeserializer implements JsonDeserializer<List<Ingredient>> {
        /**
         * Deserializer Class
         * due to the fact that the json server responses hold a results object and
         * the needed object they have to be deserialized into the needed object
         *
         * @param je   input json element
         * @param type needed wrapper class
         * @param jdc  context
         * @return list of objects from json
         * @throws JsonParseException in case the json is invalid
         */
        @Override
        public List<Ingredient> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonArray ingredients = je.getAsJsonObject().getAsJsonArray("ingredients");
            ArrayList<Ingredient> resultsArrayList = new ArrayList<>();

            for (JsonElement e : ingredients) {
                resultsArrayList.add(jdc.deserialize(e, Ingredient.class));
            }

            return resultsArrayList;
        }
    }
}
