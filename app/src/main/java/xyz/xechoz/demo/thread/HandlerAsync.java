package xyz.xechoz.demo.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by xechoz.zheng on 2/16/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class HandlerAsync implements IAsync {
    private static final String THREAD_NAME = HandlerAsync.class.getName();
    private final HandlerThread thread;
    private Handler handler;

    public HandlerAsync() {
        thread = new HandlerThread(THREAD_NAME);
        thread.start();

        handler = new Handler(thread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                return false;
            }
        });
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
