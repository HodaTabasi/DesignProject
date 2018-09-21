package com.smm.sapp.sproject.Models;

public class BankModel {
    /**
     * id : 1
     * name : alahly
     * number : 4651651651651651
     * iban : 6516516516516
     * user_id : 2
     * created_at : 2018-08-20 14:45:53
     * updated_at : 2018-08-20 14:45:53
     */

    private int id;
    private String name;
    private String number;
    private String iban;
    private String user_id;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
