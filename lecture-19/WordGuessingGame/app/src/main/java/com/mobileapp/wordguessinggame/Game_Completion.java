package com.mobileapp.wordguessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
        message = Game_CompletionArgs.fromBundle(getArguments()).getWinLose();
        word = Game_CompletionArgs.fromBundle(getArguments()).getWord();

        ResultViewModelFactory viewModelFactory = new ResultViewModelFactory(message, word);
        ResultViewModel viewModel = new ViewModelProvider(this,
                viewModelFactory).get(ResultViewModel.class);

        Button newGame = binding.btnNewGame;
        TextView display1 = binding.textView4;
        display1.setText(viewModel.result);
        TextView display2 = binding.textView5;
        display2.setText(viewModel.word);


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(Game_CompletionDirections
                        .actionGameCompletionToGame());
            }
        });
        return view;
    }
}