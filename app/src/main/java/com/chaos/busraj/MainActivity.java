package com.chaos.busraj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.busraj.BusInfo.StandSelector;

public class MainActivity extends AppCompatActivity {

    private static final int SOURCE_CODE = 1;
    private static final int DESTINATION_CODE = 2;

    TextView source, destination, search;
    String sourceValue, destinationValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        source = (TextView) findViewById(R.id.source);
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectSource = new Intent(MainActivity.this, StandSelector.class);
                startActivityForResult(selectSource, SOURCE_CODE);
            }
        });

        destination = (TextView) findViewById(R.id.destination);
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectDestination = new Intent(MainActivity.this, StandSelector.class);
                startActivityForResult(selectDestination, DESTINATION_CODE);
            }
        });


        search = (TextView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(sourceValue) || TextUtils.isEmpty(destinationValue)) {
                    Toast.makeText(MainActivity.this, "Please choose source or destination", Toast.LENGTH_LONG).show();
                } else {
                    Intent showRoute = new Intent(MainActivity.this, com.chaos.busraj.RouteDetails.search.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
