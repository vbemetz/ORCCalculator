package com.example.valentinbemetz.orccalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.valentinbemetz.orccalculator.adapter.listView.RaceListAdapter;
import com.example.valentinbemetz.orccalculator.database.BoatDatabase;
import com.example.valentinbemetz.orccalculator.model.Boat;
import com.example.valentinbemetz.orccalculator.model.Competitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RaceActivity extends AppCompatActivity {

    private ListView listView;
    private List<Competitor> competitors;
    private RaceListAdapter adapter;
    private Button btnAdd;

    public static final int newBoatRequest = 1;
    public static final int newTimeRequest = 2;

    public static final String YARDSTICK = "yardstick";
    public static final String INLO = "inlo";
    public static final String INMI = "inmi";
    public static final String INHI = "inhi";
    public static final String OFLO = "oflo";
    public static final String OFMI = "ofmi";
    public static final String OFHI = "ofhi";

    private RadioButton rbYardstick, rbIn, rbOf, rbLo, rbMi, rbHi;
    private RadioGroup rg1, rg2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);

        this.listView = (ListView)findViewById(R.id.lvCompetitors);

        rbYardstick = (RadioButton)findViewById(R.id.rbYardstick);
        rbIn = (RadioButton)findViewById(R.id.rbInshore);
        rbOf = (RadioButton)findViewById(R.id.rbOnshore);
        rbLo = (RadioButton)findViewById(R.id.rbLo);
        rbMi = (RadioButton)findViewById(R.id.rbMi);
        rbHi = (RadioButton)findViewById(R.id.rbHi);

        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg2 = (RadioGroup)findViewById(R.id.rg2);

        this.competitors = new ArrayList<>();
        //competitors.add(new Competitor(new Boat("Shooting Star"),3527));
        //competitors.add(new Competitor(new Boat("Mecki Messer"),34527));

        refreshView();
        btnAdd = (Button) findViewById(R.id.btn_addCompetitor);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RaceActivity.this, BoatsActivity.class);
                startActivityForResult(intent, newBoatRequest);
            }
        });

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RaceActivity.this, TimePickerActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, newTimeRequest);
            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                refreshView();
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                refreshView();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        BoatDatabase db = BoatDatabase.getInstance(RaceActivity.this);

        if(requestCode == newBoatRequest && resultCode == RESULT_OK){
            long newBoatId = data.getLongExtra(BoatsActivity.ADD_BOAT_TO_COMPETITOR,0);
            Boat newBoat = db.readBoat(newBoatId);
            competitors.add(new Competitor(newBoat));
            Log.e("Test", "Boat added: "+newBoat.getName());
            refreshView();
        }

        if(requestCode == newTimeRequest && resultCode ==RESULT_OK){
            int time = data.getIntExtra("time",0);
            int position  = data.getIntExtra("position", 0);
            Competitor comp = competitors.get(position);
            Log.e("Test", "new Time for " + comp.getBoat().getName()+", correctedTime: " + String.valueOf(comp.getCorrectedTime()));
            comp.setElapsedTime(time);
            refreshView();

        }
    }

    private void refreshView(){
        for(int i=0; i<competitors.size(); i++){
            Competitor comp = competitors.get(i);
            comp.setCorrectedTime(getCorrectorString(rg1,rg2));
        }
        if(rbYardstick.getId()==rg1.getCheckedRadioButtonId()){
            for (int i = 0; i < rg2.getChildCount(); i++) {
                rg2.getChildAt(i).setEnabled(false);
            }
        }else{
            for (int i = 0; i < rg2.getChildCount(); i++) {
                rg2.getChildAt(i).setEnabled(true);
            }
        }
        this.adapter = new RaceListAdapter(this, competitors);
        this.listView.setAdapter(adapter);
        Log.e("Test","viewRefreshed: ");
        adapter.sort(new Comparator<Competitor>() {
            @Override
            public int compare(Competitor o1, Competitor o2) {
                if (o1.getElapsedTime() == 0 && o2.getElapsedTime() == 0){
                    return 0;
                }else if (o1.getElapsedTime() ==0){
                    return 1;
                }else if (o2.getElapsedTime() ==0){
                    return -1;
                }
                int diff = o1.getCorrectedTime() - o2.getCorrectedTime();
                return diff;
            }
        });
    }

    private String getCorrectorString(RadioGroup rg1,RadioGroup rg2){

        String corrector = "";

        if(rg1.getCheckedRadioButtonId() == rbYardstick.getId()){
            corrector = YARDSTICK;
        }else if(rg1.getCheckedRadioButtonId() == rbIn.getId()){
            if(rg2.getCheckedRadioButtonId()==rbLo.getId()){
                corrector = INLO;
            }else if(rg2.getCheckedRadioButtonId()==rbMi.getId()){
                corrector = INMI;
            }else if(rg2.getCheckedRadioButtonId()==rbHi.getId()){
                corrector = INHI;
            }
        }else if(rg1.getCheckedRadioButtonId() == rbOf.getId()){
            if(rg2.getCheckedRadioButtonId()==rbLo.getId()){
                corrector = OFLO;
            }else if(rg2.getCheckedRadioButtonId()==rbMi.getId()){
                corrector = OFMI;
            }else if(rg2.getCheckedRadioButtonId()==rbHi.getId()){
                corrector = OFHI;
            }
        }

        return corrector;
    }
}
