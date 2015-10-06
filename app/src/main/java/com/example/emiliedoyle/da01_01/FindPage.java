package com.example.emiliedoyle.da01_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

// Volley imports
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FindPage extends AppCompatActivity {

    // Instantiate the RequestQueue.
    private RequestQueue queue;
    private String url;
    String major;


    public void getDBItems(){
        // This is from the Volley tutorial
        final TextView mTextView = (TextView) findViewById(R.id.db_contents);

        // Request a string response from the provided URL.
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string (items of the DB)
                        mTextView.setText("Database contents: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("Well fuck.");
            }
        });
        // End of tutorial from Volley

        // Add the request to the RequestQueue.
        queue.add(getRequest);

    }


    public void postMajor(){
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //getDBItems();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


            }
        }){
            @Override
        protected Map<String,String> getParams(){

                Log.d("POSTING", "The mapping thing is happening");

                Map<String, String> params = new HashMap<String, String>();
                params.put("major", major);

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_page);

        // Define queue and url
        queue = Volley.newRequestQueue(this);
        url ="http://159.203.68.208:5000/data";

        final Context context = this;
        Button search_results_button = (Button) findViewById(R.id.SearchResultsButton);
        search_results_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try to post the major to the DB
                Log.d("LISTENER", "Stuff can happen here.");
                postMajor();

                Intent search_results_screen = new Intent(context,SearchResultsPage.class);
                startActivity(search_results_screen);
            }
        });

        // Try it here?
        getDBItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_page, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public boolean EE_flag;
    public boolean ME_flag;
    public boolean BE_flag;
    public boolean CE_flag;
    public boolean CM_flag; */

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.EE_box:
                if (checked)
                    major = "Electrical Engineering";
                    //EE_flag = true;
                else
                    major = "unknown";
                    //EE_flag = false;
                break;
            case R.id.ME_box:
                if (checked)
                    major = "Mechanical Engineering";
                    //ME_flag = true;
                else
                    major = "unknown";
                    //ME_flag = false;
                break;
            case R.id.CE_box:
                if (checked)
                    major = "Civil Engineering";
                    //CE_flag = true;
                else
                    major = "unknown";
                    //CE_flag = false;
                break;
            case R.id.BE_box:
                if (checked)
                    major = "Bioengineering";
                    //BE_flag = true;
                else
                    major = "unknown";
                    //BE_flag = false;
                break;
            case R.id.CM_box:
                if (checked)
                    major = "Construction Management";
                    //CM_flag = true;
                else
                    major = "unknown";
                    //CM_flag = false;
                break;
        }
    }
}
