package resources.entitys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

public class Recipe {
    // change states
    private static final int CHST_TITLE = 0x00000001;
    private static final int CHST_DESCRIPTION = 0x00000002;
    private static final int CHST_CREATOR = 0x00000004;
    private static final int CHST_MAINIMAGE = 0x00000008;
    private static final int CHST_MAINCATEGORY = 0x00000010;
    private static final int CHST_CATEGORIES = 0x00000020;
    private static final int CHST_TAGS = 0x00000040;
    private static final int CHST_PUBLICATIONTYPE = 0x00000080;
    private static final int CHST_DIFFICULTYTYPE = 0x00000100;
    private static final int CHST_PREPARATIONTIME = 0x00000200;
    private static final int CHST_RECIPESTEPS = 0x00000400;
    private static final int CHST_FLAGS = 0x00000800;
    private static final int CHST_FORCE_UPDATE = 0xffffffff;

    private int changeState = 0;
    private boolean committed = false;
    private int flags = 0;

    private long recipeId;
    private String title;
    private String description;
    private long originalRecipeId;
    private long creatorId;
    private String creatorName;
    private long mainImageId;
    private String mainImageFileName;
    private long mainCategoryId;
    private String mainCategoryName;

    private EPublicationType publicationType;
    private EDifficultyType difficultyType;
    private int preparationTime;

    private int viewedCount;
    private int cookedCount;
    private int pinnedCount;
    private int modifiedCount;
    private int variedCount;
    private int sharedCount;

    private float rating;

    private Date createdDateTime;
    private Date lastModifiedDateTime;
    private Date lastCookedDateTime;

    private List<Category> categories;
    private List<Tag> tags;

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    private List<RecipeStepIngredient> ingredients;
    private List<RecipeStep> recipeSteps;
    private List<Recipe> similarRecipes;

    public Recipe(int id){
        this.recipeId = id;
    }

    /**
     * Gets the change state of this recipe instance that reflects what fields have changed
     * since the last synchronization. Basically an extended dirty flag.
     * @return the change state bit field
     */
    public long getChangeState() {
        return changeState;
    }

    /**
     * Reset the change state of this recipe as well as its recipe steps and recipe step ingredients
     * to denote that this recipe instance is up-to-date and synchronized with its database
     * counterpart. This method should be called after a recipe has been successfully submitted to,
     * created or updated in the database.
     */
    public void resetChangeState() {
        changeState = 0;
        if(recipeSteps != null) for(RecipeStep recipeStep : recipeSteps) {
            recipeStep.resetChangeState();
        }
    }

    public void setForceUpdate() {
        changeState = CHST_FORCE_UPDATE;
    }

    public boolean getCommited() {
        return committed;
    }

    public void resetCommitted() {
        this.committed = false;
    }

    public void commit() {
        this.committed = true;
    }

    @SuppressWarnings("unused")
    public int getFlags() {
        return flags;
    }

    @SuppressWarnings("unused")
    public void setFlags(int flags) {
        this.flags = flags;
        changeState |= CHST_FLAGS;
    }

    @SuppressWarnings("unused")
    public void addFlags(int flags) {
        this.flags |= flags;
        changeState |= CHST_FLAGS;
    }

    @SuppressWarnings("unused")
    public void removeFlags(int flags) {
        this.flags ^= flags;
        changeState |= CHST_FLAGS;
    }

    @SuppressWarnings("unused")
    public long getRecipeId() {
        return recipeId;
    }

