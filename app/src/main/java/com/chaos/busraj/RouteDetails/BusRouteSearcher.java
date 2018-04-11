package com.chaos.busraj.RouteDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.busraj.BusInfo.StandSelector;
import com.chaos.busraj.R;

/**
 * Created by ravi on 7/4/18.
 */

public class BusRouteSearcher extends AppCompatActivity{
    private static final int SOURCE_CODE = 1;
    private static final int DESTINATION_CODE = 2;

    TextView source, destination, search;
    String sourceValue, destinationValue;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        savedInstanceState = new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_route_searcher);

        source = findViewById(R.id.source);

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectSource = new Intent(BusRouteSearcher.this, StandSelector.class);
                startActivityForResult(selectSource, SOURCE_CODE);
            }
        });

        destination = (TextView) findViewById(R.id.destination);
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectDestination = new Intent(BusRouteSearcher.this, StandSelector.class);
                startActivityForResult(selectDestination, DESTINATION_CODE);
            }
        });


        search = (TextView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(sourceValue) || TextUtils.isEmpty(destinationValue)) {
                    Toast.makeText(BusRouteSearcher.this, "Please choose source or destination", Toast.LENGTH_LONG).show();
                } else {
                    Intent showRoute = new Intent(BusRouteSearcher.this, com.chaos.busraj.RouteDetails.search.class);
                    showRoute.putExtra(getString(R.string.source), sourceValue);
                    showRoute.putExtra(getString(R.string.destination), destinationValue);
                    startActivity(showRoute);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (SOURCE_CODE == requestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                sourceValue = data.getStringExtra("Stand");
                source.setText(sourceValue);
            }
        } else if(DESTINATION_CODE == requestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                destinationValue = data.getStringExtra("Stand");
                destination.setText(destinationValue);
            }
        }

    }
}
