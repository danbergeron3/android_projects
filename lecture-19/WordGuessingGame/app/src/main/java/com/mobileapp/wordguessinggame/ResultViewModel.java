package com.mobileapp.wordguessinggame;

import androidx.lifecycle.ViewModel;
public class ResultViewModel extends ViewModel {
    final String result;
    final String word;

    ResultViewModel(String finalResult, String finalWord) {
        result = finalResult;
        word = finalWord;
    }
}