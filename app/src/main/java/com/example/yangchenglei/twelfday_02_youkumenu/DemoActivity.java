package com.example.yangchenglei.twelfday_02_youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 作者：杨成雷
 * 时间：2018/8/1:16:31
 */
public class DemoActivity extends Activity {
    private YoukuView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        view = (YoukuView) findViewById(R.id.yk);
        view.setOnClickChannel4(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"你好",Toast.LENGTH_LONG).show();
            }
        });

    }
}
