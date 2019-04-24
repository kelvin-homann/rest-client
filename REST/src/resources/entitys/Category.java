package resources.entitys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Category {

    public static final int CHANGED_NAME = 0x00000001;
    public static final int CHANGED_PARENTCATEGORYID = 0x00000002;
    public static final int CHANGED_DESCRIPTION = 0x00000004;
    public static final int CHANGED_IMAGEID = 0x00000008;
    public static final int CHANGED_IMAGEFILENAME = 0x00000010;
    public static final int CHANGED_SORTPREFIX = 0x00000020;
    public static final int CHANGED_BROWSABLE = 0x00000040;
    public static final int CHANGED_FORCE_UPDATE = 0xffffffff;

    private int changeState = 0;
    private boolean committed = false;

    private final long categoryId;
    private long languageId;
    private long parentCategoryId;
    private String name;
    private String description;
    private long imageId;
    private String imageFileName;
    private String sortPrefix;
    private boolean browsable;

    public Category(final long categoryId){
        this.categoryId = categoryId;
    }

    /**
     * Gets the change state of this category instance that reflects what fields have changed
     * since the last synchronization. Basically an extended dirty flag.
     * @return
     */
    public long getChangeState() {
        return changeState;
    }

    public void resetChangeState() {
        changeState = 0;
    }

    public void setForceUpdate() {
        changeState = CHANGED_FORCE_UPDATE;
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

    public long getCategoryId() {
        return categoryId;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
        changeState |= CHANGED_PARENTCATEGORYID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        changeState |= CHANGED_NAME;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        changeState |= CHANGED_DESCRIPTION;
    }

    public long getImageId() {
        return imageId;
    }

    private void setImageId(long imageId) {
        this.imageId = imageId;
        changeState |= CHANGED_IMAGEID;
    }

    public String getImageFileName() {
        return imageFileName;
    }


    public String getSortPrefix() {
        return sortPrefix;
    }

    public void setSortPrefix(String sortPrefix) {
        this.sortPrefix = sortPrefix;
        changeState |= CHANGED_SORTPREFIX;
    }

    public boolean isBrowsable() {
        return browsable;
    }

    public void setBrowsable(boolean browsable) {
        this.browsable = browsable;
        changeState |= CHANGED_BROWSABLE;
    }

    public String toJSON(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
