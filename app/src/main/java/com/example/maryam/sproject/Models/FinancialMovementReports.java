package com.example.maryam.sproject.Models;

public class FinancialMovementReports {

    /**
     * id : 1
     * total : 500
     * descr : شحن رصيدي
     * pay_from : 2
     * pay_to : 2
     * delivered : 1
     * created_at : 2018-08-20 15:03:21
     * updated_at : 2018-08-20 15:03:21
     */

    private int id;
    private String total;
    private String descr;
    private String pay_from;
    private String pay_to;
    private String delivered;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPay_from() {
        return pay_from;
    }

    public void setPay_from(String pay_from) {
        this.pay_from = pay_from;
    }

    public String getPay_to() {
        return pay_to;
    }

    public void setPay_to(String pay_to) {
        this.pay_to = pay_to;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
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
