package client.Entitys;

import java.util.ArrayList;

/**
 * Represents the Tags for the Post Methods
 */
public class POSTTags {
    private int userId;
    private String accessToken;
    private ArrayList<String> tags;

    public POSTTags(int userId, String accessToken, ArrayList<String> tags) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.tags = tags;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
