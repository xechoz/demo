package xyz.xechoz.demo.net;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.xechoz.demo.MainApplication;

/**
 * Created by xechoz.zheng on 2/6/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class Api {
    private static final File CACHE_DIR = new File(MainApplication.getContext()
            .getCacheDir(), "api");

    private static final long MAX_CACHE_SIZE = 32 * 1024 * 1024;

    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            .cache(new Cache(CACHE_DIR, MAX_CACHE_SIZE));

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    public static OkHttpClient okHttpClient = okHttpBuilder.build();
    public static Retrofit retrofit = retrofitBuilder.client(okHttpClient)
            .build();

    static {
        
    }
}
