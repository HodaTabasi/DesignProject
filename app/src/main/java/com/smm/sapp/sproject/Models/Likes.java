package com.smm.sapp.sproject.Models;

public class Likes {

    /**
     * id : 2
     * target_id : 1
     * target_type : project
     * created_by : 3
     * created_at : 2018-08-20 14:28:44
     * updated_at : 2018-08-20 14:28:44
     */

    private int id;
    private String target_id;
    private String target_type;
    private String created_by;
    private String created_at;
    private String updated_at;
    private ProjectsLikeModels project;
    private User user;
    private PWorks pwork;

    public Likes(int id, String target_id, String target_type, String created_by, String created_at, String updated_at, ProjectsLikeModels project) {
        this.id = id;
        this.target_id = target_id;
        this.target_type = target_type;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.project = project;
    }

    public Likes(int id, String target_id, String target_type, String created_by, String created_at, String updated_at, User user) {
        this.id = id;
        this.target_id = target_id;
        this.target_type = target_type;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
    }

    public Likes(int id, String target_id, String target_type, String created_by, String created_at, String updated_at, PWorks pWork) {
        this.id = id;
        this.target_id = target_id;
        this.target_type = target_type;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pwork = pWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_type() {
        return target_type;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
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

    public ProjectsLikeModels getProject() {
        return project;
    }

    public void setProject(ProjectsLikeModels project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PWorks getpWork() {
        return pwork;
    }

    public void setpWork(PWorks pWork) {
        this.pwork = pWork;
    }
}
