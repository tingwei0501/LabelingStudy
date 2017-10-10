package edu.nctu.minuku_2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import edu.nctu.minuku.config.Constants;
import edu.nctu.minuku.event.DecrementLoadingProcessCountEvent;
import edu.nctu.minuku.event.IncrementLoadingProcessCountEvent;
import edu.nctu.minuku.logger.Log;
import edu.nctu.minuku_2.Constant;
import edu.nctu.minuku_2.MainActivity;
import edu.nctu.minuku_2.R;
import edu.nctu.minuku_2.controller.Counter;
import edu.nctu.minuku_2.controller.Timeline;
import edu.nctu.minuku_2.controller.home;
import edu.nctu.minuku_2.controller.report;
import edu.nctu.minuku_2.service.BackgroundService;

import static edu.nctu.minuku_2.MainActivity.mTabs;
import static edu.nctu.minuku_2.MainActivity.mViewPager;

//import edu.ohio.minuku_2.R;

public class CounterActivity extends AppCompatActivity {

    private android.support.design.widget.TabLayout mTabs;
    private ViewPager mViewPager;
    ArrayList viewList;
    public static View timerview,recordview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        timerview = mInflater.inflate(R.layout.counter, null);
        recordview = mInflater.inflate(R.layout.activity_timeline, null);

        initViewPager(timerview,recordview);

        SettingViewPager();
    }

    //public for update
    public void initViewPager(View timerview, View recordview){
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tablayout);
        mTabs.addTab(mTabs.newTab().setText("計時"));
        mTabs.addTab(mTabs.newTab().setText("紀錄"));

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        timerview.setTag(Constant.home_tag);


    }

    public void SettingViewPager() {
        viewList = new ArrayList<View>();
        viewList.add(timerview);
        viewList.add(recordview);

        mViewPager.setAdapter(new CounterActivity.TimerOrRecordPagerAdapter(viewList, this));
        mTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        //TODO date button now can show on menu when switch to recordview, but need to determine where to place the date textview(default is today's date).
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(!Constant.tabpos)
                    //show date on menu
                    Constant.tabpos = true;
                else
                    //hide date on menu
                    Constant.tabpos = false;
                invalidateOptionsMenu();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(MainActivity.mViewPager));
    }

    class TimerOrRecordPagerAdapter extends PagerAdapter {
        private List<View> mListViews;
        private Context mContext;

        public TimerOrRecordPagerAdapter(){};

        public TimerOrRecordPagerAdapter(List<View> mListViews,Context mContext) {
            this.mListViews = mListViews;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListViews.get(position);
            switch (position){
                case 0: //timer
                    Counter mcounter = new Counter(mContext);
                    mcounter.initCounter(timerview);
//                    Counter mcounter = new Counter(mContext);
//                    mcounter.initCounter(timerview);
//                    view = getLayoutInflater().inflate(R.layout.counter,
//                            container, false);
//                    container.addView(view);
                    break;
                case 1: //report
                    Timeline mtimeline = new Timeline(mContext);
                    mtimeline.initTime(recordview);
//                    view = getLayoutInflater().inflate(R.layout.activity_timeline,
//                            container, false);
                    break;
            }
            container.addView(view);

            //title.setText(String.valueOf(position + 1));
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }







}
