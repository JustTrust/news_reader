package com.belichenko.a.news.data_flow.data_base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.belichenko.a.news.models.LocalNews;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

@Database(version = 1, entities = {LocalNews.class})
public abstract class NewsDatabase extends RoomDatabase {
    abstract public NewsDao newsDao();
}
