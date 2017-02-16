package xyz.xechoz.demo.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by xechoz.zheng on 2/16/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class DbApi {
    private static String DB_NAME = "demo.db";
    private DaoSession daoSession;

    private static class Holder {
        private final static DbApi INSTANCE = new DbApi();
    }

    private DbApi() {
    }

    public static DbApi getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getReadableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
