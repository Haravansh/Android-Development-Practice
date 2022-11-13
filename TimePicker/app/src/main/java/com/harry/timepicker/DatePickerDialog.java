package com.harry.timepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener
{


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Getting the current date and set it as default in the picker
        final Calendar c= Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new android.app.DatePickerDialog(getActivity(), this, year, month,day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // do something with the date chosen by the user

        Toast.makeText(getActivity(), "Day "+dayOfMonth + "\nMonth: "+month
                + "\nYear: "+year, Toast.LENGTH_SHORT).show();
    }
}

