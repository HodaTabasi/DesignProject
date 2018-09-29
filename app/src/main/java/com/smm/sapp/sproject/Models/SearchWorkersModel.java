package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchWorkersModel implements Parcelable {

    /**
     * id : 2
     * name : كساب
     * email : mk@mk.com
     * type : worker
     * job_type : wall
     * fcm_token : null
     * own_projects : 0
     * worked_projects : 0
     * credit : 1000
     * owe : 0
     * total : 0
     * photo_link : http://localhost:8585/waell/public/images/1534770520.jpg
     * verify : 3221
     * phone : 0592414345
     * bio : bio biobio biobio biobio biobio biobio biobio bio
     * dob : 2/4/1995
     * gender : male
     * active : 1
     * busniess_type : individual
     * created_at : 2018-08-20 13:01:03
     * updated_at : 2018-09-27 15:57:55
     * rate : 1
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
    private int rate;

    protected SearchWorkersModel(Parcel in) {
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
        rate = in.readInt();
    }

    public static final Creator<SearchWorkersModel> CREATOR = new Creator<SearchWorkersModel>() {
        @Override
        public SearchWorkersModel createFromParcel(Parcel in) {
            return new SearchWorkersModel(in);
        }

        @Override
        public SearchWorkersModel[] newArray(int size) {
            return new SearchWorkersModel[size];
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(type);
        parcel.writeString(job_type);
        parcel.writeString(own_projects);
        parcel.writeString(worked_projects);
        parcel.writeString(credit);
        parcel.writeString(owe);
        parcel.writeString(total);
        parcel.writeString(photo_link);
        parcel.writeString(verify);
        parcel.writeString(phone);
        parcel.writeString(bio);
        parcel.writeString(dob);
        parcel.writeString(gender);
        parcel.writeString(active);
        parcel.writeString(busniess_type);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeInt(rate);
    }
}
