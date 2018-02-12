package com.southkart.newsapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by tantryr on 2/8/18.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int SUCCESS_STATUS_CODE = 200;


    // An empty private constructor makes sure that the class is not going to be initialised.
    private QueryUtils(){

    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(READ_TIMEOUT /* milliseconds */);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT /* milliseconds */);
            urlConnection.connect();
            int status = urlConnection.getResponseCode();
            if(status == SUCCESS_STATUS_CODE){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG,"Status :" + status);
            }

        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e(LOG_TAG,"IOException",e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /*
    Static method to read from a local Json File,
    takes in a input stream of a file and returns string output
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<News> extractNews(String url_string){
        // Create an empty ArrayList
        ArrayList<News> newsList = new ArrayList<>();

        try {
            // Convert String to URL
            URL url = createUrl(url_string);

            // Read the input stream and get the JSON response back
            String jsonResponse = makeHttpRequest(url);

            // Convert JSON String to JSON Object
            JSONObject rootObject = new JSONObject(jsonResponse);

            // Get reference to response Object
            JSONObject response = rootObject.getJSONObject("response");

            // Get reference to the results Object
            JSONArray results = response.getJSONArray("results");

            // Loop through the array
            for(int i=0; i< results.length(); i++){
                // Get reference to the first news data
                JSONObject newsData = results.getJSONObject(i);

                // Get reference of sectionName
                String sectionName = newsData.getString("sectionName");
                String webPublicationDate = newsData.getString("webPublicationDate");
                String webTitle = newsData.getString("webTitle");
                String webUrl = newsData.getString("webUrl");

                JSONArray tagsArray = newsData.getJSONArray("tags");
                StringBuilder authors = new StringBuilder();

                for(int indexOfTags = 0; indexOfTags < tagsArray.length(); indexOfTags++){
                    JSONObject tagsObject = tagsArray.getJSONObject(indexOfTags);
                    authors.append(tagsObject.getString("webTitle"));
                    authors.append(" ");
                }

                // Add a news object in the arraylist
                newsList.add(new News(sectionName,webPublicationDate,webTitle,webUrl, authors.toString()));
                Log.v(LOG_TAG,"News Added");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return newsList;
    }


}
