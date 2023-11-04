package com.mobileapp.lightsout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class Color_Settings_Screen extends Fragment {

    private int color = R.color.current_color;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_color_settings_screen,
                container, false);

        // get id and to set on click listeners
        Button button = view.findViewById(R.id.buttonChange);
        RadioButton radio1 = view.findViewById(R.id.radioButton);
        RadioButton radio2 = view.findViewById(R.id.radioButton2);
        RadioButton radio3 = view.findViewById(R.id.radioButton3);

        // button listener controls navigation away from the page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color_Settings_ScreenDirections.ActionColorSettingsScreenToGameBoardScreen action =
                        Color_Settings_ScreenDirections.actionColorSettingsScreenToGameBoardScreen()
                                .setColor(color);
                Navigation.findNavController(view).navigate(action);
            }
        });

        // radio listeners will modify colors values, values are sent when change button is pressed
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = R.color.red;
            }
        });
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = R.color.yellow;
            }
        });
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = R.color.amber;
            }
        });

        return view;
    }
}