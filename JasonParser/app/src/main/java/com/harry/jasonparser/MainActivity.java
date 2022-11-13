package com.harry.jasonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //widgets
    TextView name, salary;

    // JSON STRING
    String JSON_STRING =
            "{\"employee\":" +
                    "{\"name\":\"Master Coding\"," +
                    "\"salary\":4000}" +
                    "}";

    String namey, salaryy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Reference for textview
        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);


        // Getting JSON Objects
        try {

            // Get JSON Object from json file
            JSONObject obj = new JSONObject(JSON_STRING);

            // FetJSON Object named employee
            JSONObject employee = obj.getJSONObject("employee");

            // Getting Employee name & Salary inside json object (employee)
            namey = employee.getString("name");
            salaryy = employee.getString("salary");


            // Setting textviews
            name.setText("Name:" +namey);
            salary.setText("Salary:" + salaryy);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}