package com.kevin.live.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>query history about train info.
 */
@Entity
public class TrainHistoryEntity {
    @Id
    private Long id;
    private String startStation;
    private String endStation;
    private String time;
    @Generated(hash = 609155866)
    public TrainHistoryEntity(Long id, String startStation, String endStation,
            String time) {
        this.id = id;
        this.startStation = startStation;
        this.endStation = endStation;
        this.time = time;
    }
    @Generated(hash = 407110247)
    public TrainHistoryEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStartStation() {
        return this.startStation;
    }
    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }
    public String getEndStation() {
        return this.endStation;
    }
    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
