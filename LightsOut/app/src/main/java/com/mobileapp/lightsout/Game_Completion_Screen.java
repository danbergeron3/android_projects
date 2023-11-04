package com.mobileapp.lightsout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Game_Completion_Screen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_completion_screen,
                container, false);

        // get argument passed by game_board_screen, set textview id: totalMoves to argument
        String moves = Game_Completion_ScreenArgs.fromBundle(requireArguments()).getMessage();
        TextView text = view.findViewById(R.id.totalMoves);
        text.setText(moves);

        return view;
    }
}

