package ra.exercise.model.entity;

public class Category {
    private int id;
    private String cateName;
    private String description;
    private boolean status;

    public Category(int id, String cateName, String description, boolean status) {
        this.id = id;
        this.cateName = cateName;
        this.description = description;
        this.status = status;
    }

    public Category() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
