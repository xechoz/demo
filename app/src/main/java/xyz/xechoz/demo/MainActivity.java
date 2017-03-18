package xyz.xechoz.demo;

import com.google.protobuf.GeneratedMessage;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;
import xyz.xechoz.demo.model.entity.Message;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.button)
    void onClick() {
        Message.Hello hello = Message.Hello.newBuilder()
                .setContent("cot")
                .setId(System.nanoTime())
                .build();

        Logger.d("hello" + hello);

        hello.toByteArray();
    }



    private byte[] toBytyArray(GeneratedMessage message) {
        return message.toByteArray();
    }
}
