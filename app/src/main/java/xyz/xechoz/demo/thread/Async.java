package xyz.xechoz.demo.thread;

/**
 * Created by xechoz.zheng on 2/16/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class Async {
    private HandlerAsync handlerAsync;
    private PoolAsync poolAsync;

    private static class Holder {
        private static final Async INSTANCE = new Async();
    }

    private Async() {
        handlerAsync = new HandlerAsync();
        poolAsync = new PoolAsync();
    }

    public static Async getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 异步执行任务。在后台立即执行，不需要等待上一个任务完成
     * @param runnable
     */
    public void post(Runnable runnable) {
        poolAsync.post(runnable);
    }

    /**
     * 异步执行任务，但是在后台的子线程内部是同步执行的，子线程需要等待上一个任务完成
     * 适合用在需要在后台同步处理任务的情形，比如 按时间顺序重新发送失败的消息
     * @param runnable
     */
    public void postSync(Runnable runnable) {
        handlerAsync.post(runnable);
    }
}
