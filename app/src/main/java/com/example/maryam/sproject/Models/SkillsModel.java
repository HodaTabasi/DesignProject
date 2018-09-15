package com.example.maryam.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SkillsModel implements Parcelable {

    /**
     * id : 1
     * name : desginer
     * years : 5
     * user_id : 3
     * created_at : 2018-09-14 17:25:46
     * updated_at : 2018-09-14 17:25:46
     */

    private int id;
    private String name;
    private String years;
    private String user_id;
    private String created_at;
    private String updated_at;

    protected SkillsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        years = in.readString();
        user_id = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<SkillsModel> CREATOR = new Creator<SkillsModel>() {
        @Override
        public SkillsModel createFromParcel(Parcel in) {
            return new SkillsModel(in);
        }

        @Override
        public SkillsModel[] newArray(int size) {
            return new SkillsModel[size];
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(years);
        parcel.writeString(user_id);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);


    }
}
