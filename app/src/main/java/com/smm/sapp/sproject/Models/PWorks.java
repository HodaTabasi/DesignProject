package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class PWorks implements Parcelable {

    /**
     * id : 2
     * name : nice
     * descr : nice nice
     * mdate : 2-2-2015
     * user_id : 3
     * photo_link : http://mustafa.smmim.com/waell/public/images/1536946030.PNG
     * work_link :
     * views : 0
     * likes : 0
     * created_at : 2018-09-14 17:27:10
     * updated_at : 2018-09-14 17:27:10
     */

    private int id;
    private String name;
    private String descr;
    private String mdate;
    private String user_id;
    private String photo_link;
    private String work_link;
    private String views;
    private String likes;
    private String created_at;
    private String updated_at;

    protected PWorks(Parcel in) {
        id = in.readInt();
        name = in.readString();
        descr = in.readString();
        mdate = in.readString();
        user_id = in.readString();
        photo_link = in.readString();
        work_link = in.readString();
        views = in.readString();
        likes = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<PWorks> CREATOR = new Creator<PWorks>() {
        @Override
        public PWorks createFromParcel(Parcel in) {
            return new PWorks(in);
        }

        @Override
        public PWorks[] newArray(int size) {
            return new PWorks[size];
        }
    };

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

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public String getWork_link() {
        return work_link;
    }

    public void setWork_link(String work_link) {
        this.work_link = work_link;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(descr);
        parcel.writeString(mdate);
        parcel.writeString(user_id);
        parcel.writeString(photo_link);
        parcel.writeString(work_link);
        parcel.writeString(views);
        parcel.writeString(likes);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
