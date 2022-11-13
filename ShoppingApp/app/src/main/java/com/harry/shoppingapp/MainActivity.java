package com.harry.shoppingapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    Button btn;
    EditText editText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference("GoldPrice").child("price");

        //writing data to firebase
        //writing a simple data (no custom object)

        //updating the gold price to 2000 upon launching the app
        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.editTextNumber);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.setValue(editText.getText().toString());

            }
        });


    }
}