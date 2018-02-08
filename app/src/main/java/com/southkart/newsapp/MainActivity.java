package com.southkart.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> sportNews = QueryUtils.extractNews(this);

        ListView list = (ListView) findViewById(R.id.news_list);

        NewsAdapter adapter = new NewsAdapter(this,sportNews);
        list.setAdapter(adapter);

    }
}
