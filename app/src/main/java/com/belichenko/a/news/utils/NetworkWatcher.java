package com.belichenko.a.news.utils;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NetworkWatcher {

    public NetworkWatcher() {
        init();
    }

    private void init() {
        /* Some network watcher which will be triggered when internet will be accessible
         * it will run DataManger.getNewsFromServer() to load news in DB
         * if someone will be listening DB changes he will get updates
         * if not, new data just will be stored in DB
         */
    }
}
