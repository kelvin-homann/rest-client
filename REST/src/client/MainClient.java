package client;

import client.Entitys.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// TODO Javadoc
// TODO generate JSON for PUT and show it with Stackpane
public class MainClient extends Application {
    private static final String BASE_URL = "https://www.sebastianzander.de/cookaweb/api/v1/";

    private void clearTable(TableView tableView){
        tableView.getColumns().clear();
        tableView.getItems().clear();
    }

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
        top.setPadding(new Insets(20,50,8,50));

        // Combobox: changing between HTTP Request Methods
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("GET","POST","PUT","DELETE");
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
        VBox center = new VBox();

        center.setSpacing(10);
        center.setAlignment(Pos.TOP_CENTER);
        center.setMargin(urlTextField, new Insets(20, 20, 20, 20));
        center.setMargin(httpLabel, new Insets(20, 20, 20, 20));

        center.getChildren().addAll(httpLabel, tableView);

        // Send Button Event handling: on click
        buttonSend.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // RESTClient
                RESTClient restClient = new RESTClient();

                // switch cases between Combobox value
                switch (comboBox.getValue().toString()) {
                    case "GET":
                        // Getting the http Results
                        String httpResponse = restClient.GET(urlTextField.getText());

                        // Create List with needed Objects
                        Type gettingHttpResponse = new TypeToken<List<Results>>(){}.getType();
                        Gson httpGson = new GsonBuilder().registerTypeAdapter(gettingHttpResponse, new ResultsDeserializer()).create();
                        List<Results> httpResultsArrayList = httpGson.fromJson(httpResponse, gettingHttpResponse);

                        if (httpResultsArrayList != null)
                            httpLabel.setText(httpResultsArrayList.get(0).getMessage() + " | " + httpResultsArrayList.get(0).getResultCode());

                        if (urlTextField.getText().contains("recipes") || !urlTextField.getText().contains("categories") || !urlTextField.getText().contains("tags") || !urlTextField.getText().contains("steps")) {
                            try{
                                // Clearing the TableView
                                clearTable(tableView);

                                // Create List with needed Objects
                                Type deserializedRecipeList = new TypeToken<List<Recipe>>(){}.getType();
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
                            } catch (NullPointerException e){
                            }
                        }

                        if(urlTextField.getText().contains("categories")){
                            try{
                                // Clearing the TableView
                                clearTable(tableView);

                                // Create List with needed Objects
                                Type deserializedCategoryList = new TypeToken<List<Category>>(){}.getType();
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

                            }catch(NullPointerException e){
                            }
                        }

                        if(urlTextField.getText().contains("tags")){
                            try{
                                // Clearing the TableView
                                clearTable(tableView);

                                // Create List with needed Objects
                                Type deserializedTagsList = new TypeToken<List<Tags>>(){}.getType();
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

                            }catch(NullPointerException e){
                            }
                        }

                        if(urlTextField.getText().contains("steps")){
                            try{
                                // Clearing the TableView
                                clearTable(tableView);

                                // Create List with needed Objects
                                Type deserializedStepsList = new TypeToken<List<Steps>>(){}.getType();
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

                            }catch(NullPointerException e){
                            }
                        }

                        if(urlTextField.getText().contains("ingredients")){
                            try{
                                // Clearing the TableView
                                clearTable(tableView);

                                // Create List with needed Objects
                                Type deserializedIngredientsList = new TypeToken<List<Ingredient>>(){}.getType();
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

                            }catch (NullPointerException e){
                            }
                        }

                        break;
                    case "POST":
                        httpLabel.setText(restClient.POST(urlTextField.getText(), "test"));
                    case "PUT":
                        httpLabel.setText(restClient.PUT(urlTextField.getText(), "test"));
                        break;
                    case "DELETE":
                        httpLabel.setText(restClient.DELETE(urlTextField.getText()));
                        break;
                    default:
                        httpLabel.setText("Unexpected Error");
                        break;
                }
            }
        });

        // comboBox Event handling: Is called if the ComboBox changes
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                // Disable tableView if GET is not selected in the comboBox
                if (!ov.getValue().equals("GET")){
                    tableView.setVisible(false);
                } else {
                    tableView.setVisible(true);
                }
            }
        });

        // adding layouts to the main layout
        root.setTop(top);
        root.setCenter(center);

        Scene scene = new Scene(root, 1280, 720); // main layout + size
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()); // Adding css styling
        primaryStage.setScene(scene); //Adding scene to the stage
        primaryStage.setResizable(false); // Fixed Size
        primaryStage.show(); //Displaying the contents of the stage
    }

    public static void main(String[] args){
        launch(args);
    }

    public static class RecipeDeserializer implements JsonDeserializer<List<Recipe>>
    {
        @Override
        public List<Recipe> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray recipes = je.getAsJsonObject().getAsJsonArray("recipes");
            ArrayList<Recipe> recipeEntities = new ArrayList<>();

            for (JsonElement e : recipes)
            {
                recipeEntities.add(jdc.deserialize(e, Recipe.class));
            }

            return recipeEntities;

        }
    }

    public static class ResultsDeserializer implements JsonDeserializer<List<Results>>
    {
        @Override
        public List<Results> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray results = je.getAsJsonObject().getAsJsonArray("results");
            ArrayList<Results> resultsArrayList = new ArrayList<>();

            for (JsonElement e : results)
            {
                resultsArrayList.add(jdc.deserialize(e, Results.class));
            }

            return resultsArrayList;
        }
    }

    public static class CategoryDeserializer implements JsonDeserializer<List<Category>>
    {
        @Override
        public List<Category> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("categories");
            ArrayList<Category> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories)
            {
                resultsArrayList.add(jdc.deserialize(e, Category.class));
            }

            return resultsArrayList;
        }
    }

    public static class TagsDeserializer implements JsonDeserializer<List<Tags>>
    {
        @Override
        public List<Tags> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("tags");
            ArrayList<Tags> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories)
            {
                resultsArrayList.add(jdc.deserialize(e, Tags.class));
            }

            return resultsArrayList;
        }
    }

    public static class StepsDeserializer implements JsonDeserializer<List<Steps>>
    {
        @Override
        public List<Steps> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray categories = je.getAsJsonObject().getAsJsonArray("steps");
            ArrayList<Steps> resultsArrayList = new ArrayList<>();

            for (JsonElement e : categories)
            {
                resultsArrayList.add(jdc.deserialize(e, Steps.class));
            }

            return resultsArrayList;
        }
    }

    public static class IngredientsDeserializer implements JsonDeserializer<List<Ingredient>>
    {
        @Override
        public List<Ingredient> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
        {
            JsonArray ingredients = je.getAsJsonObject().getAsJsonArray("ingredients");
            ArrayList<Ingredient> resultsArrayList = new ArrayList<>();

            for (JsonElement e : ingredients)
            {
                resultsArrayList.add(jdc.deserialize(e, Ingredient.class));
            }

            return resultsArrayList;
        }
    }

}
