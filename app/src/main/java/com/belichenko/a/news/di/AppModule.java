package com.belichenko.a.news.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.belichenko.a.news.data_flow.SeverApi;
import com.belichenko.a.news.data_flow.DataManager;
import com.belichenko.a.news.data_flow.NewsDataManager;

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
    DataManager provideDataManager(Context context){
        return new NewsDataManager();
    }

    @Provides
    @Singleton
    SeverApi provideAPI(){
        return new SeverApi();
    }
}
