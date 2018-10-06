package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    /**
     * id : 3
     * name : مممم
     * email : maryf@hotmail.com
     * type : worker
     * job_type : graphic
     * fcm_token : null
     * own_projects : 0
     * worked_projects : 0
     * credit : 0
     * owe : 0
     * total : 0
     * photo_link : http://mustafa.smmim.com/waell/public/images/1536906948.png
     * verify : 0
     * phone : 0592414346
     * bio : ممم
     * dob : 23/09/18
     * gender : female
     * active : 1
     * busniess_type : company
     * created_at : 2018-08-20 13:01:03
     * updated_at : 2018-09-14 15:12:16
     */

    private int id;
    private String name;
    private String email;
    private String type;
    private String job_type;
    private Object fcm_token;
    private String own_projects;
    private String worked_projects;
    private String credit;
    private String owe;
    private String total;
    private String photo_link;
    private String verify;
    private String phone;
    private String bio;
    private String dob;
    private String gender;
    private String active;
    private String busniess_type;
    private String created_at;
    private String updated_at;
    private String rate;

    private String token;

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        type = in.readString();
        job_type = in.readString();
        own_projects = in.readString();
        worked_projects = in.readString();
        credit = in.readString();
        owe = in.readString();
        total = in.readString();
        photo_link = in.readString();
        verify = in.readString();
        phone = in.readString();
        bio = in.readString();
        dob = in.readString();
        gender = in.readString();
        active = in.readString();
        busniess_type = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        token = in.readString();
        rate = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public Object getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(Object fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getOwn_projects() {
        return own_projects;
    }

    public void setOwn_projects(String own_projects) {
        this.own_projects = own_projects;
    }

    public String getWorked_projects() {
        return worked_projects;
    }

    public void setWorked_projects(String worked_projects) {
        this.worked_projects = worked_projects;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getOwe() {
        return owe;
    }

    public void setOwe(String owe) {
        this.owe = owe;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getBusniess_type() {
        return busniess_type;
    }

    public void setBusniess_type(String busniess_type) {
        this.busniess_type = busniess_type;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(type);
        dest.writeString(job_type);
        dest.writeString(own_projects);
        dest.writeString(worked_projects);
        dest.writeString(credit);
        dest.writeString(owe);
        dest.writeString(total);
        dest.writeString(photo_link);
        dest.writeString(verify);
        dest.writeString(phone);
        dest.writeString(bio);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(active);
        dest.writeString(busniess_type);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(token);
        dest.writeString(rate);

    }
}
