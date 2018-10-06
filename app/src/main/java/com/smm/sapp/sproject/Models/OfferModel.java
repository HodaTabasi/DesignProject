package com.smm.sapp.sproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class OfferModel implements Parcelable {

    /**
     * id : 6
     * dur : 55
     * balance : 5000
     * total : 4750
     * descr : مش طبيعي ي خال
     * created_by : 2
     * project_id : 12
     * file_link : null
     * approved : 0
     * finished : 0
     * created_at : 2018-09-03 14:34:40
     * updated_at : 2018-09-03 14:34:40
     */

    private int id;
    private String dur;
    private String balance;
    private String total;
    private String descr;
    private String created_by;
    private String project_id;
    private Object file_link;
    private String approved;
    private String finished;
    private String created_at;
    private String updated_at;
    private ProjectsModels project;

    public ProjectsModels getProject() {
        return project;
    }

    public void setProject(ProjectsModels project) {
        this.project = project;
    }

    protected OfferModel(Parcel in) {
        id = in.readInt();
        dur = in.readString();
        balance = in.readString();
        total = in.readString();
        descr = in.readString();
        created_by = in.readString();
        project_id = in.readString();
        approved = in.readString();
        finished = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<OfferModel> CREATOR = new Creator<OfferModel>() {
        @Override
        public OfferModel createFromParcel(Parcel in) {
            return new OfferModel(in);
        }

        @Override
        public OfferModel[] newArray(int size) {
            return new OfferModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Object getFile_link() {
        return file_link;
    }

    public void setFile_link(Object file_link) {
        this.file_link = file_link;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
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
        dest.writeString(dur);
        dest.writeString(balance);
        dest.writeString(total);
        dest.writeString(descr);
        dest.writeString(created_by);
        dest.writeString(project_id);
        dest.writeString(approved);
        dest.writeString(finished);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
