package com.example.g3org3.horoscopes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

public class FetchJSONHoroscope extends AsyncTask<Void, Void, Void> {

    String sunsign;
    Sunsign sunsignToday;
    Sunsign sunsignYesterday;
    Sunsign sunsignTomorrow;
    Context context;

    public FetchJSONHoroscope(String sunsign, Context context) {
        this.sunsign = sunsign;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {

        sunsignToday = this.fetchData(sunsign,"today");
        sunsignYesterday = this.fetchData(sunsign,"yesterday");
        sunsignTomorrow = this.fetchData(sunsign,"tomorrow");

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Intent intent = new Intent(context, HoroscopeDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sunsignToday", sunsignToday);
        intent.putExtra("sunsignYesterday", sunsignYesterday);
        intent.putExtra("sunsignTomorrow", sunsignTomorrow);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }

    protected static Sunsign fetchData(String thisSunsign, String day) {
        Sunsign sunsignObj = new Sunsign();

        try {
            String data = "";
            String line = "";
            URL url = new URL("http://sandipbgt.com/theastrologer/api/horoscope/"+thisSunsign+"/"+day+"/");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }

            //data="{ \"date\": \"2015-10-21\", \"sunsign\": \"Aquarius\", \"horoscope\": \"Today you're infused with the excitement of discovery. The world is full of possibility. It's a great time to research or develop a project, whether a personal or professional one. The important thing is to be creative: The more innovative your ideas, the better. A pioneering approach will not only lead to big things, it will impress your employer and other VIPs who are looking for something just a little bit different now.\", \"meta\": { \"intensity\": \"85%\", \"keywords\": \"loyal, carrier\", \"mood\": \"revolutionary\" } }";

            JSONObject thisSign = new JSONObject(data);
            JSONObject signMeta = (JSONObject) thisSign.get("meta");

            sunsignObj.setAttrs(thisSign.get("horoscope").toString() + day,
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

        return sunsignObj;
    }


}
