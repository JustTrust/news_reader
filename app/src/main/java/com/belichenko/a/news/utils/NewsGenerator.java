package com.belichenko.a.news.utils;

import com.belichenko.a.news.models.News;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NewsGenerator {

    @Inject
    public NewsGenerator() {
    }

    public Single<List<News>> getListOfNewsFromServer() {
        ArrayList<News> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                News news = new News(i,
                        "News Title ".concat(String.valueOf(i)),
                        "text",
                        ("Room also verifies all of your queries in Dao classes" +
                                " while the application is being compiled so that" +
                                " if there is a problem in one of the queries, you will " +
                                "be notified instantly").getBytes("UTF-8"));
                list.add(news);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return Single.error(e);
            }
        }
        return Single.just(list);
    }

    public Single<List<News>> getListOfNewsFromServerWithDelay(){
        Single<List<News>> data =  getListOfNewsFromServer();
        return Single.zip(data, Single.timer(5, TimeUnit.SECONDS), (news, aLong) -> news);
    }
}
