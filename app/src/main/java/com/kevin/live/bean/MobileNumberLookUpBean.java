package com.kevin.live.bean;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class MobileNumberLookUpBean {

    /**
     * result : {"mobilenumber":"1821778","mobilearea":"上海","mobiletype":"移动182卡","areacode":"021","postcode":"200000"}
     * error_code : 0
     * reason : Succes
     */


    private ResultBean result;
    private int error_code;
    private String reason;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultBean {
        /**
         * mobilenumber : 1821778
         * mobilearea : 上海
         * mobiletype : 移动182卡
         * areacode : 021
         * postcode : 200000
         */

        private String mobilenumber;
        private String mobilearea;
        private String mobiletype;
        private String areacode;
        private String postcode;

        public String getMobilenumber() {
            return mobilenumber;
        }

        public void setMobilenumber(String mobilenumber) {
            this.mobilenumber = mobilenumber;
        }

        public String getMobilearea() {
            return mobilearea;
        }

        public void setMobilearea(String mobilearea) {
            this.mobilearea = mobilearea;
        }

        public String getMobiletype() {
            return mobiletype;
        }

        public void setMobiletype(String mobiletype) {
            this.mobiletype = mobiletype;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }
    }
}
