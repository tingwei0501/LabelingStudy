package edu.nctu.minuku_2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by Jimmy on 2017/10/17.
 */

public class TimeService extends Service {

    private Handler handler = new Handler();
    private long initial_time;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initial_time = SystemClock.uptimeMillis();

        handler.removeCallbacks(showTime);
        handler.postDelayed(showTime, 1000); // 1 second
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(showTime);
        super.onDestroy();
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            //log目前時間
            Log.i("time:", new Date().toString());
            handler.postDelayed(this, 1000); //1 second
        }
    };
}
