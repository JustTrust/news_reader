package com.belichenko.a.news.data_flow;

import com.belichenko.a.news.models.News;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NewsDataManager implements DataManager {

    Server serverApi;

    public NewsDataManager(Server serverApi) {
        this.serverApi = serverApi;
    }

    @Override
    public void getNewsFromServer() {
        serverApi.getNews()
        .subscribeOn(Schedulers.computation())
        .subscribe(news -> updateNews(news), throwable -> {});
    }

    private void updateNews(List<News> news) {

    }
}
