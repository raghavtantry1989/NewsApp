package com.southkart.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    private static String URL_TO_FETCH = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Execute the AsyncTask
        NewsTask task = new NewsTask();
        task.execute(URL_TO_FETCH);

        // Reference of the ListView
        ListView list = (ListView) findViewById(R.id.news_list);

        // Initialize the adapter and set it to a empty list
        adapter = new NewsAdapter(getApplicationContext(),new ArrayList<News>());
        list.setAdapter(adapter);

    }

    private class NewsTask extends AsyncTask<String , Void, List<News>>{
        @Override
        protected List<News> doInBackground(String... urls) {
            // Check for empty string
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return QueryUtils.extractNews(urls[0]);
        }

        @Override
        protected void onPostExecute(List<News> newsData) {
            // Update the Elements
            adapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (newsData != null && !newsData.isEmpty()) {
                adapter.addAll(newsData);
            }

        }
    }
}
