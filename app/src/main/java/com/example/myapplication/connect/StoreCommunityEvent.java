package com.example.myapplication.connect;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.*;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class StoreCommunityEvent{

    JSONParser jsonParser = new JSONParser();
    EditText inputName;
    EditText inputPrice;
    EditText inputDesc;

    // url to create new product
    private static String url_create_product = "http://18.233.165.117/event-connect/db_connect.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    /**
     * Background Async Task to Create new product
     * */
    public class CreateNewEvent extends AsyncTask<String, String, String> {

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
            String name = inputName.getText().toString();
            String price = inputPrice.getText().toString();
            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("price", price));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }



    }
}

