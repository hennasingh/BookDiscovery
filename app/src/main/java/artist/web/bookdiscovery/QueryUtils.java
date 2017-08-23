package artist.web.bookdiscovery;

import android.text.TextUtils;
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
import java.util.List;

/**
 * Created by User on 7/9/2017.
 */

public final class QueryUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the Google Book API and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBookData(String requestUrl) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            //If the request was successful (response code 200),
            //then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {

            Log.e(LOG_TAG, "There is an error in making a connection", e);
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

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
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

    /**
     * Return a list of {@link Book} objects retrieved from parsing a JSON response.
     */
    private static List<Book> extractFeatureFromJson(String bookJSON) {
        /** If the JSON string is empty or null, then return early. */
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        /** Create an empty ArrayList used to add books */
        List<Book> books = new ArrayList<>();

        try {
            JSONObject baseJsonResponse;    // JSON Object for the data retrieved from API request
            JSONArray bookArray;            // Array of books returned in the JSON object
            JSONObject currentBook;         // Single book at a specific position in the bookArray
            JSONObject volumeInfo;          // VolumeInfo object of the currentBook
            String title;                   // Title of the currentBook
            JSONArray authorsArray;          // Author Array of the currentBook
            String authorList = "";         // Authors of the currentBook - obtained from authorsArray
            String bookPublisher = "";       // Description of the currentBook - obtained from VolumeInfo object
            String bookPublishedDate = "";
            JSONObject imageLinks;          // JSON Object with various image links
            String thumbnailLink = "";      // Thumbnail Link of the currentBook - obtained from imageLinks
            String infoLink = "";            // Buying Link of the currentBook - obtained from saleInfo object

            baseJsonResponse = new JSONObject(bookJSON);

            bookArray = baseJsonResponse.optJSONArray("items");

            for (int i = 0; bookArray != null && i < bookArray.length(); i++) {
                currentBook = bookArray.getJSONObject(i);

                    volumeInfo = currentBook.getJSONObject("volumeInfo");
                    title = volumeInfo.getString("title");


                //book authors list

                if (volumeInfo.has("authors")) {
                    authorsArray = volumeInfo.getJSONArray("authors");
                    if (authorsArray.length() > 1) {
                        authorList = authorsArray.join(", ").replaceAll("\"", "");
                    } else if (authorsArray.length() == 1) {
                        authorList = authorsArray.getString(0);
                    } else if (authorsArray.length() == 0) {
                        authorList = "";
                    }
                } else
                    authorList = "";

                //book image links
                if(volumeInfo.has("imageLinks")) {
                    imageLinks = volumeInfo.getJSONObject("imageLinks");
                    if (imageLinks.has("smallThumbnail")) {
                        thumbnailLink = imageLinks.getString("smallThumbnail");
                    } else {
                        thumbnailLink = "";
                    }
                }

                // Get value for publishedDate if the key exists
                if (volumeInfo.has("publishedDate")) {
                    bookPublishedDate = volumeInfo.getString("publishedDate");
                } else {
                    bookPublishedDate = "";
                }

                // Get value for publisher if the key exists
                if (volumeInfo.has("publisher")) {
                    bookPublisher = volumeInfo.getString("publisher");
                } else {
                    bookPublisher = "";
                }

                // book info link
                if (volumeInfo.has("infoLink")) {
                    infoLink = volumeInfo.getString("infoLink");
                } else {
                    infoLink = "https://books.google.com/";
                }

                // Create a new {@link Book} object with parameters obtained from JSON response
                Book book = new Book(
                        title,
                        authorList,
                        bookPublisher,
                        bookPublishedDate,
                        infoLink,
                        thumbnailLink
                );

                // Add the new {@link Book} object to the list of books
                books.add(book);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "There is an error retrieving Json Results", e);
        }

        // Return the list of books
        return books;

    }


}
