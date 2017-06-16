package com.belichenko.a.news.ui.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.belichenko.a.news.NewsApplication;
import com.belichenko.a.news.data_flow.DataManager;
import com.belichenko.a.news.models.LocalNews;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class ListViewModel extends ViewModel {

    @Inject
    DataManager dataManager;
    private MutableLiveData<List<LocalNews>> newsList;

    public ListViewModel() {
        NewsApplication.getAppComponent().inject(this);
    }

    public LiveData<List<LocalNews>> getNewsList() {
        if (newsList == null) {
            newsList = new MutableLiveData<>();
            loadUsers();
        }
        return newsList;
    }

    private void loadUsers() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
