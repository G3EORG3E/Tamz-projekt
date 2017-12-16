package com.example.g3org3.horoscopes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

import java.util.List;

public class DatabaseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);

        FavouritesDbHandler db = new FavouritesDbHandler(DatabaseList.this);

        List<Sunsign> fetchedDbHoroscopes = db.getAllHoroscopes();

        ListView lv = (ListView) findViewById(R.id.databaseList);

        DatabaseListAdapter myarrayAdapter = new DatabaseListAdapter(this, R.layout.database_list_item, fetchedDbHoroscopes);
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
}
