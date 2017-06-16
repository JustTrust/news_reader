package com.belichenko.a.news.data_flow;


import com.belichenko.a.news.models.News;
import com.belichenko.a.news.utils.NewsGenerator;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class ServerApi implements Server {

    NewsGenerator newsGenerator;

    public ServerApi(NewsGenerator newsGenerator) {
        this.newsGenerator = newsGenerator;
    }

    @Override
    public Single<List<News>> getNews() {
        return newsGenerator.getListOfNewsFromServerWithDelay();
    }
}
