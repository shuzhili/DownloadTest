package com.example.download.myapplication.DBManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greenrobot.greendao.DaoMaster;
import com.greenrobot.greendao.HLSOfflineDao;

import org.greenrobot.greendao.database.Database;

public class MyDaoMasterOpenHelper extends DaoMaster.OpenHelper {
    public MyDaoMasterOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDaoMasterOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            HLSOfflineDao.createTable(db, true);
        }
    }
}
