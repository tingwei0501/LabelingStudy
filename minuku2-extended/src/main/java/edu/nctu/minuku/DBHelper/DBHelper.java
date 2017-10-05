package edu.nctu.minuku.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONObject;

import edu.nctu.minuku.manager.DBManager;


/**
 * Created by Lawrence on 2017/6/5.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance = null;

    public static final String id = "_id";
    public static final String TAG = "DBHelper";

    public static final String home_col = "home";
    public static final String neighbor_col = "neighbor";
    public static final String outside_col = "outside";
    public static final String homeorfaraway = "homeorfaraway";
    public static final String staticornot = "staticornot";
    public static final String DEVICE = "device_id";
    public static final String USERID = "user_id";
    public static final String TIME = "time"; //timeToSQLite

//    public static final String TaskDayCount = "TaskDayCount";
//    public static final String HOUR = "hour";

    //checkFamiliarOrNot link list
    public static final String link_col = "link";
    public static final String clickornot_col = "clickornot";

    //Location and Trip
    public static final String sessionid_col = "sessionid";
    public static final String latitude_col = "latitude";
    public static final String longitude_col = "longitude";
    public static final String Accuracy_col = "Accuracy";
    public static final String Altitude_col = "Altitude";
    public static final String Speed_col = "Speed";
    public static final String Bearing_col = "Bearing";
    public static final String Provider_col = "Provider";

    //ActivityRecognition
    public static final String MostProbableActivity_col = "MostProbableActivity";
    public static final String ProbableActivities_col = "ProbableActivities";

    public static final String trip_col = "Trip";

    //Transportation
    public static final String confirmTransportation_col = "Transportation";

    //Trip Annotate data
    public static final String Trip_updatedStatus = "UpdatedStatus";
    public static final String Trip_startTimeSecond = "Trip_startTimeSecond";
    public static final String Trip_startTime = "Trip_startTime";
    public static final String Trip_endTime = "Trip_endTime";
    public static final String Trip_id = "Trip_id";
    public static final String activityType = "ActivityType";
    public static final String tripType = "TripType";
    public static final String preplan = "Preplan";
    public static final String ans1 = "Ques1";
    public static final String ans2 = "Ques2";
    public static final String ans3 = "Ques3";
    public static final String ans4_1 = "Ques4_1";
    public static final String ans4_2 = "Ques4_2";
    public static final String lat = "latitude";
    public static final String lng = "longitude";

    public static JSONObject location_jsonObject;

    //ringer
    public static final String RingerMode_col = "RingerMode";
    public static final String AudioMode_col = "AudioMode";
    public static final String StreamVolumeMusic_col = "StreamVolumeMusic";
    public static final String StreamVolumeNotification_col = "StreamVolumeNotification";
    public static final String StreamVolumeRing_col = "StreamVolumeRing";
    public static final String StreamVolumeVoicecall_col = "StreamVolumeVoicecall";
    public static final String StreamVolumeSystem_col = "StreamVolumeSystem";

    //battery
    public static final String BatteryLevel_col = "BatteryLevel";
    public static final String BatteryPercentage_col = "BatteryPercentage";
    public static final String BatteryChargingState_col = "BatteryChargingState";
    public static final String isCharging_col = "isCharging";

    //connectivity
    public static final String NetworkType_col = "NetworkType";
    public static final String IsNetworkAvailable_col = "IsNetworkAvailable";
    public static final String IsConnected_col = "IsConnected";
    public static final String IsWifiAvailable_col = "IsWifiAvailable";
    public static final String IsMobileAvailable_col = "IsMobileAvailable";
    public static final String IsWifiConnected_col = "IsWifiConnected";
    public static final String IsMobileConnected_col = "IsMobileConnected";

    //AppUsage
    public static final String ScreenStatus_col = "ScreenStatus";
    public static final String Latest_Used_App_col = "Latest_Used_App";
    public static final String Latest_Foreground_Activity_col = "Latest_Foreground_Activity";

    //table name
    public static final String location_table = "Location";
    public static final String activityRecognition_table = "ActivityRecognition";
    public static final String transportationMode_table = "TransportationMode";
    public static final String annotate_table = "Annotate";
    public static final String trip_table = "Trip";
    public static final String ringer_table = "Ringer";
    public static final String battery_table = "Battery";
    public static final String connectivity_table = "Connectivity";
    public static final String appUsage_table = "AppUsage";


    public static final String DATABASE_NAME = "MySQLite.db";
    public static int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        initiateDBManager();

    }

    public static DBHelper getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new DBHelper(applicationContext);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","oncreate");

        createTransportationModeTable(db);
        createARTable(db);
        createLocationTable(db);
        createTripTable(db);
        createRingerTable(db);
        createBatteryTable(db);
        createConnectivityTable(db);
        createAppUsageTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void initiateDBManager() {
        DBManager.initializeInstance(this);
    }

    public void createTransportationModeTable(SQLiteDatabase db){
        Log.d(TAG,"create TransportationMode table");

        String cmd = "CREATE TABLE " +
                transportationMode_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                confirmTransportation_col+" TEXT" +
                ");";

        db.execSQL(cmd);

    }

    //TODO mabe need to expand
    public void createAppUsageTable(SQLiteDatabase db){
        Log.d(TAG,"create AppUsage table");

        String cmd = "CREATE TABLE " +
                appUsage_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                ScreenStatus_col+" TEXT," +
                Latest_Used_App_col+" TEXT," +
                Latest_Foreground_Activity_col+" TEXT" +
                ");";

        db.execSQL(cmd);
    }

    public void createConnectivityTable(SQLiteDatabase db){
        Log.d(TAG,"create Connectivity table");

        String cmd = "CREATE TABLE " +
                connectivity_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                NetworkType_col+" TEXT," +
                IsNetworkAvailable_col+" BOOLEAN," +
                IsConnected_col+" BOOLEAN," +
                IsWifiAvailable_col+" BOOLEAN," +
                IsMobileAvailable_col+" BOOLEAN," +
                IsWifiConnected_col+" BOOLEAN," +
                IsMobileConnected_col+" BOOLEAN" +
                ");";

        db.execSQL(cmd);
    }

    public void createBatteryTable(SQLiteDatabase db){
        Log.d(TAG,"create Battery table");

        String cmd = "CREATE TABLE " +
                battery_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                BatteryLevel_col+" INTEGER," +
                BatteryPercentage_col+" FLOAT," +
                BatteryChargingState_col+" TEXT," +
                isCharging_col+" BOOLEAN" +
                ");";

        db.execSQL(cmd);
    }

    public void createRingerTable(SQLiteDatabase db){
        Log.d(TAG,"create Ringer table");

        String cmd = "CREATE TABLE " +
                ringer_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                RingerMode_col+" TEXT," +
                AudioMode_col+" TEXT," +
                StreamVolumeMusic_col+" INTEGER," +
                StreamVolumeNotification_col+" INTEGER," +
                StreamVolumeRing_col+" INTEGER," +
                StreamVolumeVoicecall_col+" INTEGER," +
                StreamVolumeSystem_col+" INTEGER" +
                ");";

        db.execSQL(cmd);
    }

    public void createARTable(SQLiteDatabase db){
        Log.d(TAG,"create AR table");

        String cmd = "CREATE TABLE " +
                activityRecognition_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                MostProbableActivity_col+" TEXT," +
                ProbableActivities_col +" TEXT " +
                ");";

        db.execSQL(cmd);

    }

    public void createLocationTable(SQLiteDatabase db){
        Log.d(TAG,"create location table");

        String cmd = "CREATE TABLE " +
                location_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                latitude_col+" FLOAT,"+
                longitude_col +" FLOAT, " +
                Accuracy_col + " FLOAT, " +
                Altitude_col +" FLOAT," +
                Speed_col +" FLOAT," +
                Bearing_col +" FLOAT," +
                Provider_col +" TEXT" +
                ");";

        db.execSQL(cmd);

    }

    public void createTripTable(SQLiteDatabase db){

        Log.d(TAG,"create trip table");

        String cmd = "CREATE TABLE " +
                trip_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                sessionid_col + " TEXT," +
                latitude_col+" FLOAT,"+
                longitude_col +" FLOAT, " +
                Accuracy_col + " FLOAT" +
                ");";

        db.execSQL(cmd);
    }

}
