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

    static class ViewHolder{
        TextView webTitle;
        TextView webPublicationDate;
        TextView sectionName;
        TextView authorName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);

            holder = new ViewHolder();
            holder.webTitle = (TextView) listItemView.findViewById(R.id.webTitle);
            holder.webPublicationDate = (TextView) listItemView.findViewById(R.id.webPublicationDate);
            holder.sectionName = (TextView) listItemView.findViewById(R.id.sectionName);
            holder.authorName = (TextView) listItemView.findViewById(R.id.author);
            listItemView.setTag(holder);
        }
        else{
            holder = (ViewHolder) listItemView.getTag();
        }
        News currentNews = (News) getItem(position);

        holder.webTitle.setText(currentNews.getWebTitle());
        holder.webPublicationDate.setText(currentNews.getWebPublicationDate());
        holder.sectionName.setText(currentNews.getSectionName());
        holder.authorName.setText(currentNews.getAuthor());
        return listItemView;
    }
}
