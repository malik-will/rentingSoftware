package com.example.myapplication;


public class Category {

    private String _id;
    private String _category;
    private String _description;
    public Category() {
    }
    public Category(String id, String categoryname, String description) {
        _id = id;
        _category = categoryname;
        _description = description;
    }
    public Category(String productname, String description) {
        _category = productname;
        _description = description;
    }
    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setCategoryName(String category) {
        _category = category;
    }
    public String getCategoryName() {
        return _category;
    }
    public void setDescription(String description) {
        _description = description;
    }
    public String getDescription() {
        return _description;
    }
}
