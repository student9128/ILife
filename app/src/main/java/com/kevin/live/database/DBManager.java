package com.kevin.live.database;

import com.kevin.live.base.BaseApplication;

import java.util.List;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class DBManager {
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private TrainHistoryEntityDao mTrainHistoryEntityDao;
    private TrainNoHistoryEntityDao mTrainNoHistoryEntityDao;

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                mInstance = new DBManager();
            }
        }
        return mInstance;
    }

    public DBManager() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), TableConfig
                .DB_NAME_HISTORY_TRAIN);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mTrainHistoryEntityDao = mDaoSession.getTrainHistoryEntityDao();
        mTrainNoHistoryEntityDao = mDaoSession.getTrainNoHistoryEntityDao();
    }

    /**
     * insert history.
     * @param startStation
     * @param endStation
     * @param time
     */
    public void insertHistory(String startStation, String endStation, String time) {
        TrainHistoryEntity trainHistoryEntity = new TrainHistoryEntity(null, startStation, endStation, time);
        mTrainHistoryEntityDao.insert(trainHistoryEntity);
    }

    /**
     * delete one history.
     * @param startStation
     * @param endStation
     * @param time
     */
    public void deleteHistory(String startStation, String endStation, String time) {
        String sql="delete from "+TrainHistoryEntityDao.TABLENAME+" where START_STATION='"+startStation
                +"' AND END_STATION='"+endStation+"' AND TIME='"+time+"'";
        mDaoSession.getDatabase().execSQL(sql);
    }

    /**
     * clear all history.
     */
    public void clearHistory(){
        mTrainHistoryEntityDao.deleteAll();
    }

    /**
     * retrieve all history.
     * @return
     */
    public List<TrainHistoryEntity> retrieveAllHistory() {
        List<TrainHistoryEntity> list = mTrainHistoryEntityDao.queryBuilder().build().list();
        return list;
    }

    /**
     * insert history about querying train number info.
     * @param trainNo
     * @param time
     */
    public void insertNoHistory(String trainNo, String time) {
        TrainNoHistoryEntity trainNoHistoryEntity = new TrainNoHistoryEntity(null, trainNo, time);
        mTrainNoHistoryEntityDao.insert(trainNoHistoryEntity);
    }

    public void clearNoHistory(){
        mTrainNoHistoryEntityDao.deleteAll();
    }

    public List<TrainNoHistoryEntity> retrieveAllNoHistory() {
        List<TrainNoHistoryEntity> list = mTrainNoHistoryEntityDao.queryBuilder().build().list();
        return  list;
    }


}
