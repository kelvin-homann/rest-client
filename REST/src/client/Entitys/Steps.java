package client.Entitys;

import java.util.ArrayList;

/**
 * represents the steps
 */
public class Steps {
    private int recipeId;
    private int stepId;
    private int stepNumber;
    private String stepTitle;
    private String stepDescription;
    private ArrayList<Ingredient> ingredients;

    public int getRecipeId() {
        return recipeId;
    }

    public int getStepId() {
        return stepId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "recipeId=" + recipeId +
                ", stepId=" + stepId +
                ", stepNumber=" + stepNumber +
                ", stepTitle='" + stepTitle + '\'' +
                ", stepDescription='" + stepDescription + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
