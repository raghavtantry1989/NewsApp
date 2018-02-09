package com.southkart.newsapp;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantryr on 2/9/18.
 */

public class GuardianDataLoader extends AsyncTaskLoader<ArrayList<News>> {

    // Private Variable to Store the URL
    private String mUrl;

    public GuardianDataLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        // Check for empty string
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.extractNews(mUrl);
    }
}
