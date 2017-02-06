package xyz.xechoz.demo.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xechoz.zheng on 2/6/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class Api {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dev.xechoz.xyz/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
