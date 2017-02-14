package xyz.xechoz.demo.net.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xechoz.zheng on 2/7/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class GithubRepository implements Parcelable {
    public int id;
    public String name;

    @SerializedName(value = "html_url", alternate = {"htmlUrl"})
    public String htmlUrl;
    public String description;
    public String language;

    @SerializedName(value = "homepage", alternate = {"home_page", "homePage"})
    public String homePage;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeString(this.language);
        dest.writeString(this.homePage);
    }

    public GithubRepository() {
    }

    protected GithubRepository(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.language = in.readString();
        this.homePage = in.readString();
    }

    public static final Creator<GithubRepository> CREATOR = new Creator<GithubRepository>() {
        @Override
        public GithubRepository createFromParcel(Parcel source) {
            return new GithubRepository(source);
        }

        @Override
        public GithubRepository[] newArray(int size) {
            return new GithubRepository[size];
        }
    };
}
