package com.aka_npou.sberandroidschool_finalproject.data.entity;

public class ProfileEntity {
    public long id;
    public String name;
    public String imageFilePath;

    public ProfileEntity() {
    }

    public ProfileEntity(long id, String name, String imageFilePath) {
        this.id = id;
        this.name = name;
        this.imageFilePath = imageFilePath;
    }
}
