package com.example.g3org3.horoscopes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

                Sunsign thisentry = (Sunsign) parent.getItemAtPosition(position);
                Toast.makeText(DatabaseList.this, thisentry.sunsign, Toast.LENGTH_LONG).show();

            }
        });
    }
}
