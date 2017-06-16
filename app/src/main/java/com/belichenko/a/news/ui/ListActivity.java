package com.belichenko.a.news.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.belichenko.a.news.R;
import com.belichenko.a.news.models.LocalNews;
import com.belichenko.a.news.ui.adapters.NewsAdapter;
import com.belichenko.a.news.ui.view_models.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class ListActivity extends BaseActivity {

    @BindView(R.id.news_list)
    RecyclerView newsList;

    private List<LocalNews> localNews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ButterKnife.bind(this);

        newsList.setAdapter(new NewsAdapter(localNews, id -> goToDetailView(id)));

        ListViewModel model = ViewModelProviders.of(this).get(ListViewModel.class);
        model.getNewsList().observe(this, news -> {
            Timber.d("List was updated - " + news.size());
            localNews.clear();
            localNews.addAll(news);
            newsList.getAdapter().notifyDataSetChanged();
        });
        model.getErrors().observe(this, error -> {
            if (!TextUtils.isEmpty(error))
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

    }

    private void goToDetailView(int newsId) {
        Timber.d("List item was chosen " + newsId);
        DetailActivity.start(this, newsId);
    }
}
