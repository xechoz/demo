package xyz.xechoz.demo.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import xyz.xechoz.demo.BaseActivity;
import xyz.xechoz.demo.util.DataUtil;

/**
 * Created by xechoz.zheng on 2/21/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class AppLinksActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return super.getLayoutId();
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        super.onInit(savedInstanceState);

        Logger.d("params: " + getIntent().toString());

        Uri data = getIntent().getData();

        Logger.d("data: " + DataUtil.GSON.toJson(data));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Logger.d("" + intent.toString());
    }
}
