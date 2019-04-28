package client.Entitys;

public class Tags {
    private int tagId;
    private String name;
    private String lastActiveDateTime;
    private int followerCount;
    private int recipeCount;

    public Tags(int tagId, String name, String lastActiveDateTime, int followerCount, int recipeCount) {
        this.tagId = tagId;
        this.name = name;
        this.lastActiveDateTime = lastActiveDateTime;
        this.followerCount = followerCount;
        this.recipeCount = recipeCount;
    }

    public Tags() {
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public int getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }

    public String getLastActiveDateTime() {
        return lastActiveDateTime;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", lastActiveDateTime='" + lastActiveDateTime + '\'' +
                ", followerCount=" + followerCount +
                '}';
    }
}
