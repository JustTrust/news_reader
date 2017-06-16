package com.belichenko.a.news.data_flow.data_base;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.belichenko.a.news.models.LocalNews;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(LocalNews... news);

    @Update
    void updateNews(LocalNews... news);

    @Delete
    void deleteNews(LocalNews... news);

    @Query("SELECT * FROM news")
    Flowable<List<LocalNews>> getNewsList();

    @Query("SELECT * FROM news WHERE id = :userId")
    Flowable<LocalNews> getOneNews(int userId);
}
