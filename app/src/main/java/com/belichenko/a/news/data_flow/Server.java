package com.belichenko.a.news.data_flow;

import com.belichenko.a.news.models.News;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

public interface Server {
    Single<List<News>> getNews();
}
