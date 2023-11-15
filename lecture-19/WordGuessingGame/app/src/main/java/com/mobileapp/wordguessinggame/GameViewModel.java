package com.mobileapp.wordguessinggame;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {
    private final String word = "test";
    private String displayText = "____";
    private String lettersGuessed = " ";
    private int guessesLeft = 8;
    private boolean isGameOver = false;
    private boolean wonGame = false;

    // assumes input is vetted
    // updates internal strings
    public void enterNewGuess(String guess) {
        StringBuilder tempText = new StringBuilder();
        tempText.append(displayText);
        boolean letterFoundInString = false;
        for (int i = 1; i <= word.length(); i++) {
            if (word.substring(i - 1, i).equals(guess)) {
                Log.d("ENTERGUESS_DEBUG", "Found Guess:" + guess);
                tempText.replace(i-1, i, guess);
                letterFoundInString = true;
            }
        }

        if (word.equals(tempText.toString())) {
            Log.d("ENTERGUESS_DEBUG", "Win Condition hit.");
            wonGame = true;
            isGameOver = true;
        }

        displayText = tempText.toString();
        Log.d("ENTERGUESS_DEBUG", "Display Text letter: " + displayText);
        if(!letterFoundInString) {
            lettersGuessed = lettersGuessed + guess + " ";
        }
        guessesLeft--;
        if(guessesLeft <= 0) {
            Log.d("ENTERGUESS_DEBUG", "Lose Condition hit.");
            wonGame = false;
            isGameOver = true;
            guessesLeft = 0;
        }
    }

    // getters
    public boolean isWonGame() { return wonGame; }
    public String getDisplayText() {return displayText;}

    public String getWord() {return word;}
    public boolean isGameOver() {
        return isGameOver;
    }

    public int getGuessesLeft() {return guessesLeft;}

    public String getLettersGuessed() {return lettersGuessed; }


    // returns true if letter was guessed
    // false otherwise
    public boolean wasGuessed(String guess) {
        if((lettersGuessed.contains(guess)) || displayText.contains((guess))) {
            Log.d("ENTERGUESS_DEBUG", "Detected letter: " + guess);
            return true;
        }
        return false;
    }
}
