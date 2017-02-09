package xyz.xechoz.demo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by xechoz.zheng on 2/8/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initListener();
    }

    protected void initListener() {

    }

    protected @LayoutRes int getLayoutId() {
        return 0;
    }
}
