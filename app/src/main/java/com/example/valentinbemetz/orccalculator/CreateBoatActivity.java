package com.example.valentinbemetz.orccalculator;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.valentinbemetz.orccalculator.database.BoatDatabase;
import com.example.valentinbemetz.orccalculator.model.Boat;

public class CreateBoatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_boat);


        Button button = (Button) findViewById(R.id.btnAddBoat);

        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextInputLayout tiName = (TextInputLayout) findViewById(R.id.tiAddBoatName);
                    TextInputLayout tiInLo = (TextInputLayout) findViewById(R.id.tiAddBoatInLo);
                    TextInputLayout tiInMi = (TextInputLayout) findViewById(R.id.tiAddBoatInMi);
                    TextInputLayout tiInHi = (TextInputLayout) findViewById(R.id.tiAddBoatInHi);
                    TextInputLayout tiOfLo = (TextInputLayout) findViewById(R.id.tiAddBoatOfLo);
                    TextInputLayout tiOfMi = (TextInputLayout) findViewById(R.id.tiAddBoatOfMi);
                    TextInputLayout tiOfHi = (TextInputLayout) findViewById(R.id.tiAddBoatOfHi);
                    TextInputLayout tiYardstick = (TextInputLayout) findViewById(R.id.tiAddBoatYardtick);

                    BoatDatabase db = BoatDatabase.getInstance(CreateBoatActivity.this);

                    if(tiName.getEditText().length()==0){
                        Toast toast = Toast.makeText(getApplicationContext(),"Kein Name!",Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        if(tiInLo.getEditText().length()==0){
                            String name = tiName.getEditText().getText().toString();
                            db.createBoat(new Boat(name));
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
                        finish();
                        //Intent intent = new Intent(CreateBoatActivity.this, BoatsActivity.class);
                        //startActivity(intent);
                    }
                }
            });
        }
    }

}
