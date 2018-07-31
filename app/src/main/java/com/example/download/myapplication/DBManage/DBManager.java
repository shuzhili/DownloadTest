package com.example.download.myapplication.DBManage;

import android.content.Context;

import com.greenrobot.greendao.DaoMaster;
import com.greenrobot.greendao.DaoSession;
import com.greenrobot.greendao.HLSOfflineDao;

import org.greenrobot.greendao.database.Database;

public class DBManager {
    private static volatile DaoSession mDaoSession;

    public static void initGreenDao(Context context) {
        if (context == null) {
            return;
        }
        MyDaoMasterOpenHelper helper = new MyDaoMasterOpenHelper(context, "download_test");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getmDaoSession(Context context) {
        if (mDaoSession == null) {
            synchronized (DBManager.class) {
                initGreenDao(context);
            }

        }
        return mDaoSession;
    }

    public static HLSOfflineDao getHlsOfflineDao(Context context) {
        DaoSession daoSession = getmDaoSession(context);
        return daoSession == null ? null : daoSession.getHLSOfflineDao();
    }
}
