package com.harry.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);


    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        // which checkbox was clicked
        switch(view.getId()){
            case R.id.checkBox:
                if(checked)
                    Toast.makeText(this, "Pizza", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "No Pizza", Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkBox2:
                if(checked)
                    Toast.makeText(this, "Cheese Me", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "Not Cheese", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}