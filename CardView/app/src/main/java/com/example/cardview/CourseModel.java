package com.example.cardview;

public class CourseModel {

    private String course_title;
    private int course_image;

    // constructor
    public CourseModel(String course_title, int course_image) {
        this.course_title = course_title;
        this.course_image = course_image;
    }

    //Getter & Setter

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public int getCourse_image() {
        return course_image;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }
}
