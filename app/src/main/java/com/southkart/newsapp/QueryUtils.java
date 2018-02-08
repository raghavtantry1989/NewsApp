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
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by tantryr on 2/8/18.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();
    private static final String SAMPLE_JSON_FILE_NAME = "data.json";

    private QueryUtils(){

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

    public static ArrayList<News> extractNews(Context context){
        // Create an empty ArrayList
        ArrayList<News> newsList = new ArrayList<>();

        try {
            // Reference the Local Data File and get the data in stream
            AssetManager manager = context.getAssets();
            InputStream data = manager.open(SAMPLE_JSON_FILE_NAME);

            // Read the input stream and get the JSON response back
            String jsonResponse = readFromStream(data);

            // Convert JSON String to JSON Object
            JSONArray rootArray = new JSONArray(jsonResponse);

            // Get reference to the rootObject array
            JSONObject rootObject = rootArray.getJSONObject(0);

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

                // Add a news object in the arraylist
                newsList.add(new News(sectionName,webPublicationDate,webTitle,webUrl));
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
