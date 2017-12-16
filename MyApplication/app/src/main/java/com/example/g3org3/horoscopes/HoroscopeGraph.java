package com.example.g3org3.horoscopes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class HoroscopeGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_graph);
        Intent gettedIntent = getIntent();
        ArrayList<Sunsign> allHoroscopes = (ArrayList<Sunsign>) gettedIntent.getSerializableExtra("allHoroscopes");

        int intenstiyNumb = Integer.parseInt(allHoroscopes.get(0).getIntensity().replace("%",""));

        Toast.makeText(HoroscopeGraph.this, ""+ intenstiyNumb, Toast.LENGTH_LONG).show();

    }
}
