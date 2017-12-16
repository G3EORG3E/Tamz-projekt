package com.example.g3org3.horoscopes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_detail);
        Context context = DatabaseDetail.this;

        Intent gettedHor = getIntent();
        final Sunsign horoscopeObj = (Sunsign) gettedHor.getSerializableExtra("horosocpe");
        this.setTitle(horoscopeObj.sunsign.toUpperCase());

        TextView mood = (TextView) findViewById(R.id.textViewMoodDB);
        TextView intenstiy = (TextView) findViewById(R.id.textViewIntensityDB);
        TextView horosocpe = (TextView) findViewById(R.id.editTextHoroscopeDB);
        TextView keywords = (TextView) findViewById(R.id.textViewKeywordsDB);
        TextView note = (TextView) findViewById(R.id.textViewNoteDB);
        Button delete = (Button) findViewById(R.id.buttonDeleteFromDB);
        ImageView im = (ImageView)  findViewById(R.id.imageViewSignDetailDB);

        mood.setText(horoscopeObj.mood);
        intenstiy.setText(horoscopeObj.intensity);
        horosocpe.setText(horoscopeObj.horoscope);
        keywords.setText(horoscopeObj.keywords);
        note.setText(horoscopeObj.note);
        im.setImageResource(context.getResources().getIdentifier(horoscopeObj.sunsign.toLowerCase(), "drawable", context.getPackageName()));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavouritesDbHandler db = new FavouritesDbHandler(DatabaseDetail.this);
                db.deleteHoroscope(horoscopeObj);

                Toast.makeText(DatabaseDetail.this, "Odstraneno", Toast.LENGTH_LONG).show();

                Intent list = new Intent(DatabaseDetail.this,DatabaseList.class);
                startActivity(list);
            }
        });


    }
}
