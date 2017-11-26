package com.example.g3org3.horoscopes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by G3ORG3 on 26.11.2017.
 */

public class GetJSONArr extends AsyncTask<Void, Void, Void> {

    List<String> listSigns;
    Context context;

    public GetJSONArr(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String data = "";
            String line = "";
            /*
            URL url = new URL("http://sandipbgt.com/theastrologer/api/horoscope/taurus/today/");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }*/

            data= "[ \"taurus\", \"aries\", \"gemini\" ]";

            JSONArray jsonSigns = new JSONArray(data);

            listSigns = new ArrayList<String>();
            for (int i=0; i<jsonSigns.length(); i++) {
                listSigns.add( jsonSigns.getString(i) );
            }




        /*} catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        HoroscopeAdapter myarrayAdapter = new HoroscopeAdapter(context, R.layout.horoscope_list_item, listSigns);
        HoroscopesList.lv.setAdapter(myarrayAdapter);
        HoroscopesList.lv.setTextFilterEnabled(true);
    }
}
