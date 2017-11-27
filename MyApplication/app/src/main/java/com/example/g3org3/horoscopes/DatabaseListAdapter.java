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
 * Created by G3ORG3 on 27.11.2017.
 */

public class DatabaseListAdapter extends ArrayAdapter<Sunsign> {
        Context context;
        int layoutResourceId;
        List<Sunsign> data = null;


    public DatabaseListAdapter(Context context, int resource, List<Sunsign> objects) {
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
            item.textViewListSignDb = (TextView) row.findViewById(R.id.textViewListSignDb);
            item.textViewDateDb = (TextView) row.findViewById(R.id.textViewDateDb);
            item.imageSignDb = (ImageView) row.findViewById(R.id.imageViewSignDb);

            row.setTag(item);
            }
            else
            {
            item = (EntryHolder)row.getTag();
            }

            Sunsign entry = data.get(position);
            item.textViewListSignDb.setText(entry.sunsign);
            item.textViewDateDb.setText(entry.date);
            item.imageSignDb.setImageResource(context.getResources().getIdentifier(entry.sunsign.toLowerCase(), "drawable", context.getPackageName()));

            return row;
    }

    class EntryHolder {
        TextView textViewListSignDb;
        TextView textViewDateDb;
        ImageView imageSignDb;
    }
}