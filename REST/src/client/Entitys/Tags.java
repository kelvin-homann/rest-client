package client.Entitys;

public class Tags {
    private int tagId;
    private String name;
    private String lastActiveDateTime;
    private int followerCount;

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
