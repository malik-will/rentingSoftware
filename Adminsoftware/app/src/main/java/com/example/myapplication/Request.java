package com.example.myapplication;
public class Request {
    private String itemName;
    private String description;
    private String fee;
    private String startDate;
    private String endDate;
    private String categoryName;
    private String ownerID;
    private String myID;
    private String status;

    public Request() {}

    public Request(String itemName, String description, String fee, String startDate, String endDate, String categoryName, String ownerID, String myID, String status){
        this.itemName = itemName;
        this.description = description;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryName = categoryName;
        this.ownerID = ownerID;
        this.myID = myID;
        this.status = status;

    }

    // Getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
    public String getMyID() {
        return myID;
        }
    public void setMyID(String myID) {
        this.myID = myID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}