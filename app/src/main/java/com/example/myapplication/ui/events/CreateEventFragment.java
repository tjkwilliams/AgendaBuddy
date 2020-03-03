package com.example.myapplication.ui.events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myapplication.MasterCalendar;
import com.example.myapplication.R;
import com.example.myapplication.events.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author pricejoshua
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEventFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int priority;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEventFragment newInstance(String param1, String param2) {
        CreateEventFragment fragment = new CreateEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //https://developer.android.com/guide/topics/ui/controls/spinner#Populate

        Spinner spinner = (Spinner) getView().findViewById(R.id.prioritySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_event, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.createButton){
            EditText nameText = (EditText) (getView().findViewById(R.id.nameText));
            DatePicker startDatePicker = (DatePicker) (getView().findViewById(R.id.startDatePicker));
            TimePicker startTime = (TimePicker) (getView().findViewById(R.id.startTimePicker));
            DatePicker endDatePicker = (DatePicker) (getView().findViewById(R.id.endDatePicker));
            TimePicker endTime = (TimePicker) (getView().findViewById(R.id.endTimePicker));
            Button createButton = (Button) (getView().findViewById(R.id.createButton));

            String name = nameText.getText().toString();





            //events.add(new Event(name, , , priority));

        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        priority = pos;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /**
     * Pulled up method
     *
     * @param date a string representation of the date
     * @return A Date object based on that date
     */
	private Date getDate(String date){

		//With help from https://stackoverflow.com/questions/17674308/date-from-edittext/30924811

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date toReturn = null;
		try {
			toReturn = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
