package edu.nctu.minuku_2.controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import edu.nctu.minuku.logger.Log;
import edu.nctu.minuku_2.R;


import static edu.nctu.minuku_2.controller.home.recordflag;
import static edu.nctu.minuku_2.controller.home.result;

//import edu.ohio.minuku_2.R;

public class Timeline extends AppCompatActivity {

    public static ArrayList<String> myTimeDataset = new ArrayList<>();
    public static ArrayList<String> myActivityDataset = new ArrayList<>();
    String TAG="Timeline";
    Context mContext;

    public Timeline(){}
    public Timeline(Context mContext){
        this.mContext = mContext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

//        setDataListItems();
//        MyAdapter myAdapter = new MyAdapter(myTimeDataset, myActivityDataset);
//        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
//        //initialize RecyclerView
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mList.setLayoutManager(layoutManager);
//        mList.setAdapter(myAdapter);
    }

    public void initTime(View v){

        setDataListItems();
        MyAdapter myAdapter = new MyAdapter(myTimeDataset, myActivityDataset);
        RecyclerView mList = (RecyclerView) v.findViewById(R.id.list_view);
        //initialize RecyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
    }



    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mTime, mActivity;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView time, activity;
            public ViewHolder(View v) {
                super(v);
                time = (TextView) v.findViewById(R.id.tv_time);
                activity = (TextView) v.findViewById(R.id.tv_activity);
            }
        }

        public MyAdapter(List<String> timedata, List<String> activitydata) {
            mTime = timedata;
            mActivity = activitydata;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_card_view, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.time.setText(mTime.get(position));
            holder.activity.setText(mActivity.get(position));

        }

        @Override
        public int getItemCount() {
            return mTime.size();
        }
    }



    private void setDataListItems(){

        Log.d(TAG, "recordflag: " + recordflag);
        if(recordflag){
            myTimeDataset.add(result);
            myActivityDataset.add("Order placed successfully");
            recordflag=false;
        }

//        myTimeDataset.add("2017-02-10 14:30");
//        myActivityDataset.add("Order confirmed by seller");
//        myTimeDataset.add("2017-02-10 15:00");
//        myActivityDataset.add("Order processing initiated");
//        myTimeDataset.add("2017-02-11 08:00");
//        myActivityDataset.add("Order is being readied for dispatch");
//        myTimeDataset.add("2017-02-11 09:30");
//        myActivityDataset.add("Item is packed and will dispatch soon");
//        mDataList.add(new TimeLineModel("Courier is out to delivery your order", "2017-02-12 08:00"));
//        mDataList.add(new TimeLineModel("Item has reached courier facility at New Delhi", "2017-02-11 21:00"));
//        mDataList.add(new TimeLineModel("Item has been given to the courier", "2017-02-11 18:00"));
//        mDataList.add(new TimeLineModel("Item is packed and will dispatch soon", "2017-02-11 09:30"));
//        mDataList.add(new TimeLineModel("Order is being readied for dispatch", "2017-02-11 08:00"));
//        mDataList.add(new TimeLineModel("Order processing initiated", "2017-02-10 15:00"));
//        mDataList.add(new TimeLineModel("Order confirmed by seller", "2017-02-10 14:30"));
//        mDataList.add(new TimeLineModel("Order placed successfully", "2017-02-10 14:00"));
    }
}
