package com.belichenko.a.news.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.belichenko.a.news.data_flow.Server;
import com.belichenko.a.news.data_flow.ServerApi;
import com.belichenko.a.news.data_flow.DataManager;
import com.belichenko.a.news.data_flow.NewsDataManager;
import com.belichenko.a.news.data_flow.data_base.NewsDatabase;
import com.belichenko.a.news.utils.NewsGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(@NonNull Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Server provideAPI(){
        return new ServerApi(new NewsGenerator());
    }

    @Provides
    @Singleton
    NewsDatabase provideDatabase(){
        return Room.databaseBuilder(mApplication, NewsDatabase.class, "news_db").build();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(Server server, NewsDatabase database){
        return new NewsDataManager(server, database);
    }

}
