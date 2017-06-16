package com.belichenko.a.news.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.belichenko.a.news.R;
import com.belichenko.a.news.models.LocalNews;
import com.belichenko.a.news.ui.view_models.ItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by a.belichenko on 15.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class DetailActivity extends BaseActivity {

    private static final String NEWS_ID = DetailActivity.class.getCanonicalName() + "news_id";
    @BindView(R.id.detail_title)
    TextView detailTitle;
    @BindView(R.id.detail_type)
    TextView detailType;
    @BindView(R.id.detail_content)
    TextView detailContent;
    @BindView(R.id.detail_id)
    TextView detailId;

    public static void start(Context context, int newsId) {
        context.startActivity(new Intent(context, DetailActivity.class).putExtra(NEWS_ID, newsId));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detil_activity);
        ButterKnife.bind(this);
        int newsId = getIntent().getIntExtra(NEWS_ID, -1);
        if (newsId < 0) {
            throw new IllegalStateException("DetailActivity opened without news Id");
        }
        ItemViewModel model = ViewModelProviders.of(this).get(ItemViewModel.class);
        model.getOneNews(newsId).observe(this, news -> updateUI(news));

        model.getErrors().observe(this, error -> {
            if (!TextUtils.isEmpty(error))
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateUI(LocalNews localNews) {
        if (localNews.isValid()) {
            detailTitle.setText(localNews.getTitle());
            detailType.setText(localNews.getType());
            detailContent.setText(localNews.getContent());
            detailId.setText(String.valueOf(localNews.getId()));
        } else {
            detailTitle.setText(R.string.empty_item);
        }
    }
}
