package com.harry.viewpager_fragement_pt1;

public enum ModelCourse {

    RED(R.string.red, R.layout.view_red),
    BLUE(R.string.blue, R.layout.view_blue),
    GREEN(R.string.green, R.layout.view_green);

    private int mTitleID;
    private int mLayoutID;

    ModelCourse(int mTitleID, int mLayoutID) {
        this.mTitleID = mTitleID;
        this.mLayoutID = mLayoutID;
    }

    public int getmTitleID() {
        return mTitleID;
    }

    public int getmLayoutID() {
        return mLayoutID;
    }
}
