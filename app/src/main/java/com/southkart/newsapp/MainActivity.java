package com.southkart.newsapp;

import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<News>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private final static String URL_BASE = "http://content.guardianapis.com/search?q=";
    private final static String URL_KEY = "&api-key=test";
    private String searchKeyWord = "debates";
    private MainActivity self = this;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int GUARDIAN_LOADER_ID = 1;


    private NewsAdapter adapter;
    private EditText searchText;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference of the ListView
        ListView list = (ListView) findViewById(R.id.news_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = (News) adapter.getItem(position);

                // Intent to open the url in browser
                Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                openBrowser.setData(Uri.parse(currentNews.getWebUrl()));
                startActivity(openBrowser);
            }
        });

        searchText = (EditText) findViewById(R.id.searchText);
        searchKeyWord = searchText.getText().toString();

        // Initialize the Loader
        getLoaderManager().initLoader(GUARDIAN_LOADER_ID, null, this);

        // Reference of the Search Button
        searchBtn = (Button) findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the Keyword to search
                searchKeyWord = searchText.getText().toString();
                Log.v("String to Search", searchKeyWord);
                Toast.makeText(getApplicationContext(),"Will perform search for "+ searchKeyWord,Toast.LENGTH_LONG).show();

                adapter.clear();

                // Initialize the Loader
                getLoaderManager().restartLoader(GUARDIAN_LOADER_ID, null, self);

            }
        });

        // Initialize the adapter and set it to a empty list
        adapter = new NewsAdapter(getApplicationContext(),new ArrayList<News>());
        list.setAdapter(adapter);

    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        Log.v("String to Search", searchKeyWord);
        return new GuardianDataLoader(this, URL_BASE + searchKeyWord + URL_KEY);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> newsData) {
        // Update the Elements
        adapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsData != null && !newsData.isEmpty()) {
            adapter.addAll(newsData);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        adapter.clear();
    }
}
