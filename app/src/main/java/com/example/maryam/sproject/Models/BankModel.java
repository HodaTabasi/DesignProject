package com.example.maryam.sproject.Models;

public class BankModel {

    private String bankName;
    private String accounteNO;
    private String EbanNO;

    public BankModel() {
    }

    public BankModel(String bankName, String accounteNO, String ebanNO) {
        this.bankName = bankName;
        this.accounteNO = accounteNO;
        EbanNO = ebanNO;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccounteNO() {
        return accounteNO;
    }

    public void setAccounteNO(String accounteNO) {
        this.accounteNO = accounteNO;
    }

    public String getEbanNO() {
        return EbanNO;
    }

    public void setEbanNO(String ebanNO) {
        EbanNO = ebanNO;
    }
}
