package com.belichenko.a.news.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.belichenko.a.news.R;
import com.belichenko.a.news.models.LocalNews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by a.belichenko on 16.06.2017.
 * mail: a.belichenko@gmail.com
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<LocalNews> list;
    private OnItemClickListener listener;

    public NewsAdapter(List<LocalNews> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(list.get(i).getId(), viewHolder.itemView);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_text)
        TextView title;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int itemId, View view);
    }

}
