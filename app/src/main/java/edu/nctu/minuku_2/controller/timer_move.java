package edu.nctu.minuku_2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import edu.nctu.minuku_2.MainActivity;
import edu.nctu.minuku_2.R;

import static edu.nctu.minuku_2.controller.home.counter;
import static edu.nctu.minuku_2.controller.home.move;
import static edu.nctu.minuku_2.controller.home.pause;
import static edu.nctu.minuku_2.controller.home.play;
import static edu.nctu.minuku_2.controller.home.site;
import static edu.nctu.minuku_2.controller.home.stop;
import static edu.nctu.minuku_2.controller.home.traffic;


//import edu.ohio.minuku_2.R;

/**
 * Created by Lawrence on 2017/4/22.
 */

public class timer_move extends AppCompatActivity {

    final private String LOG_TAG = "timer_move";

    Button walk,bike,car;
    private Button site2;
    public static String TrafficFlag;

    public timer_move(){}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_move);

        inittimer_move();
    }

    public void inittimer_move(){

        walk = (Button) findViewById(R.id.walk);
        bike = (Button) findViewById(R.id.bike);
        car = (Button) findViewById(R.id.car);
        traffic = (ImageView)findViewById(R.id.iv_traffic);

        site2 = (Button) findViewById(R.id.site);
        walk.setOnClickListener(walkingTime);
        bike.setOnClickListener(bikingTime);
        car.setOnClickListener(carTime);

        site2.setOnClickListener(siting);


    }

    private Button.OnClickListener bikingTime = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            TrafficFlag="bike";

            startActivity(new Intent(timer_move.this, MainActivity.class));
//            traffic.setImageResource(R.drawable.bike);
            timer_move.this.finish();
        }
    };

    private Button.OnClickListener carTime = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            TrafficFlag="car";

            startActivity(new Intent(timer_move.this, MainActivity.class));
//            traffic.setImageResource(R.drawable.car);
            timer_move.this.finish();
        }
    };

    private Button.OnClickListener walkingTime = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            TrafficFlag="walk";

            startActivity(new Intent(timer_move.this, MainActivity.class));
//            traffic.setImageResource(R.drawable.walk);
            timer_move.this.finish();
        }
    };

    //to view timer_site
    private Button.OnClickListener siting = new Button.OnClickListener() {
        public void onClick(View v) {
            Log.e(LOG_TAG,"site clicked");

            //TODO this function will increase the screen in stack, need to be optimized.
            startActivity(new Intent(timer_move.this, timer_site.class));

        }
    };
}
