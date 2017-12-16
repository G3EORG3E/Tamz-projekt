package com.example.g3org3.horoscopes;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class HoroscopesList extends AppCompatActivity {
    public static ListView lv;
    Context context = HoroscopesList.this;
    private GestureDetector mGesture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscopes_list);

        mGesture = new GestureDetector(this, mOnGesture);

        lv = (ListView) findViewById(R.id.horoscopeList);
        (new FetchJSONArr(context)).execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String thisentry = (String)parent.getItemAtPosition(position);

            (new FetchJSONHoroscope(thisentry.toLowerCase(), context)).execute();

            Toast.makeText(HoroscopesList.this.getApplicationContext(), thisentry, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean handled = super.dispatchTouchEvent(ev);
        handled = mGesture.onTouchEvent(ev);
        return handled;
    }

    private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (velocityX < -5000 ) { // potáhnutí doleva

                Intent fav = new Intent(context,DatabaseList.class);
                fav.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(fav);
                overridePendingTransition(R.anim.slide_in_left, R.anim.stable);
            }
            else if (velocityX > 5000) { // potáhnutí doprava

            }

            return true;
        }
    };
}
