package com.example.myapplication;

public class Request {
    private String _requestId;
    private String _itemId;
    private String _userId;
    private String _requestDate;
    private Boolean _requestStatus;


    public Request(String requestId, String itemId, String userId, String requestDate, Boolean requestStatus) {
        _requestId = requestId;
        _itemId = itemId;
        _userId = userId;
        _requestDate = requestDate;
        _requestStatus = requestStatus;
    }

    // Getters and setters
    public String get_requestId() {
        return _requestId;
    }

    public void set_requestId(String _requestId) {
        this._requestId = _requestId;
    }

    public String get_itemId() {
        return _itemId;
    }

    public void set_itemId(String _itemId) {
        this._itemId = _itemId;
    }

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String _userId) {
        this._userId = _userId;
    }

    public String get_requestDate() {
        return _requestDate;
    }

    public void set_requestDate(String _requestDate) {
        this._requestDate = _requestDate;
    }
    public Boolean get_requestStatus() {
        return _requestStatus;
    }
    public void set_requestStatus(Boolean _requestStatus) {
        this._requestStatus = _requestStatus;
    }
}
