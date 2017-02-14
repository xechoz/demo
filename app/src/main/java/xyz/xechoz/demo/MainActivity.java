package xyz.xechoz.demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.OnClick;
import xyz.xechoz.demo.animate.SlideLayout;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.slide_layout)
    void slideView(View view) {
        SlideLayout slideLayout = (SlideLayout) view;
        TextView textView = new TextView(MainActivity.this);
        int red = textView.hashCode() % 0xff;
        int green = red * red % 0xff;
        int blue = green * green % 0xff;

        textView.setBackgroundColor(Color.rgb(red, green, blue));
        textView.setText("text: " + textView);
        textView.setGravity(Gravity.CENTER);
        textView.setWidth(slideLayout.getMeasuredWidth());
        textView.setHeight(slideLayout.getMeasuredHeight());
        SlideLayout.AnimateType type = SlideLayout.AnimateType.AnimateXml;
        slideLayout.addSlide(type, textView);
    }

    Handler handler = new Handler();
    Handler handler2;

    @OnClick(R.id.button)
    void onButtonClick() {
        GitListActivity.startMe(this);
    }


    @OnClick(R.id.button2)
    void onNewThread() {
        DemoThread demoThread = new DemoThread(new Runnable() {

            @Override
            public void run() {
                Logger.d("DemoThread run, " + System.nanoTime());
            }
        });

        demoThread.start();
    }

    private static class DemoThread extends Thread {
        private Looper looper;

        public DemoThread(Runnable target) {
            super(target, "demo");
        }

        public Looper getLooper() {
            return looper;
        }

        @Override
        public void run() {
            Logger.d("DemoThread un begin");
            Looper.prepare();

            synchronized (this) {
                looper = Looper.myLooper();
            }
            super.run();

            Looper.loop();

            Logger.d("DemoThread run end");
        }
    }
}
