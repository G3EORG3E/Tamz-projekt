package com.example.g3org3.horoscopes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HoroscopesList extends AppCompatActivity {
    Button click;
    public static TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscopes_list);

        data = (TextView) findViewById(R.id.jsonData);
        click = (Button) findViewById(R.id.button) ;


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new FetchJSONData("taurus")).execute();
            }
        });


    }
}
