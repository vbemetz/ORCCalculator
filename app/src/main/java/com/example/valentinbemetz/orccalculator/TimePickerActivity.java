package com.example.valentinbemetz.orccalculator;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import static java.lang.Thread.sleep;

public class TimePickerActivity extends AppCompatActivity {

    private static TextInputLayout teHours, teMinutes, teSeconds;
    private static Button btnSave;
    int hourCount, minuteCount, secondCount;
    String hourString, minuteString, secondString;
    LinearLayout dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        dummy = (LinearLayout) findViewById(R.id.dummy_id);

        teHours = (TextInputLayout) findViewById(R.id.tv_hours);
        teMinutes = (TextInputLayout) findViewById(R.id.tv_minutes);
        teSeconds = (TextInputLayout) findViewById(R.id.tv_seconds);
        btnSave = (Button) findViewById(R.id.btn_saveTie);

        teHours.getEditText().setCursorVisible(false);
        teMinutes.getEditText().setCursorVisible(false);
        teSeconds.getEditText().setCursorVisible(false);

        hourCount = 0;
        minuteCount = 0;
        secondCount = 0;


        teHours.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hourCount =0;
            }
        });

        teMinutes.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                minuteCount = 0;
            }
        });

        teSeconds.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                secondCount = 0;
            }
        });

        teHours.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(hourCount == 0){
                    hourString = String.valueOf(s.charAt(start));
                }
                if(hourCount == 2){
                    hourString = hourString + String.valueOf(s.charAt(start));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                hourCount ++;
                if(hourCount==1){
                    teHours.getEditText().setText("0"+hourString);
                }
                if(hourCount == 3){
                    teHours.getEditText().setText(hourString);
                }
                if(hourCount == 4){
                    teMinutes.requestFocus();
                }
            }
        });






        teMinutes.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(minuteCount == 0){
                    minuteString = String.valueOf(s.charAt(start));
                }
                if(minuteCount == 2){
                    minuteString = minuteString + String.valueOf(s.charAt(start));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                minuteCount ++;
                if(minuteCount==1){
                    teMinutes.getEditText().setText("0"+minuteString);
                }
                if(minuteCount == 3){
                    teMinutes.getEditText().setText(minuteString);
                }
                if(minuteCount == 4){
                    teSeconds.requestFocus();
                }
            }
        });

        teSeconds.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(secondCount == 0){
                    secondString = String.valueOf(s.charAt(start));
                }
                if(secondCount == 2){
                    secondString = secondString + String.valueOf(s.charAt(start));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                secondCount ++;
                if(secondCount==1){
                    teSeconds.getEditText().setText("0"+secondString);
                }
                if(secondCount == 3){
                    teSeconds.getEditText().setText(secondString);
                }
                if(secondCount == 4){
                    dummy.requestFocus();
                    View view = TimePickerActivity.this.getCurrentFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                }
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int hours, minutes, seconds, time;
                hours = Integer.parseInt(teHours.getEditText().getText().toString());
                minutes = Integer.parseInt(teMinutes.getEditText().getText().toString());
                seconds = Integer.parseInt(teSeconds.getEditText().getText().toString());
                time = hours * 3600 + minutes * 60 + seconds;
                intent.putExtra("time", time);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}
