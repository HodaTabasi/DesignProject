package com.smm.sapp.sproject.Models;

public class ShippingModel {

    /**
     * url : https://www.paytabs.com/UWdqEXwhQBytwqGFGdUZmYs3MYYkAg6BeQOupPNd12AujtE/35H988Qu5f2VVgkepMI9K6BoAEBlIw1xOcCns0MnE8kadhE/qiQJLK9T75RH0ISH013TxjYKNAVkcAM9mkOZ1655wGVjkn4/v0ZVOfKNyJadaYxTOE4vhFCq2vI8SdIT2bzR24VbhRxXQNLX6SyS10OmIQYbyPkJ2rTFpNfuFSGMmdgII_G1h9aklg
     * status : {"success":true,"code":200,"message":"Payment Url returned"}
     */

    private String url;
    private StatusBean status;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {
        /**
         * success : true
         * code : 200
         * message : Payment Url returned
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
}
