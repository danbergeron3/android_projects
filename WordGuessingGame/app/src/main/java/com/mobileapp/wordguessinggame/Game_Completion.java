package com.mobileapp.wordguessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobileapp.wordguessinggame.databinding.FragmentGameCompletionBinding;

public class Game_Completion extends Fragment {
    FragmentGameCompletionBinding binding;
    String message, word;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding =  FragmentGameCompletionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        if(savedInstanceState == null) {
            message = Game_CompletionArgs.fromBundle(getArguments()).getWinLose();
            word = Game_CompletionArgs.fromBundle(getArguments()).getWord();
        } else {
            message = savedInstanceState.getString("message");
            word = savedInstanceState.getString("word");
        }
        Button newGame = binding.btnNewGame;
        TextView display1 = binding.textView4;
        display1.setText(message);
        TextView display2 = binding.textView5;
        display2.setText(word);


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(Game_CompletionDirections
                        .actionGameCompletionToGame());
            }
        });


        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("message", message);
        savedInstanceState.putString("word", word);
        super.onSaveInstanceState(savedInstanceState);
    }
}