package com.chaos.busraj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ravi on 28/3/18.
 */

public class StandSelector extends AppCompatActivity {

    String standName;
    StandAdapter standAdapter;
    Button standSelectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_selector);

        ArrayList<String> standNames = new ArrayList<String>();

        standNames.add("MI ROAD");
        standNames.add("MNIT");
        standNames.add("GOLCHA");
        standNames.add("TRITON");
        standNames.add("TONK PHATAK");
        standNames.add("BRAHMAPURI");



        standAdapter = new StandAdapter(this, standNames);
        ListView listView = (ListView) findViewById(R.id.src);
        listView.setAdapter(standAdapter);

        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.standSearch);
        textView.setThreshold(1);
        textView.setDropDownHeight(0);
        textView.setAdapter(standAdapter);

        standSelectButton = (Button) findViewById(R.id.standSelectButton);
        standSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                standName = standAdapter.getStandName();
                Intent intent = new Intent();
                intent.putExtra("Stand", standName);
                if(null == standName) {
                    Toast.makeText(StandSelector.this,"Not a valid input",Toast.LENGTH_SHORT).show();
                } else {
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
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
