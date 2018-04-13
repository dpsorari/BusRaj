package com.chaos.busraj.BusInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.chaos.busraj.DataFetch.QueryFetch;
import com.chaos.busraj.R;

import java.util.ArrayList;

import static com.chaos.busraj.Constants.Constants.CITY_TEXT_VIEW_CODE;
import static com.chaos.busraj.Constants.Constants.INTER_CITY_CODE;
import static com.chaos.busraj.Constants.Constants.INTRA_CITY_CODE;

/**
 * Created by ravi on 28/3/18.
 */

public class OptionSelector extends AppCompatActivity {

    //Option Value in the given list and used city name to specify the stands in that given city
    String optionName, cityName;
    // Custom adapter class object
    OptionAdapter optionAdapter;
    // Used to pass selected option to previous activity
    Button optionSelectButton;
    //Used to identify the calling activity
    int activityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_selector);

        //getting the given list of options
        ArrayList<String> optionNames = getOptionNames();

        //setting the custom option adapter
        optionAdapter = new OptionAdapter(this, optionNames);
        ListView listView = (ListView) findViewById(R.id.options);
        listView.setAdapter(optionAdapter);

        //setting the custom option adapter with autocomplete text view
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.optionSearch);
        textView.setThreshold(1);
        textView.setDropDownHeight(0);
        textView.setAdapter(optionAdapter);


        //Listener on option select button
        optionSelectButton = (Button) findViewById(R.id.optionSelectButton);
        //listView.addFooterView(optionSelectButton);
        optionSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionName = optionAdapter.getOptionName();
                Intent intent = new Intent();
                intent.putExtra("optionName", optionName);
                if(null == optionName) {
                    Toast.makeText(OptionSelector.this,"Not a valid input",Toast.LENGTH_SHORT).show();
                } else {
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    //Used to return all the optons in the list
    private ArrayList getOptionNames() {
        Intent intent = getIntent();
        int activityCode = Integer.parseInt(intent.getStringExtra("activityCode"));
        ArrayList<String> optionNames = null;
        if(INTRA_CITY_CODE == activityCode) {
            cityName = intent.getStringExtra("cityName");
            optionNames = QueryFetch.getAllStops(cityName);
        } else if(INTER_CITY_CODE == activityCode || CITY_TEXT_VIEW_CODE == activityCode){
            optionNames = QueryFetch.getCitiesOfState("Rajasthan");
        }
        return optionNames;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_source, menu);
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
}
