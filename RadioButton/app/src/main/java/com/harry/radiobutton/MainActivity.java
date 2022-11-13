package com.harry.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RadioGroup radioGroup = findViewById(R.id.radioGRP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RadioButton r1 = findViewById(R.id.cheese);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RadioButton r3 = findViewById(R.id.meet);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RadioButton r2 = findViewById(R.id.chic);

    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // check which radio button was clicked
        switch(view.getId()){
            case R.id.cheese:
                if(checked)
                    Toast.makeText(this, "Cheese Burger", Toast.LENGTH_SHORT).show();
                break;
            case R.id.meet:
                if(checked)
                    Toast.makeText(this, "Ordinary Meet Burger", Toast.LENGTH_SHORT).show();
                break;

            case R.id.chic:
                if(checked)
                    Toast.makeText(this, "Chicken Burger", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}