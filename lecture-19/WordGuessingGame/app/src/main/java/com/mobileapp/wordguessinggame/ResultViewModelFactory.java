package com.mobileapp.wordguessinggame;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ResultViewModelFactory implements
        ViewModelProvider.Factory {

    private final String finalResult;
    private final String finalWord;
    //final means that the value of the variable can't be changed after it has been set
    public ResultViewModelFactory(String finalResult, String finalWord) {
        this.finalResult = finalResult;
        this.finalWord = finalWord;
    }
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T>
                                                  modelClass) {
        if (modelClass == ResultViewModel.class) {
            return (T) new ResultViewModel(finalResult, finalWord);
        }
        return null;
    }
}
