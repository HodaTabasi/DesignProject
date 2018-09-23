package com.smm.sapp.sproject.Models;

public class ProjectsLikeModels {

    /**
     * id : 1
     * name : عمل مخطط حساب كميات لخزان ارضي
     * descr : كبير ي كبير
     * type : arch
     * balance : 500
     * user_id : 3
     * status : 0
     * created_at : 2018-08-23 20:21:40
     * updated_at : 2018-08-23 20:21:40
     */

    private int id;
    private String name;
    private String descr;
    private String type;
    private String balance;
    private String user_id;
    private String status;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
