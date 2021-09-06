package com.aka_npou.sberandroidschool_finalproject.domain.model;

import android.graphics.Bitmap;

public class Profile {
    private final long id;
    private final String name;
    private final String imageFilePath;

    public Profile(long id, String name, String imageFilePath) {
        this.id = id;
        this.name = name;
        this.imageFilePath = imageFilePath;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
}
