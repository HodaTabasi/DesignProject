package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectsModels implements Parcelable {


    private int id;
    private String name;
    private String descr;
    private String type;
    private String balance;
    private String user_id;
    private String status;
    private String with_id;
    private String accepted;
    private String created_at;
    private String updated_at;
    private AddtionInfoBean addtion_info;
    private User user;
    private List<Attachments> attachs;
    private List<OfferModel> offers;
    private List<PhotoModel> photos;
    private List<Attachments> similars;


    @SerializedName("private")
    private String Private;

    protected ProjectsModels(Parcel in) {
        id = in.readInt();
        name = in.readString();
        descr = in.readString();
        type = in.readString();
        balance = in.readString();
        user_id = in.readString();
        status = in.readString();
        with_id = in.readString();
        accepted = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        offers = in.createTypedArrayList(OfferModel.CREATOR);
        Private = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(descr);
        dest.writeString(type);
        dest.writeString(balance);
        dest.writeString(user_id);
        dest.writeString(status);
        dest.writeString(with_id);
        dest.writeString(accepted);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeParcelable(user, flags);
        dest.writeTypedList(offers);
        dest.writeString(Private);
    }

    public static final Creator<ProjectsModels> CREATOR = new Creator<ProjectsModels>() {
        @Override
        public ProjectsModels createFromParcel(Parcel in) {
            return new ProjectsModels(in);
        }

        @Override
        public ProjectsModels[] newArray(int size) {
            return new ProjectsModels[size];
        }
    };

    public String getPrivate()
    {
        return Private;
    }
    public void setPrivate(String Private)
    {
        this.Private = Private;
    }

    public String getWith_id() {
        return with_id;
    }

    public void setWith_id(String with_id) {
        this.with_id = with_id;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public AddtionInfoBean getAddtion_info() {
        return addtion_info;
    }

    public void setAddtion_info(AddtionInfoBean addtion_info) {
        this.addtion_info = addtion_info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attachments> getAttachs() {
        return attachs;
    }

    public void setAttachs(List<Attachments> attachs) {
        this.attachs = attachs;
    }

    public List<OfferModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferModel> offers) {
        this.offers = offers;
    }

    public List<PhotoModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoModel> photos) {
        this.photos = photos;
    }

    public List<Attachments> getSimilars() {
        return similars;
    }

    public void setSimilars(List<Attachments> similars) {
        this.similars = similars;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    public static class AddtionInfoBean {

        /**
         * id : 10
         * area : 250متر مربع
         * city : جدة
         * colors : احمر واخضر وبني
         * lat : 66,66
         * style : ستايل جديد
         * project_id : 26
         * lng : 55,55
         * created_at : 2018-09-11 13:30:53
         * updated_at : 2018-09-11 13:30:53
         */

        private int id;
        private String area;
        private String city;
        private String colors;
        private String lat;
        private String style;
        private String project_id;
        private String lng;

        private String dur;
        private String about;

        private String newp;
        private String d_type;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColors() {
            return colors;
        }

        public void setColors(String colors) {
            this.colors = colors;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDur() {
            return dur;
        }

        public void setDur(String dur) {
            this.dur = dur;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getNewp() {
            return newp;
        }

        public void setNewp(String newp) {
            this.newp = newp;
        }

        public String getD_type() {
            return d_type;
        }

        public void setD_type(String d_type) {
            this.d_type = d_type;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
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
