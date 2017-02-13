package xyz.xechoz.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import xyz.xechoz.demo.animate.SlideLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        slideView();
    }

    private  void slideView() {
        final SlideLayout slideLayout = (SlideLayout) findViewById(R.id.slide_layout);

        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(MainActivity.this);
                int red = textView.hashCode() % 0xff;
                int green = red*red % 0xff;
                int blue = green*green % 0xff;

                textView.setBackgroundColor(Color.rgb(red, green, blue));
                textView.setText("text: " + textView);
                textView.setGravity(Gravity.CENTER);
                textView.setWidth(slideLayout.getMeasuredWidth());
                textView.setHeight(slideLayout.getMeasuredHeight());
                SlideLayout.AnimateType type = SlideLayout.AnimateType.AnimateXml;
                slideLayout.addSlide(type, textView);
            }
        });
    }

    private void testThread() {
        Log.d("thread", "id: " + Thread.currentThread().getId() + ", name: "  + Thread.currentThread().getName());

        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("thread", "id: " + Thread.currentThread().getId() + ", name: "  + Thread.currentThread().getName());

                        try {
                            button.setText("id: " + Thread.currentThread().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500, 500, TimeUnit.MILLISECONDS);
    }
}
