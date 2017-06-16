package com.belichenko.a.news.data_flow;

import com.belichenko.a.news.data_flow.data_base.NewsDatabase;
import com.belichenko.a.news.models.LocalNews;
import com.belichenko.a.news.utils.Mapping;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NewsDataManager implements DataManager {

    private Server serverApi;
    private NewsDatabase database;

    public NewsDataManager(Server serverApi, NewsDatabase database) {
        this.serverApi = serverApi;
        this.database = database;
    }

    @Override
    public void getNewsFromServer() {
        serverApi.getNews()
                .subscribeOn(Schedulers.computation())
                .map(Mapping.getNewsMapper())
                .subscribe(news -> updateNews(news), throwable -> {
                });
    }

    @Override
    public Flowable<List<LocalNews>> loadNewsList() {
        return database.newsDao().getNewsList();
    }

    private void updateNews(List<LocalNews> localNews) {
        Timber.d("Load news from sever = " + localNews.size());
        database.newsDao().insertNews(localNews.toArray(new LocalNews[]{}));
    }
}
