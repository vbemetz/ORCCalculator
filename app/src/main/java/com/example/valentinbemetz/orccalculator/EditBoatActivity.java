package com.example.valentinbemetz.orccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.valentinbemetz.orccalculator.database.BoatDatabase;
import com.example.valentinbemetz.orccalculator.model.Boat;

public class EditBoatActivity extends AppCompatActivity {

    public static final String BOAT_KEY = "boat_id";

    private Button btnSave, btnDelete;
    private TextInputLayout tiName, tiInLo, tiInMi, tiInHi, tiOfLo, tiOfMi, tiOfHi, tiYardstick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_boat);

        long id = getIntent().getLongExtra(BOAT_KEY,0);

        Boat boat = BoatDatabase.getInstance(this).readBoat(id);

        btnSave = (Button) findViewById(R.id.btnEditBoatSave);
        btnDelete = (Button) findViewById(R.id.btnEditBoatDelete);

        tiName = (TextInputLayout) findViewById(R.id.tiEditBoatName);
        tiInLo = (TextInputLayout) findViewById(R.id.tiEditBoatInLo);
        tiInMi = (TextInputLayout) findViewById(R.id.tiEditBoatInMi);
        tiInHi = (TextInputLayout) findViewById(R.id.tiEditBoatInHi);
        tiOfLo = (TextInputLayout) findViewById(R.id.tiEditBoatOfLo);
        tiOfMi = (TextInputLayout) findViewById(R.id.tiEditBoatOfMi);
        tiOfHi = (TextInputLayout) findViewById(R.id.tiEditBoatOfHi);
        tiYardstick = (TextInputLayout) findViewById(R.id.tiEditBoatYardtick);

        tiName.getEditText().setText(boat.getName());
        tiInLo.getEditText().setText(String.valueOf(boat.getInLo()));
        tiInMi.getEditText().setText(String.valueOf(boat.getInMi()));
        tiInHi.getEditText().setText(String.valueOf(boat.getInHi()));
        tiOfLo.getEditText().setText(String.valueOf(boat.getOfLo()));
        tiOfMi.getEditText().setText(String.valueOf(boat.getOfMi()));
        tiOfHi.getEditText().setText(String.valueOf(boat.getOfHi()));
        tiYardstick.getEditText().setText(String.valueOf(boat.getYardstick()));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = getIntent().getLongExtra(BOAT_KEY,0);

                BoatDatabase db = BoatDatabase.getInstance(EditBoatActivity.this);

                Boat boat = db.readBoat(id);

                if(tiName.getEditText().length()==0){
                    Toast toast = Toast.makeText(getApplicationContext(),"Kein Name!",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(tiInLo.getEditText().length()==0){
                        String name = tiName.getEditText().getText().toString();
                        boat.setName(name);
                        boat.setInLo(0);
                    }else{
                        String name = tiName.getEditText().getText().toString();
                        double InLo = Double.parseDouble(tiInLo.getEditText().getText().toString());
                        double InMi = Double.parseDouble(tiInMi.getEditText().getText().toString());
                        double InHi = Double.parseDouble(tiInHi.getEditText().getText().toString());
                        double OfLo = Double.parseDouble(tiOfLo.getEditText().getText().toString());
                        double OfMi = Double.parseDouble(tiOfMi.getEditText().getText().toString());
                        double OfHi = Double.parseDouble(tiOfHi.getEditText().getText().toString());
                        int yardstick = Integer.parseInt(tiYardstick.getEditText().getText().toString());
                        db.createBoat(new Boat(name, InLo, InMi, InHi, OfLo, OfMi, OfHi, yardstick));
                    }
                    db.updateBoat(boat);

                    Intent intent = new Intent(EditBoatActivity.this, BoatsActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = getIntent().getLongExtra(BOAT_KEY,0);
                Boat boat = BoatDatabase.getInstance(EditBoatActivity.this).readBoat(id);
                BoatDatabase.getInstance(EditBoatActivity.this).deleteBoat(boat);

                Intent intent = new Intent(EditBoatActivity.this, BoatsActivity.class);
                startActivity(intent);
            }
        });


    }

}
