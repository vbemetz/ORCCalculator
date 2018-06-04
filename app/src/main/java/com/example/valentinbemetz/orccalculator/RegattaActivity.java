package com.example.valentinbemetz.orccalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RegattaActivity extends AppCompatActivity {

    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regatta);

        table = findViewById(R.id.table_regatta);

        for(int r = 1; r <= 100; r++){
            TableRow row = new TableRow(this.getApplicationContext());
            for (int c = 1; c <= 10; c++){
                TextView tv = new TextView(this.getApplicationContext());
                tv.setText("Das ist ein Text "+String.valueOf(10*(r-1)+c));
                tv.setPadding(16,16,16,16);
                if (r%2==0){
                    if(c%2==0) {
                        tv.setBackgroundColor(getResources().getColor(R.color.colorDarkGrey));
                    }else{
                        tv.setBackgroundColor(getResources().getColor(R.color.colorMidGrey));
                    }
                }else{
                    if(c%2==0) {
                        tv.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
                    }else{
                        tv.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    }
                }
                row.addView(tv);
            }
            table.addView(row);
        }
    }
}
