package com.example.g3org3.horoscopes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HoroscopeDetail extends AppCompatActivity {
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);

        data = (TextView) findViewById(R.id.textViewHoroscope);

        Intent intent = getIntent();
        String gettedSign = intent.getExtras().getString("sign");

        (new FetchJSONData(gettedSign)).execute();

    }
}
