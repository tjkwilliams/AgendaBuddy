package com.example.myapplication.ui.events;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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

import com.example.myapplication.R;
import com.example.myapplication.connect.JSONParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    EditText nameText;
    DatePicker startDatePicker;
    TimePicker startTime;
    DatePicker endDatePicker;
    TimePicker endTime;

    String date;
    String startTimeStr;
    String endTimeStr;
    String location;


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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.createButton){
            nameText = (EditText) (getView().findViewById(R.id.nameText));
            startDatePicker = (DatePicker) (getView().findViewById(R.id.startDatePicker));
            startTime = (TimePicker) (getView().findViewById(R.id.startTimePicker));
            endDatePicker = (DatePicker) (getView().findViewById(R.id.endDatePicker));
            endTime = (TimePicker) (getView().findViewById(R.id.endTimePicker));

            String name = nameText.getText().toString();

            date = "" + startDatePicker.getYear() + "-" + startDatePicker.getMonth() + "-" +  startDatePicker.getDayOfMonth();
            startTimeStr = "" + startTime.getHour() + startTime.getMinute();
            endTimeStr = "" + endTime.getHour() + endTime.getMinute();


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


    JSONParser jsonParser = new JSONParser();
    EditText o;
    EditText inputPrice;
    EditText inputDesc;

    // url to create new product
    private static String url_create_product = "http://18.233.165.117/event-connect/create_event.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    Context toast = this.getContext();

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewEvent extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String title = nameText.getText().toString();
            String price;
            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair("startTime", startTimeStr));
            params.add(new BasicNameValuePair("endTime", endTimeStr));
            params.add(new BasicNameValuePair("date", date));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Toast.makeText(toast,"Success",Toast.LENGTH_SHORT).show();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }
}