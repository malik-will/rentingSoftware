package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;
public class Item {
    private String _id;
    private String _itemName;
    private String _fee;
    private String _description;
    private Category _category;

    public Item(String id, String itemName, String description, Category category) {
        _id = id;
        _itemName = itemName;
        _description = description;
        _category = category;
    }

    public Item(String id, String itemName, String description, String fee, String selectedCategory) {
        _id = id;
        _itemName = itemName;
        _description = description;
        _fee = fee;
      //  _category = Category.fromName(selectedCategory);
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setFee(String fee) {
        _fee = fee;
    }

    public String getFee() {
        return _fee;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getId() {
        return _id;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    public String getCategoryName() {
        return _category != null ? _category.getCategoryName() : null;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }
}