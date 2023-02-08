package com.example.roomtest02;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import java.util.List;

public class PersonRepository {

    private LiveData<List<Person>> allPersonsLive;
    private PersonDao personDao;

    public PersonRepository(Context context) {
        PersonDatabase personDatabase = PersonDatabase.getINSTANCE(context.getApplicationContext());
        personDao = personDatabase.getPersonDao();
        allPersonsLive = personDao.getAllperson();
    }

    void insertPerson(Person...persons){
        new InsertAsyncTask(personDao).execute(persons);
    }


    public LiveData<List<Person>> getAllWordsLive(){
        return allPersonsLive;
    }

    static class InsertAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        InsertAsyncTask(PersonDao personDao){
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person...persons) {
            personDao.insert(persons);
            return null;
        }
    }


}
