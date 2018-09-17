package com.example.maryam.sproject.Models;

public class Attachments {

    /**
     * id : 11
     * file_link : http://localhost:8585/waell/public/images/16876878001535057041.docx
     * project_id : 12
     * created_at : 2018-08-23 20:44:01
     * updated_at : 2018-08-23 20:44:01
     */

    private int id;
    private String file_link;
    private String project_id;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
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
