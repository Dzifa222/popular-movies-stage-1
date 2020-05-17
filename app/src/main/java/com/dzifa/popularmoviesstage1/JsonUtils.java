package com.dzifa.popularmoviesstage1;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class JsonUtils {
    private final static String MOVIEDB_BASE_URL = "http://api.themoviedb.org/3/movie";
    private final static String API_KEY = "e0a66157f76833fc726d09eee76eeb7d";

    public static final String  TITLE = "title",
            OVERVIEW = "overview",
            RELEASE_DATE = "release_date",
            RATING = "vote_average",
            POSTER = "poster_path";

    public static URL buildUrl(String[] query) throws MalformedURLException {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(query[0])
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

