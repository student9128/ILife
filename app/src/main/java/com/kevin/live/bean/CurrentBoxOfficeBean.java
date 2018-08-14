package com.kevin.live.bean;

import java.util.List;

/**
 * Created by Kevin on 2018/8/13<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CurrentBoxOfficeBean {

    /**
     * msg : success
     * result : [{"cur":74.82,"days":13,"name":"浴血广昌","sum":3068.21},{"cur":3023.7,"days":4,"name":"巨齿鲨","sum":37745.35},{"cur":8.36,"days":-3,"name":"欧洲攻略","sum":9.32},{"cur":205.99,"days":11,"name":"神秘世界历险记4","sum":8618.23},{"cur":4513.67,"days":4,"name":"一出好戏","sum":57840.91},{"cur":975.44,"days":18,"name":"西虹市首富","sum":234605.38},{"cur":69.4,"days":-4,"name":"美食大冒险之英雄烩","sum":142.21},{"cur":86.56,"days":11,"name":"小偷家族","sum":8601.65},{"cur":22.02,"days":40,"name":"我不是药神","sum":308211.89},{"cur":71.88,"days":11,"name":"风语咒","sum":10859.58},{"cur":1406.95,"days":4,"name":"爱情公寓","sum":50361.96},{"cur":37.2,"days":18,"name":"狄仁杰之四大天王","sum":59226.06}]
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
         * cur : 74.82
         * days : 13
         * name : 浴血广昌
         * sum : 3068.21
         */

        private double cur;
        private int days;
        private String name;
        private double sum;

        public double getCur() {
            return cur;
        }

        public void setCur(double cur) {
            this.cur = cur;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }
    }
}
