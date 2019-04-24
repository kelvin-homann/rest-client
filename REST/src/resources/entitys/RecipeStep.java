package resources.entitys;

import java.util.List;

/**
 * A class that represents a cooking step from a recipe and that is an app model representation of
 * the corresponding database entity called RecipeStep. Used to serialize between app and database
 * and to cache its state without needing to repeatedly query the same unchanged object from the
 * database.
 */
public class RecipeStep {

    // change states
    public static final int CHST_TITLE = 0x00000001;
    public static final int CHST_DESCRIPTION = 0x00000002;
    public static final int CHST_STEPNUMBER = 0x00000004;
    public static final int CHST_INGREDIENTS = 0x00000008;
    public static final int CHST_FLAGS = 0x00000010;
    public static final int CHST_FORCE_UPDATE = 0xffffffff;

    // flags
    public static final int FLAG_HIDDEN = 0x00000001;
    public static final int FLAG_DELETED = 0x00000002;

    private int changeState = 0;
    private boolean committed = false;
    private int flags = 0;

    private final long recipeStepId;
    private int stepNumber;
    private int originalStepNumber = -1;
    private String stepTitle;
    private String stepDescription;

    private List<RecipeStepIngredient> recipeStepIngredients;

    private RecipeStep(final long recipeStepId, int stepNumber, String stepTitle, String
            stepDescription, List<RecipeStepIngredient> recipeStepIngredients)
    {
        this.recipeStepId = recipeStepId;
        this.stepNumber = stepNumber;
        this.stepTitle = stepTitle;
        this.stepDescription = stepDescription;
        this.recipeStepIngredients = recipeStepIngredients;
        this.changeState = 0;
    }

    /**
     * Gets the change state of this recipe step instance that reflects what fields have changed
     * since the last synchronization. Basically an extended dirty flag.
     * @return the change state bit field
     */
    public int getChangeState() {
        return changeState;
    }

    /**
     * Reset the change state of this recipe step as well as its recipe step ingredients to denote
     * that this recipe step instance is up-to-date and synchronized with its database
     * counterpart. This method should be called after a recipe step has been successfully submitted
     * to, created or updated in the database like for example in the course of a recipe update.
     */
    public void resetChangeState() {
        changeState = 0;
        originalStepNumber = -1;
        if(recipeStepIngredients != null) for(RecipeStepIngredient recipeStepIngredient : recipeStepIngredients) {
            recipeStepIngredient.resetChangeState();
        }
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
    public long getRecipeStepId() {
        return recipeStepId;
    }

    @SuppressWarnings("unused")
    public int getStepNumber() {
        return stepNumber;
    }

    @SuppressWarnings("unused")
    public void setStepNumber(int stepNumber) {
        if(this.stepNumber != stepNumber) {
            // if the new step number is different to the original step number set change state
            if(originalStepNumber == -1) {
                originalStepNumber = this.stepNumber;
                changeState |= CHST_STEPNUMBER;
            }
            // if the new step number again is equal to the original step number revert change state
            else if(originalStepNumber == stepNumber) {
                originalStepNumber = -1;
                changeState ^= CHST_STEPNUMBER;
            }

            this.stepNumber = stepNumber;
        }
    }

    @SuppressWarnings("unused")
    public int getOriginalStepNumber() {
        return originalStepNumber;
    }

    @SuppressWarnings("unused")
    public String getStepTitle() {
        return stepTitle;
    }

    @SuppressWarnings("unused")
    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
        changeState |= CHST_TITLE;
    }

    @SuppressWarnings("unused")
    public String getStepDescription() {
        return stepDescription;
    }

    @SuppressWarnings("unused")
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
        changeState |= CHST_DESCRIPTION;
    }

    @SuppressWarnings("unused")
    public List<RecipeStepIngredient> getRecipeStepIngredients() {
        return recipeStepIngredients;
    }

    @SuppressWarnings("unused")
    public void setRecipeStepIngredients(List<RecipeStepIngredient> recipeStepIngredients) {
        this.recipeStepIngredients = recipeStepIngredients;
        changeState |= CHST_INGREDIENTS;
    }

    @SuppressWarnings("unused")
    public void addRecipeStepIngredient(RecipeStepIngredient recipeStepIngredient) {
        if(recipeStepIngredient == null)
            return;
        recipeStepIngredients.add(recipeStepIngredient);
        changeState |= CHST_INGREDIENTS;
    }
}