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
            String data = "";
            String line = "";
            /*URL url = new URL("http://sandipbgt.com/theastrologer/api/horoscope/"+sunsign+"/today/");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }*/

            data="{ \"date\": \"2015-10-21\", \"sunsign\": \"Aquarius\", \"horoscope\": \"Today you're infused with the excitement of discovery. The world is full of possibility. It's a great time to research or develop a project, whether a personal or professional one. The important thing is to be creative: The more innovative your ideas, the better. A pioneering approach will not only lead to big things, it will impress your employer and other VIPs who are looking for something just a little bit different now.\", \"meta\": { \"intensity\": \"85%\", \"keywords\": \"loyal, carrier\", \"mood\": \"revolutionary\" } }";

            JSONObject thisSign = new JSONObject(data);
            JSONObject signMeta = (JSONObject) thisSign.get("meta");

            sunsignObj = new Sunsign(thisSign.get("horoscope").toString(),
                                    signMeta.get("intensity").toString(),
                                    signMeta.get("mood").toString(),
                                    signMeta.get("keywords").toString(),
                                    thisSign.get("date").toString(),
                                    thisSign.get("sunsign").toString());


       /* } catch (MalformedURLException e) {
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
        HoroscopeDetail.data.setText(sunsignObj.horoscope);
    }
}
