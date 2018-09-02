package com.kevin.live.bean;

import java.util.List;

/**
 * Created by Kevin on 2018/8/31<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainInfoByStationBean {

    /**
     * msg : success
     * result : [{"arriveTime":"09:30","endStationName":"北京","lishi":"15:28","pricegrw":"¥879.5","pricerw":"¥476.5","pricewz":"¥177.5","priceyw":"¥304.5","priceyz":"¥177.5","startStationName":"上海","startTime":"18:02","stationTrainCode":"T110","trainClassName":"特快"},{"arriveTime":"10:46","endStationName":"北京","lishi":"22:28","pricewz":"¥156.5","priceyw":"¥283.5","priceyz":"¥156.5","startStationName":"上海","startTime":"12:18","stationTrainCode":"1462","trainClassName":"普快"},{"arriveTime":"17:23","endStationName":"北京南","lishi":"05:57","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"11:26","stationTrainCode":"G130","trainClassName":"高速"},{"arriveTime":"12:23","endStationName":"北京南","lishi":"05:30","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"06:53","stationTrainCode":"G104","trainClassName":"高速"},{"arriveTime":"13:11","endStationName":"北京南","lishi":"05:51","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"07:20","stationTrainCode":"G108","trainClassName":"高速"},{"arriveTime":"13:48","endStationName":"北京南","lishi":"04:48","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"09:00","stationTrainCode":"G2","trainClassName":"高速"},{"arriveTime":"15:44","endStationName":"北京南","lishi":"05:50","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"09:54","stationTrainCode":"G118","trainClassName":"高速"},{"arriveTime":"15:23","endStationName":"北京南","lishi":"05:49","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"09:34","stationTrainCode":"G116","trainClassName":"高速"},{"arriveTime":"23:48","endStationName":"北京南","lishi":"04:48","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"19:00","stationTrainCode":"G8","trainClassName":"高速"},{"arriveTime":"17:00","endStationName":"北京南","lishi":"05:50","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"11:10","stationTrainCode":"G126","trainClassName":"高速"},{"arriveTime":"18:52","endStationName":"北京南","lishi":"05:50","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"13:02","stationTrainCode":"G134","trainClassName":"高速"},{"arriveTime":"16:33","endStationName":"北京南","lishi":"05:47","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"10:46","stationTrainCode":"G122","trainClassName":"高速"},{"arriveTime":"20:50","endStationName":"北京南","lishi":"05:45","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"15:05","stationTrainCode":"G146","trainClassName":"高速"},{"arriveTime":"19:23","endStationName":"北京南","lishi":"05:53","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"13:30","stationTrainCode":"G138","trainClassName":"高速"},{"arriveTime":"12:42","endStationName":"北京南","lishi":"05:32","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"07:10","stationTrainCode":"G106","trainClassName":"高速"},{"arriveTime":"19:31","endStationName":"北京南","lishi":"05:50","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"13:41","stationTrainCode":"G140","trainClassName":"高速"},{"arriveTime":"14:58","endStationName":"北京南","lishi":"04:58","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"10:00","stationTrainCode":"G14","trainClassName":"高速"},{"arriveTime":"22:24","endStationName":"北京南","lishi":"05:24","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"17:00","stationTrainCode":"G22","trainClassName":"高速"},{"arriveTime":"13:33","endStationName":"北京南","lishi":"06:03","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"07:30","stationTrainCode":"G110","trainClassName":"高速"},{"arriveTime":"12:18","endStationName":"北京南","lishi":"05:39","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"06:39","stationTrainCode":"G102","trainClassName":"高速"},{"arriveTime":"18:08","endStationName":"北京南","lishi":"05:56","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"12:12","stationTrainCode":"G132","trainClassName":"高速"},{"arriveTime":"23:01","endStationName":"北京南","lishi":"05:37","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"17:24","stationTrainCode":"G44","trainClassName":"高速"},{"arriveTime":"15:55","endStationName":"北京南","lishi":"04:55","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"11:00","stationTrainCode":"G16","trainClassName":"高速"},{"arriveTime":"14:12","endStationName":"北京南","lishi":"05:56","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"08:16","stationTrainCode":"G114","trainClassName":"高速"},{"arriveTime":"18:31","endStationName":"北京南","lishi":"06:08","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"12:23","stationTrainCode":"G412","trainClassName":"高速"},{"arriveTime":"07:45","endStationName":"北京南","lishi":"11:52","priceed":"¥309.0","pricerw":"¥615.0","pricewz":"¥309.0","startStationName":"上海","startTime":"19:53","stationTrainCode":"D322","trainClassName":"动车"},{"arriveTime":"21:12","endStationName":"北京南","lishi":"05:40","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"15:32","stationTrainCode":"G148","trainClassName":"高速"},{"arriveTime":"13:16","endStationName":"北京南","lishi":"05:16","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"08:00","stationTrainCode":"G12","trainClassName":"高速"},{"arriveTime":"16:06","endStationName":"北京南","lishi":"05:38","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"10:28","stationTrainCode":"G42","trainClassName":"高速"},{"arriveTime":"08:55","endStationName":"北京南","lishi":"11:47","priceed":"¥309.0","pricerw":"¥615.0","pricewz":"¥309.0","startStationName":"上海","startTime":"21:08","stationTrainCode":"D314","trainClassName":"动车"},{"arriveTime":"21:47","endStationName":"北京南","lishi":"05:42","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"16:05","stationTrainCode":"G150","trainClassName":"高速"},{"arriveTime":"21:52","endStationName":"北京南","lishi":"05:33","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"16:19","stationTrainCode":"G152","trainClassName":"高速"},{"arriveTime":"23:18","endStationName":"北京南","lishi":"05:44","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"17:34","stationTrainCode":"G158","trainClassName":"高速"},{"arriveTime":"18:48","endStationName":"北京南","lishi":"04:48","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"14:00","stationTrainCode":"G4","trainClassName":"高速"},{"arriveTime":"11:55","endStationName":"北京南","lishi":"04:55","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"07:00","stationTrainCode":"G6","trainClassName":"高速"},{"arriveTime":"22:40","endStationName":"北京南","lishi":"05:26","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"17:14","stationTrainCode":"G154","trainClassName":"高速"},{"arriveTime":"19:55","endStationName":"北京南","lishi":"04:55","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"15:00","stationTrainCode":"G18","trainClassName":"高速"},{"arriveTime":"14:08","endStationName":"北京南","lishi":"06:03","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"08:05","stationTrainCode":"G112","trainClassName":"高速"},{"arriveTime":"21:17","endStationName":"北京南","lishi":"05:17","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"16:00","stationTrainCode":"G20","trainClassName":"高速"},{"arriveTime":"20:18","endStationName":"北京南","lishi":"05:57","priceed":"¥553.0","pricesw":"¥1748.0","pricewz":"¥553.0","priceyd":"¥933.0","startStationName":"上海虹桥","startTime":"14:21","stationTrainCode":"G142","trainClassName":"高速"}]
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
         * arriveTime : 09:30
         * endStationName : 北京
         * lishi : 15:28
         * pricegrw : ¥879.5
         * pricerw : ¥476.5
         * pricewz : ¥177.5
         * priceyw : ¥304.5
         * priceyz : ¥177.5
         * startStationName : 上海
         * startTime : 18:02
         * stationTrainCode : T110
         * trainClassName : 特快
         * priceed : ¥553.0
         * pricesw : ¥1748.0
         * priceyd : ¥933.0
         */

        private String arriveTime;
        private String endStationName;
        private String lishi;
        private String pricegrw;
        private String pricerw;
        private String pricewz;
        private String priceyw;
        private String priceyz;
        private String startStationName;
        private String startTime;
        private String stationTrainCode;
        private String trainClassName;
        private String priceed;
        private String pricesw;
        private String priceyd;

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

        public String getLishi() {
            return lishi;
        }

        public void setLishi(String lishi) {
            this.lishi = lishi;
        }

        public String getPricegrw() {
            return pricegrw;
        }

        public void setPricegrw(String pricegrw) {
            this.pricegrw = pricegrw;
        }

        public String getPricerw() {
            return pricerw;
        }

        public void setPricerw(String pricerw) {
            this.pricerw = pricerw;
        }

        public String getPricewz() {
            return pricewz;
        }

        public void setPricewz(String pricewz) {
            this.pricewz = pricewz;
        }

        public String getPriceyw() {
            return priceyw;
        }

        public void setPriceyw(String priceyw) {
            this.priceyw = priceyw;
        }

        public String getPriceyz() {
            return priceyz;
        }

        public void setPriceyz(String priceyz) {
            this.priceyz = priceyz;
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

        public String getStationTrainCode() {
            return stationTrainCode;
        }

        public void setStationTrainCode(String stationTrainCode) {
            this.stationTrainCode = stationTrainCode;
        }

        public String getTrainClassName() {
            return trainClassName;
        }

        public void setTrainClassName(String trainClassName) {
            this.trainClassName = trainClassName;
        }

        public String getPriceed() {
            return priceed;
        }

        public void setPriceed(String priceed) {
            this.priceed = priceed;
        }

        public String getPricesw() {
            return pricesw;
        }

        public void setPricesw(String pricesw) {
            this.pricesw = pricesw;
        }

        public String getPriceyd() {
            return priceyd;
        }

        public void setPriceyd(String priceyd) {
            this.priceyd = priceyd;
        }
    }
}
