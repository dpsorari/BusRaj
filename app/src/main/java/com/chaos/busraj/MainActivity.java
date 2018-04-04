package com.chaos.busraj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chaos.busraj.BusInfo.StandSelector;

public class MainActivity extends AppCompatActivity {


    TextView src, dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        src= (TextView) findViewById(R.id.src);
        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showroute = new Intent(MainActivity.this, StandSelector.class);
                startActivityForResult(showroute,1);
            }
        });

       dest = (TextView) findViewById(R.id.dest);
        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showroute = new Intent(MainActivity.this , StandSelector.class );
                startActivityForResult(showroute,2);
            }
        });


        TextView search = (TextView) findViewById(R.id.search);
        search. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent path = new Intent(MainActivity.this, com.chaos.busraj.RouteDetails.search.class);
                startActivity(path);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                src.setText(data.getStringExtra("Stand"));
            }
        } else {
            if (resultCode == RESULT_OK) {
                dest.setText(data.getStringExtra("Stand"));
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
