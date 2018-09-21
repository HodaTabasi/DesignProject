package com.smm.sapp.sproject.Models;

public class MessageDetails {

    /**
     * id : 5
     * message : welcome
     * seen : 0
     * gr_id : 1
     * created_at : 2018-09-19 22:07:14
     * updated_at : 2018-09-19 22:07:14
     * user : {"id":2,"name":"mustafa kassab","email":"mk@mk.com","type":"worker","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"1000","owe":"0","total":"0","photo_link":"http://localhost:8585/waell/public/images/1534770520.jpg","verify":"4809","phone":"0592414345","bio":"bio biobio biobio biobio biobio biobio biobio bio","dob":"2/4/1995","gender":"male","active":"1","busniess_type":null,"created_at":"2018-08-20 13:01:03","updated_at":"2018-09-19 15:57:38"}
     * sender_id : 2
     */

    private int id;
    private String message;
    private String seen;
    private String gr_id;
    private String created_at;
    private String updated_at;
    private User user;
    private String sender_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getGr_id() {
        return gr_id;
    }

    public void setGr_id(String gr_id) {
        this.gr_id = gr_id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
}
