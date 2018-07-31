package com.greenrobot.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.download.myapplication.HLSOffline;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "offline_tbl".
*/
public class HLSOfflineDao extends AbstractDao<HLSOffline, String> {

    public static final String TABLENAME = "offline_tbl";

    /**
     * Properties of entity HLSOffline.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property VodId = new Property(0, String.class, "vodId", true, "vodId");
        public final static Property SetId = new Property(1, String.class, "setId", false, "setId");
        public final static Property SetTitle = new Property(2, String.class, "setTitle", false, "setTitle");
        public final static Property SetCover = new Property(3, String.class, "setCover", false, "setId");
        public final static Property Title = new Property(4, String.class, "title", false, "title");
        public final static Property Pic = new Property(5, String.class, "pic", false, "pic");
        public final static Property Url = new Property(6, String.class, "url", false, "url");
        public final static Property Url_md5 = new Property(7, String.class, "url_md5", false, "url_md5");
        public final static Property Fold = new Property(8, String.class, "fold", false, "fold");
        public final static Property File = new Property(9, String.class, "file", false, "file");
        public final static Property BandWidth = new Property(10, int.class, "bandWidth", false, "bandWidth");
        public final static Property BandW = new Property(11, float.class, "bandW", false, "bandW");
        public final static Property TotalDuration = new Property(12, int.class, "totalDuration", false, "totalDuration");
        public final static Property PlayedDuration = new Property(13, int.class, "playedDuration", false, "playedDuration");
        public final static Property DownloadDuration = new Property(14, int.class, "downloadDuration", false, "downloadDuration");
        public final static Property TotalSize = new Property(15, int.class, "totalSize", false, "totalSize");
        public final static Property DownloadSize = new Property(16, int.class, "downloadSize", false, "downloadSize");
        public final static Property Status = new Property(17, int.class, "status", false, "status");
        public final static Property Progress = new Property(18, int.class, "progress", false, "progress");
        public final static Property TsNum = new Property(19, int.class, "tsNum", false, "tsNum");
        public final static Property Tsdl = new Property(20, int.class, "tsdl", false, "tsdl");
        public final static Property IsChecked = new Property(21, boolean.class, "isChecked", false, "isChecked");
    }


    public HLSOfflineDao(DaoConfig config) {
        super(config);
    }
    
    public HLSOfflineDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"offline_tbl\" (" + //
                "\"vodId\" TEXT PRIMARY KEY NOT NULL ," + // 0: vodId
                "\"setId\" TEXT," + // 1: setId
                "\"setTitle\" TEXT," + // 2: setTitle
                "\"setId\" TEXT," + // 3: setCover
                "\"title\" TEXT," + // 4: title
                "\"pic\" TEXT," + // 5: pic
                "\"url\" TEXT," + // 6: url
                "\"url_md5\" TEXT," + // 7: url_md5
                "\"fold\" TEXT," + // 8: fold
                "\"file\" TEXT," + // 9: file
                "\"bandWidth\" INTEGER NOT NULL ," + // 10: bandWidth
                "\"bandW\" REAL NOT NULL ," + // 11: bandW
                "\"totalDuration\" INTEGER NOT NULL ," + // 12: totalDuration
                "\"playedDuration\" INTEGER NOT NULL ," + // 13: playedDuration
                "\"downloadDuration\" INTEGER NOT NULL ," + // 14: downloadDuration
                "\"totalSize\" INTEGER NOT NULL ," + // 15: totalSize
                "\"downloadSize\" INTEGER NOT NULL ," + // 16: downloadSize
                "\"status\" INTEGER NOT NULL ," + // 17: status
                "\"progress\" INTEGER NOT NULL ," + // 18: progress
                "\"tsNum\" INTEGER NOT NULL ," + // 19: tsNum
                "\"tsdl\" INTEGER NOT NULL ," + // 20: tsdl
                "\"isChecked\" INTEGER NOT NULL );"); // 21: isChecked
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"offline_tbl\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HLSOffline entity) {
        stmt.clearBindings();
 
        String vodId = entity.getVodId();
        if (vodId != null) {
            stmt.bindString(1, vodId);
        }
 
        String setId = entity.getSetId();
        if (setId != null) {
            stmt.bindString(2, setId);
        }
 
        String setTitle = entity.getSetTitle();
        if (setTitle != null) {
            stmt.bindString(3, setTitle);
        }
 
        String setCover = entity.getSetCover();
        if (setCover != null) {
            stmt.bindString(4, setCover);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String pic = entity.getPic();
        if (pic != null) {
            stmt.bindString(6, pic);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
 
        String url_md5 = entity.getUrl_md5();
        if (url_md5 != null) {
            stmt.bindString(8, url_md5);
        }
 
        String fold = entity.getFold();
        if (fold != null) {
            stmt.bindString(9, fold);
        }
 
        String file = entity.getFile();
        if (file != null) {
            stmt.bindString(10, file);
        }
        stmt.bindLong(11, entity.getBandWidth());
        stmt.bindDouble(12, entity.getBandW());
        stmt.bindLong(13, entity.getTotalDuration());
        stmt.bindLong(14, entity.getPlayedDuration());
        stmt.bindLong(15, entity.getDownloadDuration());
        stmt.bindLong(16, entity.getTotalSize());
        stmt.bindLong(17, entity.getDownloadSize());
        stmt.bindLong(18, entity.getStatus());
        stmt.bindLong(19, entity.getProgress());
        stmt.bindLong(20, entity.getTsNum());
        stmt.bindLong(21, entity.getTsdl());
        stmt.bindLong(22, entity.getIsChecked() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HLSOffline entity) {
        stmt.clearBindings();
 
        String vodId = entity.getVodId();
        if (vodId != null) {
            stmt.bindString(1, vodId);
        }
 
        String setId = entity.getSetId();
        if (setId != null) {
            stmt.bindString(2, setId);
        }
 
        String setTitle = entity.getSetTitle();
        if (setTitle != null) {
            stmt.bindString(3, setTitle);
        }
 
        String setCover = entity.getSetCover();
        if (setCover != null) {
            stmt.bindString(4, setCover);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String pic = entity.getPic();
        if (pic != null) {
            stmt.bindString(6, pic);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
 
        String url_md5 = entity.getUrl_md5();
        if (url_md5 != null) {
            stmt.bindString(8, url_md5);
        }
 
        String fold = entity.getFold();
        if (fold != null) {
            stmt.bindString(9, fold);
        }
 
        String file = entity.getFile();
        if (file != null) {
            stmt.bindString(10, file);
        }
        stmt.bindLong(11, entity.getBandWidth());
        stmt.bindDouble(12, entity.getBandW());
        stmt.bindLong(13, entity.getTotalDuration());
        stmt.bindLong(14, entity.getPlayedDuration());
        stmt.bindLong(15, entity.getDownloadDuration());
        stmt.bindLong(16, entity.getTotalSize());
        stmt.bindLong(17, entity.getDownloadSize());
        stmt.bindLong(18, entity.getStatus());
        stmt.bindLong(19, entity.getProgress());
        stmt.bindLong(20, entity.getTsNum());
        stmt.bindLong(21, entity.getTsdl());
        stmt.bindLong(22, entity.getIsChecked() ? 1L: 0L);
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public HLSOffline readEntity(Cursor cursor, int offset) {
        HLSOffline entity = new HLSOffline( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // vodId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // setId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // setTitle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // setCover
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // pic
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // url
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // url_md5
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // fold
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // file
            cursor.getInt(offset + 10), // bandWidth
            cursor.getFloat(offset + 11), // bandW
            cursor.getInt(offset + 12), // totalDuration
            cursor.getInt(offset + 13), // playedDuration
            cursor.getInt(offset + 14), // downloadDuration
            cursor.getInt(offset + 15), // totalSize
            cursor.getInt(offset + 16), // downloadSize
            cursor.getInt(offset + 17), // status
            cursor.getInt(offset + 18), // progress
            cursor.getInt(offset + 19), // tsNum
            cursor.getInt(offset + 20), // tsdl
            cursor.getShort(offset + 21) != 0 // isChecked
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HLSOffline entity, int offset) {
        entity.setVodId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSetId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSetTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSetCover(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPic(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUrl_md5(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFold(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFile(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBandWidth(cursor.getInt(offset + 10));
        entity.setBandW(cursor.getFloat(offset + 11));
        entity.setTotalDuration(cursor.getInt(offset + 12));
        entity.setPlayedDuration(cursor.getInt(offset + 13));
        entity.setDownloadDuration(cursor.getInt(offset + 14));
        entity.setTotalSize(cursor.getInt(offset + 15));
        entity.setDownloadSize(cursor.getInt(offset + 16));
        entity.setStatus(cursor.getInt(offset + 17));
        entity.setProgress(cursor.getInt(offset + 18));
        entity.setTsNum(cursor.getInt(offset + 19));
        entity.setTsdl(cursor.getInt(offset + 20));
        entity.setIsChecked(cursor.getShort(offset + 21) != 0);
     }
    
    @Override
    protected final String updateKeyAfterInsert(HLSOffline entity, long rowId) {
        return entity.getVodId();
    }
    
    @Override
    public String getKey(HLSOffline entity) {
        if(entity != null) {
            return entity.getVodId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HLSOffline entity) {
        return entity.getVodId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
