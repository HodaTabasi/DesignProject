package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProjectsLikeModels implements Parcelable {

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
    private User user;
    private ProjectsModels.AddtionInfoBean project;
    private String status;

    private String with_id;
    private String accepted;
    private String created_at;
    private String updated_at;

    @SerializedName("private")
    private String Private;


    protected ProjectsLikeModels(Parcel in) {
        id = in.readInt();
        name = in.readString();
        descr = in.readString();
        type = in.readString();
        balance = in.readString();
        user_id = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        status = in.readString();
        with_id = in.readString();
        accepted = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        Private = in.readString();
    }

    public static final Creator<ProjectsLikeModels> CREATOR = new Creator<ProjectsLikeModels>() {
        @Override
        public ProjectsLikeModels createFromParcel(Parcel in) {
            return new ProjectsLikeModels(in);
        }

        @Override
        public ProjectsLikeModels[] newArray(int size) {
            return new ProjectsLikeModels[size];
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public ProjectsModels.AddtionInfoBean getProject() {
        return project;
    }

    public void setProject(ProjectsModels.AddtionInfoBean project) {
        this.project = project;
    }

    public String getWith_id() {
        return with_id;
    }

    public void setWith_id(String with_id) {
        this.with_id = with_id;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getPrivate() {
        return Private;
    }

    public void setPrivate(String aPrivate) {
        Private = aPrivate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(descr);
        dest.writeString(type);
        dest.writeString(balance);
        dest.writeString(user_id);
        dest.writeParcelable(user, flags);
        dest.writeString(status);
        dest.writeString(with_id);
        dest.writeString(accepted);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(Private);
    }
}
