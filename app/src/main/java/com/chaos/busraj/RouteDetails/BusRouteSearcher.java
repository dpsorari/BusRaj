package com.chaos.busraj.RouteDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.busraj.BusInfo.OptionSelector;
import com.chaos.busraj.R;

import static com.chaos.busraj.Constants.Constants.DESTINATION_TEXT_VIEW_CODE;
import static com.chaos.busraj.Constants.Constants.INTRA_CITY_CODE;
import static com.chaos.busraj.Constants.Constants.SOURCE_TEXT_VIEW_CODE;

/**
 * Created by ravi on 7/4/18.
 */

public class BusRouteSearcher extends AppCompatActivity{
    private static final int SOURCE_CODE = 1;
    private static final int DESTINATION_CODE = 2;

    TextView source, destination, search;
    String sourceValue, destinationValue, cityValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState = new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_route_searcher);

        //Used to get activity code for calling activity
        final int activityCode = getActivityCode();

        //Used to get city name
        final String cityName = getCityName();

        //Used to set listener on source text view
        setListenerOnSourceTextView(activityCode, cityName);

        //Used to set listener on destination text view
        setListenerOnDestinationTextView(activityCode, cityName);

        //Used to set listener on search text view
        setListenerOnSearchTextView(activityCode, cityName);
    }

    //Used to set listener on source text view
    private void setListenerOnSourceTextView(final int activityCode, @NonNull final String cityName) {
        source = findViewById(R.id.source);
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectSource = new Intent(BusRouteSearcher.this, OptionSelector.class);
                selectSource.putExtra("activityCode",activityCode+"");
                if(INTRA_CITY_CODE == activityCode) {
                    selectSource.putExtra("cityName",cityName+"");
                }
                startActivityForResult(selectSource, SOURCE_TEXT_VIEW_CODE);
            }
        });
    }

    //Used to set listener on destination text view
    private void setListenerOnDestinationTextView(final int activityCode, @NonNull final String cityName) {
        destination = (TextView) findViewById(R.id.destination);
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectDestination = new Intent(BusRouteSearcher.this, OptionSelector.class);
                selectDestination.putExtra("activityCode",activityCode+"");
                if(INTRA_CITY_CODE == activityCode) {
                    selectDestination.putExtra("cityName",cityName+"");
                }
                startActivityForResult(selectDestination, DESTINATION_TEXT_VIEW_CODE);
            }
        });
    }

    //Used to set listener on search text view
    private void setListenerOnSearchTextView(final int activityCode, @NonNull final String cityName) {
        search = (TextView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(sourceValue) || TextUtils.isEmpty(destinationValue)) {
                    Toast.makeText(BusRouteSearcher.this, "Please choose source or destination", Toast.LENGTH_LONG).show();
                } else {
                    Intent showRoute = new Intent(BusRouteSearcher.this, com.chaos.busraj.RouteDetails.search.class);
                    showRoute.putExtra("activityCode",activityCode+"");
                    showRoute.putExtra(getString(R.string.source), sourceValue);
                    showRoute.putExtra(getString(R.string.destination), destinationValue);
                    if(INTRA_CITY_CODE == activityCode) {
                        showRoute.putExtra("cityName",cityName+"");
                    }
                    startActivity(showRoute);
                }
            }
        });
    }

    //Used to get activity code for calling activity
    private int getActivityCode() {
        Intent intent = getIntent();
        int activityCode = Integer.parseInt(intent.getStringExtra("activityCode"));
        return activityCode;
    }

    //Used to get city name
    private String getCityName() {
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        return cityName;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (SOURCE_TEXT_VIEW_CODE == requestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                sourceValue = data.getStringExtra("optionName");
                source.setText(sourceValue);
            }
        } else if(DESTINATION_TEXT_VIEW_CODE == requestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                destinationValue = data.getStringExtra("optionName");
                destination.setText(destinationValue);
            }
        }

    }
}
