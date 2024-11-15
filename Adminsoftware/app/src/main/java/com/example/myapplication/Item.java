package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private String _id;
    private String _Itemname;
    private int _sku;
    private String _description;
    private Category _category;



    public Item(String id, String productname, String description, Category category) {
        _id = id;
        _Itemname = productname;
        _description = description;
        _category = category;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("productname", _Itemname);
        result.put("sku", _sku);

        return result;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }

    public String getCategoryName() {
        return _category.getCategoryName();
    }
    public void setDescription(String description) {
        _description = description;
    }
    public String getDescription() {
        return _description;
    }


}
