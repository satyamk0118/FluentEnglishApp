package com.example.registration;

public class Course {
    private int imgResourceId;

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String courseName;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private String details;

    public Course(int imgResourceId, String courseName, String details) {
        this.imgResourceId = imgResourceId;
        this.courseName = courseName;
        this.details = details;
    }
}
