package xyz.xechoz.demo;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.EditText;

import org.json.JSONObject;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.xechoz.demo.net.Api;
import xyz.xechoz.demo.net.api.GithubApi;

/**
 * Created by xechoz.zheng on 2/7/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class GitListActivity extends BaseActivity {
    @BindView(R.id.edit_query)
    EditText queryEditText;

    public static void startMe(Context context) {
        context.startActivity(new Intent(context, GitListActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_git_list;
    }

    @Override
    protected void initListener() {
        super.initListener();

        queryEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                onSearch(v.getText().toString().trim());
                return true;
            }

            return false;
        });
    }

    private void onSearch(String query) {
//        Api.retrofit
//                .create(GithubApi.class)
//                .search(query, "star")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(Logger::d);

        Api.retrofit
                .create(GithubApi.class)
                .search(query, "star")
                .enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {

                    }
                });
    }
}
