package com.mobileapp.wordguessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
    Guessing_Game game;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        com.mobileapp.wordguessinggame.databinding.FragmentGameBinding binding
                = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        if (savedInstanceState != null) {
            int guesses = savedInstanceState.getInt("numGuesses");
            String displayText = savedInstanceState.getString("displayText");
            String word = savedInstanceState.getString("hiddenWord");
            String lettersGuessed = savedInstanceState.getString("lettersGuessed");
            game = new Guessing_Game(guesses, word, displayText, lettersGuessed);
        } else {
            game = new Guessing_Game();
        }

        TextView displayText = binding.textView;
        displayText.setText(game.getDisplayText());
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
                if(!game.wasGuessed(guess)) {
                    game.enterNewGuess(guess);
                    String tempStr = "Incorrect Guesses: " + game.getLettersGuessed();
                    guessedLetters.setText(tempStr);
                    displayText.setText(game.getDisplayText());
                    tempStr = "You Have " + game.getGuessesLeft() + " guesses left.";
                    guessesLeft.setText(tempStr);
                }
                if(game.isGameOver()) {
                    String message ;
                    if(game.isWonGame()) {
                        message = "You Won!\nThe Word Was";
                    } else {
                         message = "Sorry, You Lost.\nThe Word Was";
                    }
                    GameDirections.ActionGameToGameCompletion action =
                            GameDirections.actionGameToGameCompletion(message, game.getWord());
                    Navigation.findNavController(view).navigate(action);
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        int guesses = game.getGuessesLeft();
        String displayText = game.getDisplayText();
        String lettersGuessed = game.getLettersGuessed();
        String word = game.getWord();

        savedInstanceState.putString("displayText", displayText);
        savedInstanceState.putString("lettersGuessed", lettersGuessed);
        savedInstanceState.putString("hiddenWord", word);
        savedInstanceState.putInt("numGuesses", guesses);
        super.onSaveInstanceState(savedInstanceState);
    }
}