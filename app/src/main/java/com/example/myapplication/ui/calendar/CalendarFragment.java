package com.example.myapplication.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.events.Event;
import com.example.myapplication.ui.account.AccountMaster;
import com.example.myapplication.ui.events.CreateEventFragment;

import java.util.ArrayList;

public class CalendarFragment extends Fragment implements  View.OnClickListener{

    private CalendarViewModel calendarViewModel;

    private CalendarView calendarView;

    private TextView accountName, myDate;

    private static ArrayList<Event> events = new ArrayList<>();

    private Button newEventButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_master_calendar, container, false);
        /*final TextView textView = root.findViewById(R.id.text_calendar);
        calendarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */


        myDate = (TextView) root.findViewById(R.id.myDateText);
        myDate.setOnClickListener(this);


        try {

            //The problem is here.
            calendarView = (CalendarView) root.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    String date = "[ " + (1+month) + " / " + dayOfMonth + " / " + year + " ]";
                    myDate.setText(date);

                }
            });

            Toast.makeText(this.getContext(), "Calendar succeeded.", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(this.getContext(), e.toString() +"1Calendar FAILED.", Toast.LENGTH_SHORT).show();
        }

        newEventButton = (Button) root.findViewById(R.id.newEventBtn);
        newEventButton.setOnClickListener(this);

        return root;
    }

    /**
     * Adds an new event to a calendar.
     *
     * @param event The event to add.
     */
    public static void addEvent(Event event){

        // Put the event on the calendar.


    }

    /**
     * Removes an event from a calendar.
     *
     * @param e The event to remove.
     */
    public static void removeEvent(Event e){

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.newEventBtn:
                try {
                    // TODO: send Month and day in newInstance arguments
                    Fragment fragment = CreateEventFragment.newInstance("", "");
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.createEventFragment, fragment);
                } catch(Exception e) {
                    Toast.makeText(this.getContext(), "New Event needs to be started.", Toast.LENGTH_SHORT).show();
                }
/*
                // Create an instance of Fragment1
                Fragment firstFragment = new Fragment();

                // In case this activity was started with special instructions from an Intent,
                // pass the Intent's extras to the fragment as arguments
                firstFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();*/
            break;

            case R.id.myDateText:

                //Toast.makeText(this.getContext(), "Date was clicked.", Toast.LENGTH_SHORT).show();

                break;



            default:
                //Toast.makeText(this.getContext(), "Nothing was clicked.", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}