    @SuppressWarnings("unused")
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
        changeState |= CHST_TITLE;
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
        changeState |= CHST_DESCRIPTION;
    }

    @SuppressWarnings("unused")
    public long getOriginalRecipeId() {
        return originalRecipeId;
    }

    @SuppressWarnings("unused")
    public void setOriginalRecipeId(long originalRecipeId) {
        this.originalRecipeId = originalRecipeId;
    }

    @SuppressWarnings("unused")
    public long getCreatorId() {
        return creatorId;
    }

    @SuppressWarnings("unused")
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
        changeState |= CHST_CREATOR;
    }

    @SuppressWarnings("unused")
    public String getCreatorName() {
        return creatorName;
    }

    @SuppressWarnings("unused")
    public long getMainImageId() {
        return mainImageId;
    }

    @SuppressWarnings("unused")
    public void setMainImageId(long mainImageId) {
        this.mainImageId = mainImageId;
        changeState |= CHST_MAINIMAGE;
    }

    @SuppressWarnings("unused")
    public String getMainImageFileName() {
        return mainImageFileName;
    }

    @SuppressWarnings("unused")
    public void setMainImageFileName(String mainImageFileName) {
        this.mainImageFileName = mainImageFileName;
        changeState |= CHST_MAINIMAGE;
    }


    @SuppressWarnings("unused")
    public long getMainCategoryId() {
        return mainCategoryId;
    }

    @SuppressWarnings("unused")
    public void setMainCategoryId(long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
        changeState |= CHST_MAINCATEGORY;
    }

    @SuppressWarnings("unused")
    public String getMainCategoryName() {
        return mainCategoryName;
    }

    @SuppressWarnings("unused")
    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
        changeState |= CHST_MAINCATEGORY;
    }

    @SuppressWarnings("unused")
    public EPublicationType getPublicationType() {
        return publicationType;
    }

    @SuppressWarnings("unused")
    public void setPublicationType(EPublicationType publicationType) {
        this.publicationType = publicationType;
        changeState |= CHST_PUBLICATIONTYPE;
    }

    @SuppressWarnings("unused")
    public EDifficultyType getDifficultyType() {
        return difficultyType;
    }

    @SuppressWarnings("unused")
    public void setDifficultyType(EDifficultyType difficultyType) {
        this.difficultyType = difficultyType;
        changeState |= CHST_DIFFICULTYTYPE;
    }

    @SuppressWarnings("unused")
    public int getPreparationTime() {
        return preparationTime;
    }

    @SuppressWarnings("unused")
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
        changeState |= CHST_PREPARATIONTIME;
    }

    @SuppressWarnings("unused")
    public int getViewedCount() {
        return viewedCount;
    }

    @SuppressWarnings("unused")
    public void setViewedCount(int viewedCount) {
        this.viewedCount = viewedCount;
    }

    @SuppressWarnings("unused")
    public int getCookedCount() {
        return cookedCount;
    }

    @SuppressWarnings("unused")
    public void setCookedCount(int cookedCount) {
        this.cookedCount = cookedCount;
    }

    @SuppressWarnings("unused")
    public int getPinnedCount() {
        return pinnedCount;
    }

    @SuppressWarnings("unused")
    public void setPinnedCount(int pinnedCount) {
        this.pinnedCount = pinnedCount;
    }

    @SuppressWarnings("unused")
    public int getModifiedCount() {
        return modifiedCount;
    }

    @SuppressWarnings("unused")
    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    @SuppressWarnings("unused")
    public int getVariedCount() {
        return variedCount;
    }

    @SuppressWarnings("unused")
    public void setVariedCount(int variedCount) {
        this.variedCount = variedCount;
    }

    @SuppressWarnings("unused")
    public int getSharedCount() {
        return sharedCount;
    }

    @SuppressWarnings("unused")
    public void setSharedCount(int sharedCount) {
        this.sharedCount = sharedCount;
    }

    @SuppressWarnings("unused")
    public float getRating() {
        return rating;
    }

    @SuppressWarnings("unused")
    public void setRating(float rating) {
        this.rating = rating;
    }

    @SuppressWarnings("unused")
    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    @SuppressWarnings("unused")
    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @SuppressWarnings("unused")
    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    @SuppressWarnings("unused")
    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    @SuppressWarnings("unused")
    public Date getLastCookedDateTime() {
        return lastCookedDateTime;
    }

    @SuppressWarnings("unused")
    public void setLastCookedDateTime(Date lastCookedDateTime) {
        this.lastCookedDateTime = lastCookedDateTime;
    }

    @SuppressWarnings("unused")
    public List<Category> getCategories() {
        return categories;
    }

    @SuppressWarnings("unused")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
        changeState |= CHST_CATEGORIES;
    }

    @SuppressWarnings("unused")
    public List<Tag> getTags() {
        return tags;
    }

    @SuppressWarnings("unused")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
        changeState |= CHST_TAGS;
    }

    @SuppressWarnings("unused")
    public List<RecipeStepIngredient> getIngredients() {
        return ingredients;
    }

    @SuppressWarnings("unused")
    public void setIngredients(List<RecipeStepIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @SuppressWarnings("unused")
    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    @SuppressWarnings("unused")
    public void setRecipeSteps(List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
        changeState |= CHST_RECIPESTEPS;
    }

    @SuppressWarnings("unused")
    public List<Recipe> getSimilarRecipes() {
        return similarRecipes;
    }

    @SuppressWarnings("unused")
    public void setSimilarRecipes(List<Recipe> similarRecipes) {
        this.similarRecipes = similarRecipes;
    }

    @SuppressWarnings("unused")

    public String toJSON(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
