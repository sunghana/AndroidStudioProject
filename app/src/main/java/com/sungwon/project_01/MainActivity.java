package com.sungwon.project_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Runnable runnable;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intObj = new Intent(MainActivity.this,searchActivity.class);
                startActivity(intObj);
                finish();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable,1300);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
