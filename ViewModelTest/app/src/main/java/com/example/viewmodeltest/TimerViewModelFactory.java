package com.example.viewmodeltest;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TimerViewModelFactory implements ViewModelProvider.Factory {
    private int currentSecond;

    public TimerViewModelFactory(int currentSecond) {
        this.currentSecond = currentSecond;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TimerViewModel(currentSecond);
    }
}
