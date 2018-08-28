package com.kevin.live.bean;

import java.util.List;

/**
 * Created by Kevin on 2018/8/28<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class PostCodeResultBean {

    /**
     * msg : success
     * result : [{"address":["旗察素齐镇","察素齐镇太平村","察素齐镇西园村","察素齐镇中山村","察素齐镇友好村","把什乡万家沟村","只几梁乡五甲桥村","旗察素齐镇土默特左旗"],"cId":"1401","city":"呼和浩特市","dId":"14010","district":"土默特左旗","pId":"14","postNumber":"010100","province":"内蒙古自治区","size":"8"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : ["旗察素齐镇","察素齐镇太平村","察素齐镇西园村","察素齐镇中山村","察素齐镇友好村","把什乡万家沟村","只几梁乡五甲桥村","旗察素齐镇土默特左旗"]
         * cId : 1401
         * city : 呼和浩特市
         * dId : 14010
         * district : 土默特左旗
         * pId : 14
         * postNumber : 010100
         * province : 内蒙古自治区
         * size : 8
         */

        private String cId;
        private String city;
        private String dId;
        private String district;
        private String pId;
        private String postNumber;
        private String province;
        private String size;
        private List<String> address;

        public String getCId() {
            return cId;
        }

        public void setCId(String cId) {
            this.cId = cId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDId() {
            return dId;
        }

        public void setDId(String dId) {
            this.dId = dId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getPostNumber() {
            return postNumber;
        }

        public void setPostNumber(String postNumber) {
            this.postNumber = postNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }
    }
}
