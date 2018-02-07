package com.southkart.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tantryr on 2/5/18.
 */

public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }

        News currentNews = (News) getItem(position);

        TextView webTitle = (TextView) listItemView.findViewById(R.id.webTitle);
        webTitle.setText(currentNews.getWebTitle());

        TextView webPublicationDate = (TextView) listItemView.findViewById(R.id.webPublicationDate);
        webPublicationDate.setText(currentNews.getWebPublicationDate());

        return listItemView;
    }
}
