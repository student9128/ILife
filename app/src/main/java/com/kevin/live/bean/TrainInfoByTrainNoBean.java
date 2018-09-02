package com.kevin.live.bean;

import java.util.List;

/**
 * Created by Kevin on 2018/9/2<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainInfoByTrainNoBean {


    /**
     * msg : success
     * result : [{"arriveTime":"----","endStationName":"北京南","startStationName":"上海虹桥","startTime":"11:26","stationName":"上海虹桥","stationNo":"01","stationTrainCode":"G130","stopoverTime":"----","trainClassName":"高速"},{"arriveTime":"11:49","startTime":"11:51","stationName":"苏州北","stationNo":"02","stopoverTime":"2分钟"},{"arriveTime":"12:01","startTime":"12:03","stationName":"无锡东","stationNo":"03","stopoverTime":"2分钟"},{"arriveTime":"12:20","startTime":"12:22","stationName":"常州北","stationNo":"04","stopoverTime":"2分钟"},{"arriveTime":"12:54","startTime":"12:56","stationName":"南京南","stationNo":"05","stopoverTime":"2分钟"},{"arriveTime":"13:14","startTime":"13:16","stationName":"滁州","stationNo":"06","stopoverTime":"2分钟"},{"arriveTime":"14:18","startTime":"14:20","stationName":"徐州东","stationNo":"07","stopoverTime":"2分钟"},{"arriveTime":"14:38","startTime":"14:40","stationName":"枣庄","stationNo":"08","stopoverTime":"2分钟"},{"arriveTime":"14:52","startTime":"14:54","stationName":"滕州东","stationNo":"09","stopoverTime":"2分钟"},{"arriveTime":"15:37","startTime":"15:44","stationName":"济南西","stationNo":"10","stopoverTime":"7分钟"},{"arriveTime":"16:08","startTime":"16:10","stationName":"德州东","stationNo":"11","stopoverTime":"2分钟"},{"arriveTime":"17:23","startTime":"17:23","stationName":"北京南","stationNo":"12","stopoverTime":"----"}]
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
         * arriveTime : ----
         * endStationName : 北京南
         * startStationName : 上海虹桥
         * startTime : 11:26
         * stationName : 上海虹桥
         * stationNo : 01
         * stationTrainCode : G130
         * stopoverTime : ----
         * trainClassName : 高速
         */

        private String arriveTime;
        private String endStationName;
        private String startStationName;
        private String startTime;
        private String stationName;
        private String stationNo;
        private String stationTrainCode;
        private String stopoverTime;
        private String trainClassName;

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getEndStationName() {
            return endStationName;
        }

        public void setEndStationName(String endStationName) {
            this.endStationName = endStationName;
        }

        public String getStartStationName() {
            return startStationName;
        }

        public void setStartStationName(String startStationName) {
            this.startStationName = startStationName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getStationNo() {
            return stationNo;
        }

        public void setStationNo(String stationNo) {
            this.stationNo = stationNo;
        }

        public String getStationTrainCode() {
            return stationTrainCode;
        }

        public void setStationTrainCode(String stationTrainCode) {
            this.stationTrainCode = stationTrainCode;
        }

        public String getStopoverTime() {
            return stopoverTime;
        }

        public void setStopoverTime(String stopoverTime) {
            this.stopoverTime = stopoverTime;
        }

        public String getTrainClassName() {
            return trainClassName;
        }

        public void setTrainClassName(String trainClassName) {
            this.trainClassName = trainClassName;
        }
    }
}
