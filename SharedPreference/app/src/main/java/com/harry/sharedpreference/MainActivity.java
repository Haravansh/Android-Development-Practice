package com.harry.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredText = editText.getText().toString();
                DisplayAndSaveText(enteredText);

            }
        });


    }

    private void DisplayAndSaveText(String enteredText) {
         //Display the text
        textView.setText(enteredText);

        // saving the text into shared pref
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        //writing data to shared pref
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", enteredText);
        editor.commit();



    }
}