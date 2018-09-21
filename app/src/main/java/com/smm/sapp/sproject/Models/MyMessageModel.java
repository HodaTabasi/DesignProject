package com.smm.sapp.sproject.Models;

public class MyMessageModel {

    /**
     * id : 1
     * accepted : 0
     * created_at : 2018-08-20 14:49:20
     * updated_at : 2018-08-20 14:49:20
     * user : {"id":3,"name":"مريمم","email":"m@hotmail.com","type":"client","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"0","owe":"0","total":"0","photo_link":"http://mustafa.smmim.com/waell/public/images/1536906948.png","verify":"0","phone":"0592414346","bio":"bio","dob":"17/09/18","gender":"female","active":"1","busniess_type":"individual","created_at":"2018-08-20 13:01:03","updated_at":"2018-09-19 15:38:07"}
     * my_user : {"id":2,"name":"mustafa kassab","email":"mk@mk.com","type":"worker","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"1000","owe":"0","total":"0","photo_link":"http://localhost:8585/waell/public/images/1534770520.jpg","verify":"4809","phone":"0592414345","bio":"bio biobio biobio biobio biobio biobio biobio bio","dob":"2/4/1995","gender":"male","active":"1","busniess_type":null,"created_at":"2018-08-20 13:01:03","updated_at":"2018-09-19 15:57:38"}
     * last_message : {"id":4,"first_id":"2","message":"hi","seen":"0","gr_id":"1","created_at":"2018-09-03 14:19:27","updated_at":"2018-09-03 14:19:27","user":{"id":3,"name":"مريمم","email":"m@hotmail.com","type":"client","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"0","owe":"0","total":"0","photo_link":"http://mustafa.smmim.com/waell/public/images/1536906948.png","verify":"0","phone":"0592414346","bio":"bio","dob":"17/09/18","gender":"female","active":"1","busniess_type":"individual","created_at":"2018-08-20 13:01:03","updated_at":"2018-09-19 15:38:07"}}
     * seen : 0
     */

    private int id;
    private String accepted;
    private String created_at;
    private String updated_at;
    private User user;
    private User my_user;
    private LastMessageBean last_message;
    private String seen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getMy_user() {
        return my_user;
    }

    public void setMy_user(User my_user) {
        this.my_user = my_user;
    }

    public LastMessageBean getLast_message() {
        return last_message;
    }

    public void setLast_message(LastMessageBean last_message) {
        this.last_message = last_message;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public static class LastMessageBean {
        /**
         * id : 4
         * first_id : 2
         * message : hi
         * seen : 0
         * gr_id : 1
         * created_at : 2018-09-03 14:19:27
         * updated_at : 2018-09-03 14:19:27
         * user : {"id":3,"name":"مريمم","email":"m@hotmail.com","type":"client","job_type":"wall","fcm_token":null,"own_projects":"0","worked_projects":"0","credit":"0","owe":"0","total":"0","photo_link":"http://mustafa.smmim.com/waell/public/images/1536906948.png","verify":"0","phone":"0592414346","bio":"bio","dob":"17/09/18","gender":"female","active":"1","busniess_type":"individual","created_at":"2018-08-20 13:01:03","updated_at":"2018-09-19 15:38:07"}
         */

        private int id;
        private String first_id;
        private String message;
        private String seen;
        private String gr_id;
        private String created_at;
        private String updated_at;
        private User user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirst_id() {
            return first_id;
        }

        public void setFirst_id(String first_id) {
            this.first_id = first_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSeen() {
            return seen;
        }

        public void setSeen(String seen) {
            this.seen = seen;
        }

        public String getGr_id() {
            return gr_id;
        }

        public void setGr_id(String gr_id) {
            this.gr_id = gr_id;
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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
