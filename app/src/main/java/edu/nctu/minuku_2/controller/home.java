package edu.nctu.minuku_2.controller;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import edu.nctu.minuku_2.R;
import static edu.nctu.minuku_2.MainActivity.recordview;
import static edu.nctu.minuku_2.MainActivity.task;
import static edu.nctu.minuku_2.MainActivity.timerview;

//import edu.ohio.minuku_2.R;

/**
 * Created by Lawrence on 2017/4/20.
 */

public class home extends AppCompatActivity {

    final private String LOG_TAG = "home";

    private Context mContext;
    private LayoutInflater mInflater;

    public static TextView counter;
    public static String result;
    public static String stoptime, starttime;
    private int tsec=0,csec=0,cmin=0,chour=0;
    public static boolean startflag=false;
    public static boolean recordflag=false;
    String TAG="Counter";
    public static SimpleDateFormat formatter;




    public static Button move,site;
    public static ImageButton  play, pause, stop;
    public static ImageView traffic;

    public home(){}

    public home(Context mContext){
        this.mContext = mContext;
    }

    public home(LayoutInflater mInflater){
        this.mInflater = mInflater;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


    }

    public void inithome(View v){
        counter = (TextView) v.findViewById(R.id.tv_timer);
        counter.setVisibility(View.INVISIBLE);
        play = (ImageButton) v.findViewById(R.id.btn_play);
        play.setVisibility(View.INVISIBLE);
        pause = (ImageButton) v.findViewById(R.id.btn_pause);
        pause.setVisibility(View.INVISIBLE);
        stop = (ImageButton) v.findViewById(R.id.btn_stop);
        stop.setVisibility(View.INVISIBLE);
        move = (Button) v.findViewById(R.id.move);
        site = (Button) v.findViewById(R.id.site);
        traffic = (ImageView)v.findViewById(R.id.iv_traffic);
        traffic.setVisibility(View.INVISIBLE);

        move.setOnClickListener(moving);
        site.setOnClickListener(siting);

        //Button監聽
        play.setOnClickListener(listener);
        pause.setOnClickListener(listener);
        stop.setOnClickListener(listener);

        //宣告Timer
        Timer timer01 =new Timer();

        //設定Timer(task為執行內容，0代表立刻開始,間格1秒執行一次)
        timer01.schedule(task, 0,1000);
        formatter = new SimpleDateFormat("HH:mm:ss");


//        Date mDate = new Date() ; // 獲取當前時間
//        time = mDate.getTime();

    }

    private Button.OnClickListener editinguractivity = new Button.OnClickListener() {
        public void onClick(View v) {
            Log.e(LOG_TAG,"edituractivity clicked");


        }
    };
    //to view timer_move
    private Button.OnClickListener moving = new Button.OnClickListener() {
        public void onClick(View v) {
            Log.e(LOG_TAG,"move clicked");
            mContext.startActivity(new Intent(mContext, timer_move.class));

        }
    };
    //to view timer_site
    private Button.OnClickListener siting = new Button.OnClickListener() {
        public void onClick(View v) {
            Log.e(LOG_TAG,"site clicked");

            mContext.startActivity(new Intent(mContext, timer_site.class));

        }
    };

    //TimerTask無法直接改變元件因此要透過Handler來當橋樑
    private Handler handler = new Handler(){
        public  void  handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    csec=tsec%60;
                    cmin=tsec/60;
                    chour=tsec/3600;
                    String s="";
                    if(chour <10){
                        s="0"+chour;
                    }else{
                        s=""+chour;
                    }
                    if(cmin <10){
                        s=s+":0"+cmin;
                    }else{
                        s=s+":"+cmin;
                    }
                    if(csec < 10){
                        s=s+":0"+csec;
                    }else{
                        s=s+":"+csec;
                    }

                    //s字串為00:00:00格式
                    counter.setText(s);
                    break;
            }

        }
    };

    private TimerTask task = new TimerTask(){

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (startflag){
                //如果startflag是true則每秒tsec+1
                tsec++;
                Message message = new Message();

                //傳送訊息1
                message.what =1;
                handler.sendMessage(message);
            }
        }

    };

    private View.OnClickListener listener =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch(v.getId()){
                case R.id.btn_play:
                    startflag=true;
                    recordflag=false;
                    Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
                    starttime = formatter.format(curDate);

                    break;
                case R.id.btn_pause:
                    startflag=false;
                    break;
                case R.id.btn_stop:
                    tsec=0;
                    startflag=false;
                    recordflag=true;
                    Date curDate2 = new Date(System.currentTimeMillis()) ; // 獲取當前時間
                    stoptime = formatter.format(curDate2);
                    result = starttime + " - " + stoptime + "\nduration: " + counter.getText().toString();
                    edu.nctu.minuku.logger.Log.d(TAG, "recordflag: " + recordflag + ", counter:" + result);

                    Timeline mtimeline = new Timeline(mContext);
                    mtimeline.initTime(recordview);
                    counter.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.INVISIBLE);
                    pause.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.INVISIBLE);
                    traffic.setVisibility(View.INVISIBLE);
                    move.setVisibility(View.VISIBLE);
                    site.setVisibility(View.VISIBLE);
                    //TextView 初始化
                    counter.setText("00:00:00");

                    break;
            }
        }

    };
}
