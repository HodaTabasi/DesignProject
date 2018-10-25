package com.smm.sapp.sproject.Models;

public class NotificationsModels {

    /**
     * id : 199
     * noti_ar : رسالة جديدة
     * target_id : 156
     * target_type : message
     * user_id : 2
     * seen : 1
     * created_at : 2018-10-25 16:03:48
     * updated_at : 2018-10-25 13:03:48
     * message : {"id":156,"message":"hickfjsp","seen":"1","file_link":null,"gr_id":"1","created_at":"2018-10-25 12:56:13","updated_at":"2018-10-25 13:03:43","user":{"id":3,"name":"فادي","email":"zord.1@msn.com","type":"client","job_type":"wall","fcm_token":"f72gOnmRfIU:APA91bGMZmxSk8LSwK6OmCr36mPPATJhxxQzf0I4q_tmZhNhHPYE10j6EMSRS3CnAYcB8mwy0gY1hYTg9eXB87_mw2HE8bQbpA4qAe_dAFZizRTVMbRYmNTO5P-cpOu9UU349iUdurx_","own_projects":"0","worked_projects":"0","credit":"0","owe":"0","total":"0","photo_link":"http://mustafa.smmim.com/waell/public/images/1536906948.png","verify":"6576","phone":"0592414346","bio":"bio","dob":"07/10/2018","gender":"male","active":"1","busniess_type":"individual","created_at":"2018-08-20 13:01:03","updated_at":"2018-10-21 12:33:28"},"sender_id":3}
     */

    private int id;
    private String noti_ar;
    private String target_id;
    private String target_type;
    private String user_id;
    private String seen;
    private String created_at;
    private String updated_at;
    private MessageDetails message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoti_ar() {
        return noti_ar;
    }

    public void setNoti_ar(String noti_ar) {
        this.noti_ar = noti_ar;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_type() {
        return target_type;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
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

    public MessageDetails getMessage() {
        return message;
    }

    public void setMessage(MessageDetails message) {
        this.message = message;
    }
}
