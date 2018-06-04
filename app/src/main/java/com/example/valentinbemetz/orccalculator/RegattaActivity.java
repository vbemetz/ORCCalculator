package com.example.valentinbemetz.orccalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RegattaActivity extends AppCompatActivity {

    TableLayout table;
    ScrollView scrollView, tableScrollVert;
    HorizontalScrollView horizontalScrollView, tableScrollHor;
    TableLayout tableRow;
    TableLayout tableColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regatta);

        table = findViewById(R.id.table_regatta);
        scrollView = findViewById(R.id.sv_row);
        tableScrollHor = findViewById(R.id.table_scrollHor);
        tableScrollVert = findViewById(R.id.table_scrollVert);
        horizontalScrollView = findViewById(R.id.sv_column);
        tableRow = findViewById(R.id.table_row);
        tableColumn = findViewById(R.id.table_column);

        tableRow.removeAllViews();
        tableColumn.removeAllViews();


        for(int r = 1; r <= 20; r++){
            TableRow row = new TableRow(this.getApplicationContext());
            for (int c = 1; c <= 5; c++){
                TextView tv = new TextView(this.getApplicationContext());
                //tv.setText("Das ist ein Text "+String.valueOf(10*(r-1)+c));
                tv.setText("TestTestTest");
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
        table.

        for (int i = 0; i<table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            if (i==0){
                TableRow row1 = new TableRow(this);
                for (int j = 0; j < row.getChildCount(); j++) {
                    View tv = row.getChildAt(j);
                    TextView tv1 = new TextView(this);
                    tv1.setText("Column "+String.valueOf(j));
                    tv1.setPadding(16,16,16,16);
                    row1.addView(tv1);
                }
                row1.setLayoutParams(row.getLayoutParams());
                tableColumn.addView(row1);
            }
            TextView tv = (TextView)((TableRow)table.getChildAt(i)).getChildAt(0);
            TextView tv1 = new TextView(this);
            tv1.setText("Row "+String.valueOf(i));
            tv1.setPadding(16,16,16,16);
            TableRow row1 = new TableRow(this);
            row1.addView(tv1);
            tableRow.addView(row1);
        }


    }
}
