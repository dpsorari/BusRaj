package com.chaos.busraj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.busraj.BusInfo.OptionSelector;
import com.chaos.busraj.RouteDetails.BusRouteSearcher;

import static com.chaos.busraj.Constants.Constants.CITY_TEXT_VIEW_CODE;
import static com.chaos.busraj.Constants.Constants.INTER_CITY_CODE;
import static com.chaos.busraj.Constants.Constants.INTRA_CITY_CODE;

public class MainActivity extends AppCompatActivity {

    //buttons reference for intercity and intracity, imageButton
    private Button intercityButton, intracityButton;
    private ImageButton nextButton;

    //textView reference in main activity
    private TextView cityInfo;

    //text holding city selected
    private String cityVal = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intercityButton = (Button)findViewById(R.id.intercity);
        intracityButton = (Button)findViewById(R.id.intracity);

        cityInfo = (TextView)findViewById(R.id.cityInfo);
        nextButton = (ImageButton)findViewById(R.id.nextButton);

        //set listeners
        intercityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BusRouteSearcher.class);
                intent.putExtra("activityCode",INTER_CITY_CODE+"");
                startActivity(intent);
            }
        });

        intracityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout)cityInfo.getParent();
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        //imageButton listener to redirect to next activity
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if text is set and go to next page if true
                if(!TextUtils.isEmpty(cityVal)) {
                    Intent intent = new Intent(MainActivity.this,BusRouteSearcher.class);
                    intent.putExtra("activityCode",INTRA_CITY_CODE+"");
                    intent.putExtra("cityName" , cityVal);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"Please choose city" , Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        cityInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OptionSelector.class);
                intent.putExtra("activityCode",CITY_TEXT_VIEW_CODE+"");
                startActivityForResult(intent, CITY_TEXT_VIEW_CODE);
            }
        });

    }


    //get the result from standSelector to set textView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CITY_TEXT_VIEW_CODE == requestCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                cityVal = data.getStringExtra("optionName");
                cityInfo.setText(cityVal);
            }
        }
    }
}
