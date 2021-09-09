package com.aka_npou.sberandroidschool_finalproject.domain.model;

public class Profile {
    private final String name;
    private final String imageFilePath;

    public Profile(String name, String imageFilePath) {
        this.name = name;
        this.imageFilePath = imageFilePath;
    }

    public String getName() {
        return name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
}
