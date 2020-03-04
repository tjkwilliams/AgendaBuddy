package com.example.myapplication.ui.calendar;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.ui.account.AccountMaster;

import org.w3c.dom.Text;

public class CalendarFragment extends Fragment implements  View.OnClickListener{

    private CalendarViewModel calendarViewModel;

    private CalendarView calendarView;

    private TextView accountName, datePicked, myDate;

    private Button newEventButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.activity_master_calendar, container, false);
        /*final TextView textView = root.findViewById(R.id.text_calendar);
        calendarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */


        myDate = (TextView) root.findViewById(R.id.myDate);
        myDate.setOnClickListener(this);

        try {

            //The problem is here.
            calendarView = (CalendarView) root.findViewById(R.id.calendarView);

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    String date = "[ " + dayOfMonth + "/" + month + "/" + year + "]";

                    myDate.setText(date);

                }
            });

            Toast.makeText(this.getContext(), "Calendar succeeded.", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(this.getContext(), "Calendar FAILED.", Toast.LENGTH_SHORT).show();
        }

        newEventButton = (Button) root.findViewById(R.id.newEventBtn);
        datePicked = (TextView) root.findViewById(R.id.myDate);

        datePicked.setOnClickListener(this);
        newEventButton.setOnClickListener(this);

        try{

            accountName = (TextView) root.findViewById(R.id.accountName);
            accountName.setText(AccountMaster.username());



        } catch(Exception e){
            Toast.makeText(this.getContext(), "Calendar FAILED.", Toast.LENGTH_SHORT).show();
        }
        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.newEventBtn:
                Toast.makeText(this.getContext(), "New Event needs to be started.", Toast.LENGTH_SHORT).show();
            break;

            case R.id.myDate:

                Toast.makeText(this.getContext(), "Date was clicked.", Toast.LENGTH_SHORT).show();

                break;



            default:
                Toast.makeText(this.getContext(), "Nothing was clicked.", Toast.LENGTH_SHORT).show();



        }

    }
}