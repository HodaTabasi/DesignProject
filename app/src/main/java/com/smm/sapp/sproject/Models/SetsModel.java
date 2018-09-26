package com.smm.sapp.sproject.Models;

public class SetsModel {


    /**
     * status : {"success":true,"code":200,"message":"sets Returned"}
     * policy : {"id":1,"keyy":"policy","value":"policy","created_at":null,"updated_at":null}
     * rights : {"id":2,"keyy":"rights","value":"rights","created_at":null,"updated_at":null}
     * about : {"id":3,"keyy":"about","value":"about","created_at":null,"updated_at":null}
     * Questions : {"id":4,"keyy":"Questions","value":"Questions","created_at":null,"updated_at":null}
     */

    private StatusBean status;
    private PolicyBean policy;
    private RightsBean rights;
    private AboutBean about;
    private QuestionsBean Questions;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public PolicyBean getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyBean policy) {
        this.policy = policy;
    }

    public RightsBean getRights() {
        return rights;
    }

    public void setRights(RightsBean rights) {
        this.rights = rights;
    }

    public AboutBean getAbout() {
        return about;
    }

    public void setAbout(AboutBean about) {
        this.about = about;
    }

    public QuestionsBean getQuestions() {
        return Questions;
    }

    public void setQuestions(QuestionsBean Questions) {
        this.Questions = Questions;
    }

    public static class StatusBean {
        /**
         * success : true
         * code : 200
         * message : sets Returned
         */

        private boolean success;
        private int code;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class PolicyBean {
        /**
         * id : 1
         * keyy : policy
         * value : policy
         * created_at : null
         * updated_at : null
         */

        private int id;
        private String keyy;
        private String value;
        private Object created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyy() {
            return keyy;
        }

        public void setKeyy(String keyy) {
            this.keyy = keyy;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class RightsBean {
        /**
         * id : 2
         * keyy : rights
         * value : rights
         * created_at : null
         * updated_at : null
         */

        private int id;
        private String keyy;
        private String value;
        private Object created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyy() {
            return keyy;
        }

        public void setKeyy(String keyy) {
            this.keyy = keyy;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class AboutBean {
        /**
         * id : 3
         * keyy : about
         * value : about
         * created_at : null
         * updated_at : null
         */

        private int id;
        private String keyy;
        private String value;
        private Object created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyy() {
            return keyy;
        }

        public void setKeyy(String keyy) {
            this.keyy = keyy;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class QuestionsBean {
        /**
         * id : 4
         * keyy : Questions
         * value : Questions
         * created_at : null
         * updated_at : null
         */

        private int id;
        private String keyy;
        private String value;
        private Object created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyy() {
            return keyy;
        }

        public void setKeyy(String keyy) {
            this.keyy = keyy;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }
}
