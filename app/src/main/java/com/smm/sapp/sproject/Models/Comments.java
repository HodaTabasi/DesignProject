package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Comments implements Parcelable{

    /**
     * id : 1
     * created_by : 3
     * target_id : 1
     * comment : nice
     * target_type : project
     * rate : 3
     * rate2 : 0
     * rate3 : 0
     * rate4 : 0
     * rate5 : 0
     * created_at : 2018-09-10 08:08:22
     * updated_at : 2018-09-10 08:08:22
     */

    private int id;
    private String created_by;
    private String target_id;
    private String comment;
    private String target_type;
    private String rate;
    private String rate2;
    private String rate3;
    private String rate4;
    private String rate5;
    private String created_at;
    private String updated_at;

    protected Comments(Parcel in) {
        id = in.readInt();
        created_by = in.readString();
        target_id = in.readString();
        comment = in.readString();
        target_type = in.readString();
        rate = in.readString();
        rate2 = in.readString();
        rate3 = in.readString();
        rate4 = in.readString();
        rate5 = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTarget_type() {
        return target_type;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getRate3() {
        return rate3;
    }

    public void setRate3(String rate3) {
        this.rate3 = rate3;
    }

    public String getRate4() {
        return rate4;
    }

    public void setRate4(String rate4) {
        this.rate4 = rate4;
    }

    public String getRate5() {
        return rate5;
    }

    public void setRate5(String rate5) {
        this.rate5 = rate5;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(created_by);
        dest.writeString(target_id);
        dest.writeString(comment);
        dest.writeString(target_type);
        dest.writeString(rate);
        dest.writeString(rate2);
        dest.writeString(rate3);
        dest.writeString(rate4);
        dest.writeString(rate5);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
