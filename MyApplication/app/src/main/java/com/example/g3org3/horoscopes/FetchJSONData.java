package com.example.g3org3.horoscopes;

import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by G3ORG3 on 25.11.2017.
 */

public class FetchJSONData extends AsyncTask<Void, Void, Void> {
    Sunsign sunsignObj;
    String sunsign;

    public FetchJSONData(String sunsign) {
        this.sunsign = sunsign;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL("http://sandipbgt.com/theastrologer/api/horoscope/"+sunsign+"/today/");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data = "";
            String line = "";

            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject thisSign = new JSONObject(data);
            JSONObject signMeta = (JSONObject) thisSign.get("meta");

            sunsignObj = new Sunsign(thisSign.get("horoscope").toString(),
                                    signMeta.get("intensity").toString(),
                                    signMeta.get("mood").toString(),
                                    signMeta.get("keywords").toString(),
                                    thisSign.get("date").toString(),
                                    thisSign.get("sunsign").toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        HoroscopesList.data.setText(sunsignObj.mood);
    }
}
