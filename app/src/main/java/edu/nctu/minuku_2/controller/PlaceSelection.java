package edu.nctu.minuku_2.controller;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.nctu.minuku_2.MainActivity;
import edu.nctu.minuku_2.R;

//import edu.ohio.minuku_2.R;

public class PlaceSelection extends FragmentActivity implements OnMapReadyCallback {

    MapView mapView;
    Button AddPlace, SecRes, Muf, Third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_selection);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(PlaceSelection.this);
        AddPlace = (Button)findViewById(R.id.btn_addplace);
        SecRes = (Button)findViewById(R.id.btn_secRes);
        Muf = (Button)findViewById(R.id.btn_muf);
        Third = (Button)findViewById(R.id.btn_third);


        AddPlace.setOnClickListener(onClick);
    }

    private Button.OnClickListener onClick = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            final View v = LayoutInflater.from(PlaceSelection.this).inflate(R.layout.addplace, null);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlaceSelection.this);
            alertDialog.setTitle("自訂地點");
            alertDialog.setView(v);
            alertDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText editText = (EditText) v.findViewById(R.id.edit_text);
                    String name = editText.getText().toString();
                    AddPlace.setText("使用\"" + name + "\"為地點");
                }
            });
            alertDialog.show();
        }
    };

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
