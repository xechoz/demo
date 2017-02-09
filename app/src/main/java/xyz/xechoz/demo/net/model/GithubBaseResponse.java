package xyz.xechoz.demo.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xechoz.zheng on 2/8/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class GithubBaseResponse <T>{
    @SerializedName(value = "total_count", alternate = {"totalCount"})
    public int totalCount;

    @SerializedName("incomplete_results")
    public boolean incompleteResults;

    public List<T> items;
}
