package com.belichenko.a.news.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.belichenko.a.news.R;
import com.belichenko.a.news.ui.view_models.ListViewModel;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ButterKnife.bind(this);

        ListViewModel model = ViewModelProviders.of(this).get(ListViewModel.class);
        model.getNewsList().observe(this, news -> {
            Timber.d("List was updated");
        });
    }
}
