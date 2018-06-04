package com.example.valentinbemetz.orccalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valentinbemetz.orccalculator.adapter.listView.BoatListAdapter;
import com.example.valentinbemetz.orccalculator.database.BoatDatabase;
import com.example.valentinbemetz.orccalculator.model.Boat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BoatsActivity extends AppCompatActivity {

    private ListView listView;
    private List<Boat> dataSource;
    private BoatListAdapter adapter;

    public static final String ADD_BOAT_TO_COMPETITOR = "addBoatCompetitor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);

        this.listView = (ListView)findViewById(R.id.boats);

        Button addBoat = (Button)findViewById(R.id.addBoat);

        this.dataSource = BoatDatabase.getInstance(this).readAllBoats();
        //this.dataSource = new ArrayList<>();
        //dataSource.add(new Boat("Shaker"));
        //dataSource.add(new Boat("Full Moon",0.8));

        this.adapter = new BoatListAdapter(this, dataSource);
        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object element = parent.getAdapter().getItem(position);
                Boat clickedBoat = (Boat)element;
                Log.e("Click on List", clickedBoat.getName());
                Intent intent = getIntent();
                intent.putExtra(ADD_BOAT_TO_COMPETITOR,clickedBoat.getId());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object element = parent.getAdapter().getItem(position);
                Boat clickedBoat = (Boat) element;
                Intent intent = new Intent(BoatsActivity.this, EditBoatActivity.class);
                intent.putExtra(EditBoatActivity.BOAT_KEY, clickedBoat.getId());

                startActivity(intent);
                return false;

            }
        });

        if(addBoat != null){
            addBoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BoatsActivity.this, CreateBoatActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshListView();
    }

    private void refreshListView(){
        dataSource.clear();
        dataSource.addAll(BoatDatabase.getInstance(this).readAllBoats());
        adapter.notifyDataSetChanged();
        adapter.sort(new Comparator<Boat>() {
            @Override
            public int compare(Boat o1, Boat o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

}
