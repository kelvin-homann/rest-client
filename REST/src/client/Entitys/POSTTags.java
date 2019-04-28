package client.Entitys;

import javafx.scene.control.Label;

public class POSTTags {
    private int userId;
    private String accesstoken;
    private String name;

    public int getUserId() {
        return userId;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getName() {
        return name;
    }

    public POSTTags(int userId, String accesstoken, String name) {
        this.userId = userId;
        this.accesstoken = accesstoken;
        this.name = name;
    }
}
