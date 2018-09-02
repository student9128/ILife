package com.kevin.live.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>query history about train info.
 */
@Entity
public class TrainNoHistoryEntity {
    @Id
    private Long id;
    private String trainNo;
    private String time;
    @Generated(hash = 208633608)
    public TrainNoHistoryEntity(Long id, String trainNo, String time) {
        this.id = id;
        this.trainNo = trainNo;
        this.time = time;
    }
    @Generated(hash = 24639650)
    public TrainNoHistoryEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTrainNo() {
        return this.trainNo;
    }
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }


}
