package xyz.xechoz.demo.net.api;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xechoz.zheng on 2/6/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public interface GithubApi {
    @GET("search/repositories")
    Call<JSONObject> search(@Query("q") String query, @Query("sort") String sort);
}
