package client.Entitys;

import java.util.ArrayList;

public class Category {
    private int categoryId;
    private Name name;
    private int parentCategoryId;
    private ArrayList<String> parentCategoryName;
    private ArrayList<String> description;
    private int imageId;
    private String imageFileName;
    private String sortPrefix;
    private int browsable;

    public int getCategoryId() {
        return categoryId;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public ArrayList<String> getParentCategoryName() {
        return parentCategoryName;
    }

    public String getName() {
        return name.getName();
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getSortPrefix() {
        return sortPrefix;
    }

    public int getBrowsable() {
        return browsable;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                ", parentCategoryName='" + parentCategoryName + '\'' +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                ", imageFileName='" + imageFileName + '\'' +
                ", sortPrefix='" + sortPrefix + '\'' +
                ", browsable=" + browsable +
                '}';
    }
}
