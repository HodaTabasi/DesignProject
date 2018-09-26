package com.smm.sapp.sproject.Models;

public class PortfolioModel {

    /**
     * id : 6
     * name : nice
     * descr : nice nice
     * mdate : 2-2-2015
     * user_id : 3
     * type : null
     * photo_link : http://smm.smmim.com/waell/public/images/1537958002.jpeg
     * work_link : sadsad
     * views : 0
     * likes : 0
     * created_at : 2018-09-26 10:33:22
     * updated_at : 2018-09-26 10:33:22
     * user : {"id":3,"name":"علي","email":"zord.1@msn.com","type":"client","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"0","owe":"0","total":"0","photo_link":"http://mustafa.smmim.com/waell/public/images/1536906948.png","verify":"8628","phone":"0592414346","bio":"bio","dob":"20/09/95","gender":"male","active":"1","busniess_type":"individual","created_at":"2018-08-20 13:01:03","updated_at":"2018-09-26 16:07:20"}
     */

    private int id;
    private String name;
    private String descr;
    private String mdate;
    private String user_id;
    private Object type;
    private String photo_link;
    private String work_link;
    private String views;
    private int likes;
    private String created_at;
    private String updated_at;
    private UserBean user;

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

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 3
         * name : علي
         * email : zord.1@msn.com
         * type : client
         * job_type : wall
         * fcm_token : null
         * own_projects : 0
         * worked_projects : 0
         * credit : 0
         * owe : 0
         * total : 0
         * photo_link : http://mustafa.smmim.com/waell/public/images/1536906948.png
         * verify : 8628
         * phone : 0592414346
         * bio : bio
         * dob : 20/09/95
         * gender : male
         * active : 1
         * busniess_type : individual
         * created_at : 2018-08-20 13:01:03
         * updated_at : 2018-09-26 16:07:20
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
    }
}
