package com.example.android.sampleapp.models;

import com.example.android.sampleapp.utils.APIManager;

import java.util.ArrayList;
import java.util.List;

/**
 * API response model for {@link APIManager#getMenuItems()}
 */

public class ResponseModel{

    private List<Item> items;

    public ResponseModel(){
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
