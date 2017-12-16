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

        this.setTitle("Horoscopes - Intensity Compare");
        Intent gettedIntent = getIntent();
        ArrayList<Sunsign> allHoroscopes = (ArrayList<Sunsign>) gettedIntent.getSerializableExtra("allHoroscopes");

        setContentView(new GraphView(this,allHoroscopes));

        Toast.makeText(HoroscopeGraph.this, "sddd", Toast.LENGTH_LONG).show();

    }
}
