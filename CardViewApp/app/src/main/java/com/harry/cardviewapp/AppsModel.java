package com.harry.cardviewapp;

public class AppsModel {

    // step - 1 : Variables
    private String name;
    private int numOfDownloads;
    private int thumbnail;

    // Step - 2: Constructors

    public AppsModel() {
    }

    public AppsModel(String name, int numOfDownloads, int thumbnail) {
        this.name = name;
        this.numOfDownloads = numOfDownloads;
        this.thumbnail = thumbnail;
    }

    // Step 3 - Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDownloads() {
        return numOfDownloads;
    }

    public void setNumOfDownloads(int numOfDownloads) {
        this.numOfDownloads = numOfDownloads;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
