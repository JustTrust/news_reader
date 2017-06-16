package com.belichenko.a.news.di;

import com.belichenko.a.news.ui.view_models.ListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(ListViewModel listViewModel);
}
