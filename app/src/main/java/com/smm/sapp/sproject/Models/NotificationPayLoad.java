package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Map;

public class NotificationPayLoad implements Parcelable {
    private String updated_at;
    private String seen;
    private String id;
    private User user;
    private String sender_id;
    private String gr_id;
    private String message;
    private String created_at;

    public NotificationPayLoad() {
    }

    protected NotificationPayLoad(Parcel in) {
        updated_at = in.readString();
        seen = in.readString();
        id = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        sender_id = in.readString();
        gr_id = in.readString();
        message = in.readString();
        created_at = in.readString();
    }

    public static final Creator<NotificationPayLoad> CREATOR = new Creator<NotificationPayLoad>() {
        @Override
        public NotificationPayLoad createFromParcel(Parcel in) {
            return new NotificationPayLoad(in);
        }

        @Override
        public NotificationPayLoad[] newArray(int size) {
            return new NotificationPayLoad[size];
        }
    };

    public void NotificationPayLoad(Map<String, String> data) {
        this.setCreated_at(data.get("created_at"));
        this.setMessage(data.get("message"));
        this.setSender_id(data.get("sender_id"));
        this.setGr_id(data.get("gr_id"));
        this.setUpdated_at(data.get("updated_at"));
        this.setId(data.get("id"));
        this.setSeen(data.get("seen"));

    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGr_id() {
        return gr_id;
    }

    public void setGr_id(String gr_id) {
        this.gr_id = gr_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updated_at);
        dest.writeString(seen);
        dest.writeString(id);
        dest.writeParcelable(user, flags);
        dest.writeString(sender_id);
        dest.writeString(gr_id);
        dest.writeString(message);
        dest.writeString(created_at);
    }
}
