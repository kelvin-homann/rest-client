package client;

public class RecipeEntity{
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

    public RecipeEntity() {

    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(int mainImageId) {
        this.mainImageId = mainImageId;
    }

    public String getMainImageFileName() {
        return mainImageFileName;
    }

    public void setMainImageFileName(String mainImageFileName) {
        this.mainImageFileName = mainImageFileName;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(String difficultyType) {
        this.difficultyType = difficultyType;
    }

    public float getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(float preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getCookedCount() {
        return cookedCount;
    }

    public void setCookedCount(int cookedCount) {
        this.cookedCount = cookedCount;
    }

    public int getPinnedCount() {
        return pinnedCount;
    }

    public void setPinnedCount(int pinnedCount) {
        this.pinnedCount = pinnedCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
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
