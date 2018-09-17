package com.example.maryam.sproject.Models;

public class PhotoModel {

    /**
     * id : 17
     * photo_link : http://localhost/waell/public/images/8798407441535145117.png
     * project_id : 17
     * created_at : 2018-08-24 22:11:57
     * updated_at : 2018-08-24 22:11:57
     */

    private int id;
    private String photo_link;
    private String project_id;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
