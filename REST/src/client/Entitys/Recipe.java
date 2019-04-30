package client.Entitys;

/**
 * represents the recipes
 */
public class Recipe {
    private int recipeId;
    private int languageId;
    private String title;
    private int creatorId;
    private String creatorName;
    private int mainImageId;
    private String mainImageFileName;
    private int mainCategoryId;
    private String mainCategoryName;
    private String difficultyType;
    private float preparationTime;
    private int cookedCount;
    private int pinnedCount;
    private float rating;
    private int flags;

    public int getRecipeId() {
        return recipeId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getTitle() {
        return title;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public int getMainImageId() {
        return mainImageId;
    }

    public String getMainImageFileName() {
        return mainImageFileName;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public String getDifficultyType() {
        return difficultyType;
    }

    public float getPreparationTime() {
        return preparationTime;
    }

    public int getCookedCount() {
        return cookedCount;
    }

    public int getPinnedCount() {
        return pinnedCount;
    }

    public float getRating() {
        return rating;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", languageId=" + languageId +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", mainImageId=" + mainImageId +
                ", mainImageFileName='" + mainImageFileName + '\'' +
                ", mainCategoryId=" + mainCategoryId +
                ", mainCategoryName='" + mainCategoryName + '\'' +
                ", difficultyType='" + difficultyType + '\'' +
                ", preparationTime=" + preparationTime +
                ", cookedCount=" + cookedCount +
                ", pinnedCount=" + pinnedCount +
                ", rating=" + rating +
                ", flags=" + flags +
                '}';
    }
}
