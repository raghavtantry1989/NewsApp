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

        ArrayList<News> sportNews = new ArrayList<>();
        sportNews.add(
                new News(
                "Nick Foles made the catch the great Tom Brady couldn't in Super Bowl LII",
                "https://www.theguardian.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                "https://content.guardianapis.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "2018-02-05T05:26:32Z"

                ));

        sportNews.add(
                new News(
                        "Nick Foles made the catch the great Tom Brady couldn't in Super Bowl LII",
                        "https://www.theguardian.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "https://content.guardianapis.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "2018-02-05T05:26:32Z"

                ));

        sportNews.add(
                new News(
                        "Nick Foles made the catch the great Tom Brady couldn't in Super Bowl LII",
                        "https://www.theguardian.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "https://content.guardianapis.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "2018-02-05T05:26:32Z"

                ));

        sportNews.add(
                new News(
                        "Nick Foles made the catch the great Tom Brady couldn't in Super Bowl LII",
                        "https://www.theguardian.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "https://content.guardianapis.com/sport/blog/2018/feb/04/nick-foles-catch-tom-brady-super-bowl-2018",
                        "2018-02-05T05:26:32Z"

                ));

        ListView list = (ListView) findViewById(R.id.news_list);

        NewsAdapter adapter = new NewsAdapter(this,sportNews);
        list.setAdapter(adapter);

    }
}
