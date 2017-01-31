package com.xjtu.kangy.WereWolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by kangy on 2017/1/31.
 */

public class WelcomeActivity extends Activity {
    private static int TIME=2000;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转

            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, SetupActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME);
    }
}
