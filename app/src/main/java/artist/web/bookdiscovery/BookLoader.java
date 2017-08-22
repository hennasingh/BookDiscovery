package artist.web.bookdiscovery;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by User on 7/9/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /** Tag for log messages */
    private static final String LOG_TAG = BookLoader.class.getName();
    private String book_url;

    /**
     * Constructs a new {@link BookLoader} object
     * @param context
     * @param url
     */
    public BookLoader (Context context, String url) {
        super(context);
        book_url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Book> loadInBackground() {
        if (book_url == null) {
            return null;
        }

        // Perform network request, parse the response, and extract a list of books
        // matching search criteria
        List<Book> books = QueryUtils.fetchBookData(book_url);
        return books;
    }
}
