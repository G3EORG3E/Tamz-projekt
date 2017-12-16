package com.example.g3org3.horoscopes;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HoroscopeDetail extends AppCompatActivity {

    static Intent gettedIntent;
    static Sunsign sunsignToday;
    static Sunsign sunsignYesterday;
    static Sunsign sunsignTomorrow;
    Context context = HoroscopeDetail.this;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);

        gettedIntent = this.getIntent();

        sunsignToday = (Sunsign)gettedIntent.getSerializableExtra("sunsignToday");
        sunsignYesterday = (Sunsign)gettedIntent.getSerializableExtra("sunsignYesterday");
        sunsignTomorrow = (Sunsign)gettedIntent.getSerializableExtra("sunsignTomorrow");

        this.setTitle(sunsignToday.sunsign);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View thisView= view;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(HoroscopeDetail.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_addnote, null);

                final EditText mNote = (EditText) mView.findViewById(R.id.dialog_note);
                Button mAdd = (Button) mView.findViewById(R.id.dialog_button_add);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FavouritesDbHandler db = new FavouritesDbHandler(HoroscopeDetail.this);


                        if (mViewPager.getCurrentItem() == 0) {
                            sunsignYesterday.note = mNote.getText().toString();
                            db.addHoroscope(sunsignYesterday);
                        }
                        else if (mViewPager.getCurrentItem() == 1) {
                            sunsignToday.note = mNote.getText().toString();
                            db.addHoroscope(sunsignToday);
                        }
                        else {
                            sunsignTomorrow.note = mNote.getText().toString();
                            db.addHoroscope(sunsignTomorrow);
                        }

                        dialog.hide();
                        Snackbar.make(thisView, "Uloženo do oblíbených", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });

                dialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horoscope_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_horoscope_detail, container, false);

            ImageView im = (ImageView)  rootView.findViewById(R.id.imageViewSignDetailDB);
            im.setImageResource(getContext().getResources().getIdentifier(sunsignToday.sunsign.toLowerCase(), "drawable", getContext().getPackageName()));

            TextView data = (TextView) rootView.findViewById(R.id.section_label_horoscope);
            TextView mood = (TextView) rootView.findViewById(R.id.textViewMood);
            TextView intenstiy = (TextView) rootView.findViewById(R.id.textViewIntensity);
            TextView keywords = (TextView) rootView.findViewById(R.id.textViewKeywords);
            Button showChart = (Button) rootView.findViewById(R.id.buttonShowChart);

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                data.setText(sunsignYesterday.horoscope);
                mood.setText(sunsignYesterday.mood);
                intenstiy.setText(sunsignYesterday.intensity);
                keywords.setText(sunsignYesterday.keywords);

                showChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        (new FetchHoroscoperForGraph(getContext(), "yesterday")).execute();
                    }
                });
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                data.setText(sunsignToday.horoscope);
                mood.setText(sunsignToday.mood);
                intenstiy.setText(sunsignToday.intensity);
                keywords.setText(sunsignToday.keywords);

                showChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        (new FetchHoroscoperForGraph(getContext(), "today")).execute();
                    }
                });
            }
            else {
                data.setText(sunsignTomorrow.horoscope);
                mood.setText(sunsignTomorrow.mood);
                intenstiy.setText(sunsignTomorrow.intensity);
                keywords.setText(sunsignTomorrow.keywords);

                showChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        (new FetchHoroscoperForGraph(getContext(), "tomorrow")).execute();
                    }
                });
            }

            return rootView;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "YESTERDAY";
                case 1:
                    return "TODAY";
                case 2:
                    return "TOMORROW";
            }
            return null;
        }
    }
}
