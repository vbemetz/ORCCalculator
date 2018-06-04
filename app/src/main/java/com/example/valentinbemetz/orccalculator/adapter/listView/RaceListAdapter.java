package com.example.valentinbemetz.orccalculator.adapter.listView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.valentinbemetz.orccalculator.R;
import com.example.valentinbemetz.orccalculator.model.Competitor;

import java.util.List;

public class RaceListAdapter extends ArrayAdapter<Competitor> {
    public RaceListAdapter(@NonNull Context context, @NonNull List<Competitor> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Competitor currentCompetitor = getItem(position);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.race_list_item, parent, false);
        }

        ((TextView)view.findViewById(R.id.tvRaceName)).setText(currentCompetitor.getBoat().getName());
        ((TextView)view.findViewById(R.id.tvRaceElapsed)).setText(timeToString(currentCompetitor.getElapsedTime()));
        ((TextView)view.findViewById(R.id.tvRaceCorrected)).setText(timeToString(currentCompetitor.getCorrectedTime()));
    return view;

    }

    public String timeToString(int time){
        int hours;
        int minutes;
        int seconds;
        String hr;
        String min;
        String sec;

        hours = (int) time/(60*60);
        minutes = (int) time/60 % 60;
        seconds = (int) time % 60;

        if (hours <10){
            hr = "0" + String.valueOf(hours);
        }else hr = String.valueOf(hours);

        if (minutes <10){
            min = "0" + String.valueOf(minutes);
        }else min = String.valueOf(minutes);

        if (seconds <10){
            sec = "0" + String.valueOf(seconds);
        }else sec = String.valueOf(seconds);

        return hr+":"+min+":"+sec;

    }
}
