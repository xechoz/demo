package xyz.xechoz.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import xyz.xechoz.demo.animate.SlideLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
