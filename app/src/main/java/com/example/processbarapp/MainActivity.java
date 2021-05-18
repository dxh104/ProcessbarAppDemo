package com.example.processbarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomeProcessBar customeProcessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        customeProcessBar.setmProcessValue(0);
        customeProcessBar.setmProcessMaxValue(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=1000 ; i++) {
                    try {
                        Thread.sleep(1);
                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customeProcessBar.setmProcessValue(finalI);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        customeProcessBar = (CustomeProcessBar) findViewById(R.id.customeProcessBar);
    }
}
