package xyz.xechoz.demo.thread.aidl;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by xechoz.zheng on 2/14/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class GitsService extends IntentService {
    private IGits.Stub binder = new IGits.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void search(String query, String sortBy) throws RemoteException {

        }
    };

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GitsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
