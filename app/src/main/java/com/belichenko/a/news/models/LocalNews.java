package com.belichenko.a.news.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

@Entity(tableName = "news")
public class LocalNews {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "content")
    private String content;

    public LocalNews() {
    }

    @Ignore
    public LocalNews(int id, String title, String type, String content) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.content = content;
    }

    @Ignore
    public LocalNews(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.type = news.getType();
        if (news.getContent().length > 0) {
            try {
                this.content = new String(news.getContent(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                this.content = "…";
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalNews localNews = (LocalNews) o;

        return id == localNews.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean isValid() {
        if (id < 0) return false;
        if (TextUtils.isEmpty(title)) return false;
        if (TextUtils.isEmpty(type)) return false;
        if (TextUtils.isEmpty(content)) return false;
        return true;
    }
}
