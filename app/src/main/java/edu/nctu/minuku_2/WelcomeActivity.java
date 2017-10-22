package edu.nctu.minuku_2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.nctu.minuku_2.controller.timer_move;

//import edu.ohio.minuku_2.R;

public class WelcomeActivity extends AppCompatActivity {

    private static long sleeptime = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, timer_move.class);
                startActivity(intent);
                finish();
            }
        },sleeptime);
    }
}
