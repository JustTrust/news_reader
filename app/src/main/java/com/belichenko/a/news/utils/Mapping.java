package com.belichenko.a.news.utils;

import android.support.annotation.NonNull;

import com.belichenko.a.news.models.LocalNews;
import com.belichenko.a.news.models.News;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class Mapping {
    @NonNull
    public static Function<List<News>, List<LocalNews>> getNewsMapper() {
        return news -> {
            ArrayList<LocalNews> localNews = new ArrayList<>();
            for (News n : news) {
                LocalNews ln = new LocalNews(n);
                if (ln.isValid()) {
                    localNews.add(new LocalNews(n));
                }
            }
            return localNews;
        };
    }
}
