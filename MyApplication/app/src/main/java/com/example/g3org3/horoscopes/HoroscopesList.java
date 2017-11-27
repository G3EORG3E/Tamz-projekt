package com.example.g3org3.horoscopes;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class HoroscopesList extends AppCompatActivity {
    public static ListView lv;
    Context context = HoroscopesList.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscopes_list);

        lv = (ListView) findViewById(R.id.horoscopeList);
        (new GetJSONArr(context)).execute();

       /* click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new FetchJSONData("taurus")).execute();
            }
        }); */


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String thisentry = (String)parent.getItemAtPosition(position);

            (new FetchJSONData(thisentry.toLowerCase(), context)).execute();

            Toast.makeText(HoroscopesList.this.getApplicationContext(), thisentry, Toast.LENGTH_LONG).show();
            }
        });


    }
}
