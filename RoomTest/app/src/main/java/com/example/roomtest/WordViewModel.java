package com.example.roomtest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository wordRepository;

    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    void insertWords(Word... words){
        wordRepository.insertWords(words);
    }
    void updataWords(Word... words){
        wordRepository.updataWords(words);
    }
    void DeleteWords(Word... words){
        wordRepository.DeleteWords(words);
    }
    void DeleteAllWords(){
        wordRepository.DeleteAllWords();
    }

}
