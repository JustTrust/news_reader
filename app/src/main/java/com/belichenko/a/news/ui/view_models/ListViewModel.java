package com.belichenko.a.news.ui.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.belichenko.a.news.NewsApplication;
import com.belichenko.a.news.data_flow.DataManager;
import com.belichenko.a.news.models.LocalNews;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class ListViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    @Inject
    DataManager dataManager;
    private MutableLiveData<List<LocalNews>> newsList;
    private MutableLiveData<String> error;

    public ListViewModel() {
        NewsApplication.getAppComponent().inject(this);
        dataManager.getNewsFromServer();
    }

    public LiveData<List<LocalNews>> getNewsList() {
        if (newsList == null) {
            newsList = new MutableLiveData<>();
            loadNews();
        }
        return newsList;
    }

    public LiveData<String> getErrors() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    private void loadNews() {
        disposables.add(dataManager.loadNewsList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(localNews -> newsList.postValue(localNews),
                        throwable -> error.postValue(throwable.getMessage())));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
