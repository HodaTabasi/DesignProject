package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    /**
     * id : 2
     * name : mustafa kassab
     * email : mk@mk.com
     * type : worker
     * job_type : moshen
     * fcm_token : null
     * own_projects : 0
     * worked_projects : 0
     * credit : 500
     * owe : 0
     * total : 0
     * photo_link : http://localhost:8585/waell/public/images/1534770520.jpg
     * verify : 4809
     * phone : 0592414345
     * bio : bio biobio biobio biobio biobio biobio biobio bio
     * dob : 2/4/1995
     * gender : male
     * active : 1
     * busniess_type : null
     * created_at : 2018-08-20 13:01:03
     * updated_at : 2018-08-30 11:22:54
     * banks : [{"id":1,"name":"alahly","number":"4651651651651651","iban":"6516516516516","user_id":"2","created_at":"2018-08-20 14:45:53","updated_at":"2018-08-20 14:45:53"}]
     * skills : []
     * pworks : []
     * likes : []
     * comments : []
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    private String rate;
    private ArrayList<BanksBean> banks;
    private ArrayList<SkillsModel> skills;
    private ArrayList<PWorks> pworks;
    private ArrayList<Likes> likes;
    private ArrayList<Comments> comments;

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

    public ArrayList<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<BanksBean> banks) {
        this.banks = banks;
    }

    public ArrayList<SkillsModel> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<SkillsModel> skills) {
        this.skills = skills;
    }

    public ArrayList<PWorks> getPworks() {
        return pworks;
    }

    public void setPworks(ArrayList<PWorks> pworks) {
        this.pworks = pworks;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Likes> likes) {
        this.likes = likes;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    public static class BanksBean implements Parcelable {
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

        protected BanksBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            number = in.readString();
            iban = in.readString();
            user_id = in.readString();
            created_at = in.readString();
            updated_at = in.readString();
        }

        public static final Creator<BanksBean> CREATOR = new Creator<BanksBean>() {
            @Override
            public BanksBean createFromParcel(Parcel in) {
                return new BanksBean(in);
            }

            @Override
            public BanksBean[] newArray(int size) {
                return new BanksBean[size];
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(number);
            dest.writeString(iban);
            dest.writeString(user_id);
            dest.writeString(created_at);
            dest.writeString(updated_at);
        }
    }
}
