package com.harry.timepicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void showTimePickerDialog(View v){
        DialogFragment newFragment = new TimePickerFragment();

        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerDialog();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

}