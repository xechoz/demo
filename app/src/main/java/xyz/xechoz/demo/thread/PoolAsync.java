package xyz.xechoz.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xechoz.zheng on 2/16/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class PoolAsync implements IAsync {
    private ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void post(Runnable runnable) {
        service.execute(runnable);
    }
}
