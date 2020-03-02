package com.example.myapplication.ui.events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.events.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author pricejoshua
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            EditText startDateText = (EditText) (getView().findViewById(R.id.dateText));
            EditText name = (EditText) (getView().findViewById(R.id.nameText));
            EditText time = (EditText) (getView().findViewById(R.id.timeText));

            //With help from https://stackoverflow.com/questions/17674308/date-from-edittext/30924811

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date myDate = null;
            try {
                myDate = df.parse(startDateText.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            Calendar c = Calendar.getInstance();
            c.setTime(myDate);



        }
    }
}
