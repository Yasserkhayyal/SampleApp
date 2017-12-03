package com.example.android.sampleapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Yasser on 12/1/2017.
 */

public class Item {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("photoUrl")
    private String photoUrl;

    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
