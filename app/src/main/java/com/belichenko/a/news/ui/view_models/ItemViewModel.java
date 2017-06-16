package com.belichenko.a.news.ui.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.belichenko.a.news.NewsApplication;
import com.belichenko.a.news.data_flow.DataManager;
import com.belichenko.a.news.models.LocalNews;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class ItemViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    @Inject
    DataManager dataManager;
    private MutableLiveData<LocalNews> oneNews;
    private MutableLiveData<String> error;

    public ItemViewModel() {
        NewsApplication.getAppComponent().inject(this);
    }

    public LiveData<LocalNews> getOneNews(int nwesId) {
        if (oneNews == null) {
            oneNews = new MutableLiveData<>();
            loadNews(nwesId);
        }
        return oneNews;
    }

    public LiveData<String> getErrors() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    private void loadNews(int newsId) {
        disposables.add(dataManager.loadOneNews(newsId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(localNews -> oneNews.postValue(localNews),
                        throwable -> error.postValue(throwable.getMessage())));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
