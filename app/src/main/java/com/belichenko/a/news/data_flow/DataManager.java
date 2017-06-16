package com.belichenko.a.news.data_flow;

import com.belichenko.a.news.models.LocalNews;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public interface DataManager {

    void getNewsFromServer();

    Flowable<List<LocalNews>> loadNewsList();

    Flowable<LocalNews> loadOneNews(int newsId);
}
