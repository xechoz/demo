package xyz.xechoz.demo;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

import xyz.xechoz.demo.database.DbApi;
import xyz.xechoz.demo.thread.Async;

/**
 * Created by xechoz.zheng on 2/7/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class MainApplication extends Application {
    private static final String YOUR_TAG = "demo";
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        context = this;

        Async.getInstance().post(new Runnable() {
            @Override
            public void run() {
                Logger.init(YOUR_TAG);
                DbApi.getInstance().init(MainApplication.this);
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
