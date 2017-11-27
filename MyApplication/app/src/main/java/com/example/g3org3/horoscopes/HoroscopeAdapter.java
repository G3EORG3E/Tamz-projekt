package com.example.g3org3.horoscopes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by G3ORG3 on 26.11.2017.
 */

public class HoroscopeAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutResourceId;
    List<String> data = null;


    public HoroscopeAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder item = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(layoutResourceId, parent, false);

            item = new EntryHolder();
            item.textViewListSign = (TextView) row.findViewById(R.id.textViewListSign);
            item.imageSign = (ImageView) row.findViewById(R.id.imageViewSign);

            row.setTag(item);
        }
        else
        {
            item = (EntryHolder)row.getTag();
        }

        String entry = data.get(position);
        item.textViewListSign.setText(entry);
        item.imageSign.setImageResource(context.getResources().getIdentifier(entry.toLowerCase(), "drawable", context.getPackageName()));

        return row;
    }

    class EntryHolder {
        TextView textViewListSign;
        ImageView imageSign;
    }
}
