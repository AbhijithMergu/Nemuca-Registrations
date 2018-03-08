package com.example.abhijith.eventsapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pavan on 3/9/2018.
 */

public class CustomListAdapter extends ArrayAdapter<String> {
    List<String> events;
    Context context;
    int resource;
    public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<String> events) {
        super(context, resource, events);
        this.context = context;
        this.resource = resource;
        this.events = events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);
        String event = events.get(position);
        TextView textView = (TextView) view.findViewById(R.id.list_item_text);
        textView.setText(event);
        return view;
    }
}
