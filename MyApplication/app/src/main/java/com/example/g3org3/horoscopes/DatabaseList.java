package com.example.g3org3.horoscopes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.widget.Toast;

import java.util.List;

public class DatabaseList extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager SM;
    private static final float SHAKE_THRESHOLD = 3.25f;
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private SensorManager mSensorMgr;
    ListView lv;
    List<Sunsign> fetchedDbHoroscopes;
    DatabaseListAdapter myarrayAdapter;
    boolean canShow = true;
    AlertDialog.Builder mBuilder;
    View mView;
    Button removeAll;
    Button cancelBtn;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);

        // dialog
        mBuilder = new AlertDialog.Builder(DatabaseList.this);
        mView = getLayoutInflater().inflate(R.layout.dialog_remove_all, null);
        removeAll = (Button) mView.findViewById(R.id.buttonDialogRemoveDB);
        cancelBtn = (Button) mView.findViewById(R.id.buttonDialogCancel);
        mBuilder.setView(mView);

        dialog = mBuilder.create();

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canShow = true;
                FavouritesDbHandler db = new FavouritesDbHandler(DatabaseList.this);
                db.resetHorosopeTable();
                fetchedDbHoroscopes.clear();
                lv.setAdapter(myarrayAdapter);
                dialog.hide();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canShow = true;
                dialog.hide();
            }
        });

        // akcelerrator
        mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorMgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }



        FavouritesDbHandler db = new FavouritesDbHandler(DatabaseList.this);

        fetchedDbHoroscopes = db.getAllHoroscopes();

        lv = (ListView) findViewById(R.id.databaseList);

        myarrayAdapter = new DatabaseListAdapter(this, R.layout.database_list_item, fetchedDbHoroscopes);
        lv.setAdapter(myarrayAdapter);
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sunsign horoscope = (Sunsign) parent.getItemAtPosition(position);

                Intent detail = new Intent(DatabaseList.this,DatabaseDetail.class);
                detail.putExtra("horosocpe", horoscope);
                startActivity(detail);

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;

                if (acceleration > SHAKE_THRESHOLD && canShow) {
                    canShow = false;
                    mLastShakeTime = curTime;

                    dialog.show();
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
