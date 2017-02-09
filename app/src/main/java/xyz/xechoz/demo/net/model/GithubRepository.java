package xyz.xechoz.demo.net.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xechoz.zheng on 2/7/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class GithubRepository {
    public int id;
    public String name;

    @SerializedName(value = "html_url", alternate = {"htmlUrl"})
    public String htmlUrl;
    public String description;
    public String language;

    @SerializedName(value = "homepage", alternate = {"home_page", "homePage"})
    public String homePage;
}
