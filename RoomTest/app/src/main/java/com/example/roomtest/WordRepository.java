package com.example.roomtest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//仓库
public class WordRepository {

    private LiveData<List<Word>>allWordsLive;
    private WordDao wordDao;

    public WordRepository(Context context){
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordLive();
    }

    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void updataWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void DeleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }
    void DeleteAllWords(Word... words){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    public LiveData<List<Word>> getAllWordsLive(){
        return allWordsLive;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        InsertAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords((words));
            return null;
        }
    }


    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao wordDao;

        UpdateAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords((words));
            return null;
        }
    }


    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao wordDao;

        DeleteAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deletWord((words));
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordDao wordDao;

        DeleteAllAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }


}
