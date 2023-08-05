package com.example.viewmodellearncode;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> userLiveData ;
    private MutableLiveData<Boolean> loadingLiveData;

    public UserViewModel() {
        userLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    //获取用户信息,假装网络请求 2s后 返回用户信息
    public void getUserInfo() {

        loadingLiveData.setValue(true);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                loadingLiveData.setValue(false);
                userLiveData.setValue(s);//抛出用户信息
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String userName = "11111111nihao1111111";
                return userName;
            }
        }.execute();
    }

    public LiveData<String> getUserLiveData() {
        return userLiveData;
    }
    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}

