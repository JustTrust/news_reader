package com.belichenko.a.news;

import android.app.Application;

import com.belichenko.a.news.di.AppComponent;
import com.belichenko.a.news.di.AppModule;
import com.belichenko.a.news.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NewsApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)
                            + "::Line:" + element.getLineNumber() + ""
                            + "::" + element.getMethodName() + "()";
                }
            });
        }
    }
}
