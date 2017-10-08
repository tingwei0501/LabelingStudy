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
import edu.nctu.minuku_2.controller.home;
import edu.nctu.minuku_2.controller.report;
import edu.nctu.minuku_2.service.BackgroundService;

//import edu.ohio.minuku_2.R;

public class CounterActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

    }

}
