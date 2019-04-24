package server;

import resources.entitys.EDifficultyType;
import resources.entitys.EPublicationType;
import resources.entitys.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseClient {

    private Connection conn;

    public DatabaseClient(){
        String driverName = "com.mysql.cj.jdbc.Driver";

        String serverName = "localhost";
        String mydatabase = "cooka";

        String username = "root";
        String password = "";
        try {
            Class.forName(driverName);

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, username, password); // DB Connection
            System.out.println("Successfully connected to DB");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error trying to connect to DB");
            e.printStackTrace();
        }
    }

    public void CloseConnection(){
        try {
            conn.close();
            System.out.println("Closed the Db Connnection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recipe getRecipeById(int id){
        String query = "SELECT\n" +
                "    recipe.recipeId,\n" +
                "    titleString.originalLanguageId AS languageId,\n" +
                "    titleString.originalValue AS title,\n" +
                "    descriptionString.originalValue AS description,\n" +
                "    recipe.originalRecipeId,\n" +
                "    originalRecipeTitleString.originalValue AS originalTitle,\n" +
                "    usr.userId AS creatorId,\n" +
                "    usr.userName AS creatorName,\n" +
                "    recipe.mainImageId,\n" +
                "    mainImage.imageFileName AS mainImageFileName,\n" +
                "    recipe.mainCategoryId,\n" +
                "    categoryNameString.originalValue AS mainCategoryName,\n" +
                "    recipe.publicationType,\n" +
                "    recipe.difficultyType,\n" +
                "    recipe.preparationTime,\n" +
                "    recipe.viewedCount,\n" +
                "    recipe.cookedCount,\n" +
                "    recipe.pinnedCount,\n" +
                "    recipe.modifiedCount,\n" +
                "    recipe.variedCount,\n" +
                "    recipe.sharedCount,\n" +
                "    recipe.createdDateTime,\n" +
                "    recipe.lastModifiedDateTime,\n" +
                "    recipe.lastCookedDateTime,\n" +
                "    recipe.rating,\n" +
                "    recipe.flags\n" +
                "FROM\n" +
                "    Recipes recipe\n" +
                "LEFT JOIN Recipes originalRecipe ON\n" +
                "    recipe.originalRecipeId = originalRecipe.recipeId\n" +
                "LEFT JOIN Users usr ON\n" +
                "    recipe.creatorId = usr.userId\n" +
                "LEFT JOIN Categories category ON\n" +
                "    recipe.mainCategoryId = category.categoryId\n" +
                "LEFT JOIN Strings titleString ON\n" +
                "    recipe.titleStringId = titleString.stringId\n" +
                "LEFT JOIN Strings descriptionString ON\n" +
                "    recipe.descriptionStringId = descriptionString.stringId\n" +
                "LEFT JOIN Strings originalRecipeTitleString ON\n" +
                "    originalRecipe.titleStringId = originalRecipeTitleString.stringId\n" +
                "LEFT JOIN Strings categoryNameString ON\n" +
                "    category.nameStringId = categoryNameString.stringId\n" +
                "LEFT JOIN Images mainImage ON\n" +
                "    mainImage.imageId = recipe.mainImageId\n" +
                "LEFT JOIN Logins login ON\n" +
                "    login.userId = usr.userId\n" +
                "WHERE\n" +
                "    recipe.recipeId =" + id;

        Recipe recipe = new Recipe(id);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                recipe.setTitle(rs.getString("title"));
                recipe.setDescription(rs.getString("description"));
                recipe.setCreatorId(rs.getInt("creatorId"));
                recipe.setCreatorName(rs.getString("creatorName"));
                recipe.setMainImageFileName(rs.getString("mainImageFileName"));
                recipe.setMainCategoryId(rs.getLong("mainCategoryId"));
                recipe.setMainCategoryName(rs.getString("mainCategoryName"));
                recipe.setPublicationType(EPublicationType.PUBLIC);
                recipe.setDifficultyType(EDifficultyType.SIMPLE);
                recipe.setPreparationTime(rs.getInt("preparationTime"));
                recipe.setViewedCount(rs.getInt("viewedCount"));
                recipe.setCookedCount(rs.getInt("cookedCount"));
                recipe.setPinnedCount(rs.getInt("pinnedCount"));
                recipe.setModifiedCount(rs.getInt("modifiedCount"));
                recipe.setVariedCount(rs.getInt("variedCount"));
                recipe.setSharedCount(rs.getInt("sharedCount"));
                recipe.setRating(rs.getFloat("rating"));
                recipe.setCreatedDateTime(rs.getTime("createdDateTime"));
                recipe.setLastModifiedDateTime(rs.getTime("lastModifiedDateTime"));
                recipe.setLastCookedDateTime(rs.getTime("lastCookedDateTime"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        System.out.println("Created Recipe Object");
        return recipe;
    }

    public ArrayList<Recipe> getRecipes(){
        String query = "SELECT * FROM recipes";
        ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            System.out.println(rowcount);
            while(rs.next()){
                int id = rs.getInt("recipeId");
                Recipe recipe = new Recipe(id);
//                    recipe.setTitle(rs.getString("title"));
//                    recipe.setDescription(rs.getString("description"));
//                    recipe.setCreatorId(rs.getInt("creatorId"));
//                    recipe.setCreatorName(rs.getString("creatorName"));
//                    recipe.setMainImageFileName(rs.getString("mainImageFileName"));
//                    recipe.setMainCategoryId(rs.getLong("mainCategoryId"));
//                    recipe.setMainCategoryName(rs.getString("mainCategoryName"));
//                    recipe.setPublicationType(EPublicationType.PUBLIC);
//                    recipe.setDifficultyType(EDifficultyType.SIMPLE);
//                    recipe.setPreparationTime(rs.getInt("preparationTime"));
//                    recipe.setViewedCount(rs.getInt("viewedCount"));
//                    recipe.setCookedCount(rs.getInt("cookedCount"));
//                    recipe.setPinnedCount(rs.getInt("pinnedCount"));
//                    recipe.setModifiedCount(rs.getInt("modifiedCount"));
//                    recipe.setVariedCount(rs.getInt("variedCount"));
//                    recipe.setSharedCount(rs.getInt("sharedCount"));
//                    recipe.setRating(rs.getFloat("rating"));
//                    recipe.setCreatedDateTime(rs.getTime("createdDateTime"));
//                    recipe.setLastModifiedDateTime(rs.getTime("lastModifiedDateTime"));
//                    recipe.setLastCookedDateTime(rs.getTime("lastCookedDateTime"));
                recipeList.add(recipe);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        System.out.println("Created Recipe Object");
        return recipeList;
    }
}
