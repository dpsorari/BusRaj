package com.chaos.busraj;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by deepak on 20/3/18.
 */
public class StandAdapter extends ArrayAdapter<String> {

    AutoCompleteTextView textView;

    public StandAdapter(Context context, ArrayList<String> resource) {
        super(context, 0, resource);
    }

    int selectedPosition = 0;
    String stand;
    Button standSelectButton;

    RadioButton standName;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        stand = getItem(position);
        standName = (RadioButton) listItemView.findViewById(R.id.stand);
        standName.setChecked(position == selectedPosition);
        standName.setText(stand);
        standName.setTag(position);
        standName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = (Integer) view.getTag();
                textView = view.getRootView().findViewById(R.id.standSearch);
                notifyDataSetChanged();
                textView.setText(getItem(selectedPosition));
                selectedPosition = 0;
            }
        });

        return listItemView;
    }

    public String getStandName() {
        if (0 == getCount()) {
            return null;
        } else {
            return getItem(selectedPosition);
        }
    }
}
