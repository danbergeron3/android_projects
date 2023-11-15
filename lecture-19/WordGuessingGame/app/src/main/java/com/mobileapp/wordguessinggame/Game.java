package com.mobileapp.wordguessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobileapp.wordguessinggame.databinding.FragmentGameBinding;

public class Game extends Fragment {
    private GameViewModel gameViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        com.mobileapp.wordguessinggame.databinding.FragmentGameBinding binding
                = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        TextView displayText = binding.textView;
        displayText.setText(gameViewModel.getDisplayText());
        TextView guessedLetters = binding.textView3;
        TextView guessesLeft = binding.textView2;
        Button Guess = binding.button;
        EditText input = binding.editTextText;



        Guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(input.length() == 0) {
                    return;
                }

                // parse input
                String inputStr = input.getText().toString();
                String guess = inputStr.substring(0,1);

                Log.d("INPUT_DEBUG", "Guess Letter: " + guess + " ");
                if(!gameViewModel.wasGuessed(guess)) {
                    gameViewModel.enterNewGuess(guess);
                    String tempStr = "Incorrect Guesses: " + gameViewModel.getLettersGuessed();
                    guessedLetters.setText(tempStr);
                    displayText.setText(gameViewModel.getDisplayText());
                    tempStr = "You Have " + gameViewModel.getGuessesLeft() + " guesses left.";
                    guessesLeft.setText(tempStr);
                }
                if(gameViewModel.isGameOver()) {
                    String message;
                    if(gameViewModel.isWonGame()) {
                        message = "You Won!\nThe Word Was";
                    } else {
                         message = "Sorry, You Lost.\nThe Word Was";
                    }
                    GameDirections.ActionGameToGameCompletion action =
                            GameDirections.actionGameToGameCompletion(message, gameViewModel.getWord());
                    Navigation.findNavController(view).navigate(action);
                }
            }
        });
        return view;
    }

}