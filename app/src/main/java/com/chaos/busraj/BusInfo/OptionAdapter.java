package com.chaos.busraj.BusInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;

import com.chaos.busraj.R;

import java.util.ArrayList;

/**
 * Created by deepak on 20/3/18.
 */
public class OptionAdapter extends ArrayAdapter<String> {

    //Autocomplete text view to search option in the given list
    AutoCompleteTextView textView;
    //Used to specify the selected option in the given list
    int selectedPosition = 0;
    //Used to store optionValue
    String option;
    //Radio button as a option in UI
    RadioButton optionName;

    public OptionAdapter(Context context, ArrayList<String> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        option = getItem(position);
        optionName = (RadioButton) listItemView.findViewById(R.id.stand);
        optionName.setChecked(position == selectedPosition);
        optionName.setText(option);
        optionName.setTag(position);

        //set listener on every option view in the list
        optionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = (Integer) view.getTag();
                textView = view.getRootView().findViewById(R.id.optionSearch);
                notifyDataSetChanged();
                textView.setText(getItem(selectedPosition));
                selectedPosition = 0;
            }
        });

        return listItemView;
    }

    //Used to get selected option name in the given list
    public String getOptionName() {
        if (0 == getCount()) {
            return null;
        } else {
            return getItem(selectedPosition);
        }
    }
}
