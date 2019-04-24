package resources.entitys;

public class Tag {
    public static final String LOGTAG = "COOKALOG";

    private final long tagId;
    private String name;

    private Tag(final long tagId, String name) {

        this.tagId = tagId;
        this.name = name;
    }

    public long getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }
}
