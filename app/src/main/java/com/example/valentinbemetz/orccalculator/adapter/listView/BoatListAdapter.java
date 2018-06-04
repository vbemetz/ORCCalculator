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
import com.example.valentinbemetz.orccalculator.model.Boat;

import java.util.List;

public class BoatListAdapter extends ArrayAdapter<Boat> {
    public BoatListAdapter(@NonNull Context context, @NonNull List<Boat> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Boat currentBoat = getItem(position);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.boat_list_item, parent, false);
        }

        ((TextView)view.findViewById(R.id.name)).setText(currentBoat.getName());

        TextView tvYardstick = (TextView)view.findViewById(R.id.tvBoatsYardstick);
        TextView tvIn = (TextView)view.findViewById(R.id.tvBoatsIn);
        TextView tvOf = (TextView)view.findViewById(R.id.tvBoatsOf);

        tvYardstick.setText(String.valueOf(currentBoat.getYardstick()));
        tvIn.setText(String.valueOf(currentBoat.getInLo())+" / "+String.valueOf(currentBoat.getInMi())+" / "+String.valueOf(currentBoat.getInHi()));
        tvOf.setText(String.valueOf(currentBoat.getOfLo())+" / "+String.valueOf(currentBoat.getOfMi())+" / "+String.valueOf(currentBoat.getOfHi()));


        return view;

    }
}
