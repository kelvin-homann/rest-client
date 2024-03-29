package client.Entitys;

/**
 * Represents the ingredients
 */
public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String ingredientDescription;
    private float ingredientAmount;
    private int unitTypeId;
    private String unitTypeName;
    private String unitTypeAbbreviation;
    private String customUnit;
    private String joinedUnitName;
    private String unicodeAmountUnitShort;
    private String unicodeAmountUnitLong;

    public String getJoinedUnitName() {
        return joinedUnitName;
    }

    public String getUnicodeAmountUnitShort() {
        return unicodeAmountUnitShort;
    }

    public String getUnicodeAmountUnitLong() {
        return unicodeAmountUnitLong;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public float getIngredientAmount() {
        return ingredientAmount;
    }

    public int getUnitTypeId() {
        return unitTypeId;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public String getUnitTypeAbbreviation() {
        return unitTypeAbbreviation;
    }

    public String getCustomUnit() {
        return customUnit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientDescription='" + ingredientDescription + '\'' +
                ", ingredientAmount=" + ingredientAmount +
                ", unitTypeId=" + unitTypeId +
                ", unitTypeName='" + unitTypeName + '\'' +
                ", unitTypeAbbreviation='" + unitTypeAbbreviation + '\'' +
                ", customUnit='" + customUnit + '\'' +
                '}';
    }
}
