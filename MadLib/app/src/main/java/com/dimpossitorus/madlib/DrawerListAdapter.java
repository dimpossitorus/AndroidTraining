package com.dimpossitorus.madlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Dimpos Sitorus on 09/06/2016.
 */
public class DrawerListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public DrawerListAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_listview, parent, false);
        TextView item = (TextView) rowView.findViewById(R.id.itemName);
        item.setText(values[position]);
        return rowView;
    }
}
