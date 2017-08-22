package artist.web.bookdiscovery;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/10/2017.
 */

public class BookListActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookListActivity.class.getName();

    /** Constant value for the BookLoader ID */
    private static final int BOOK_LOADER_ID = 1;

    /** Google API URL */
    private static final String BOOKS_REQUEST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    private static final int NUM_RESULTS_DEFAULT = 10;

    private static String bookSearched;
    private BookAdapter book_adapter;
    private TextView emptyState;
    public static List<Book> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        // Get Intent Extras
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            bookSearched = bundle.getString("bookTitle");

        }

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list_books);

        emptyState = (TextView)findViewById(R.id.text_empty_list);
        bookListView.setEmptyView(emptyState);

        // Create a new adapter that takes the list of books as input
        booksList = new ArrayList<Book>();
        book_adapter = new BookAdapter(this,booksList);

        // Create a new adapter that takes the list of books as input
        bookListView.setAdapter(book_adapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Hide loading indicator and show empty state view
            View progressIndicator = findViewById(R.id.progress_indicator);
            progressIndicator.setVisibility(View.GONE);
            emptyState.setText(R.string.error_no_connection);
        }

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Book currentBook = book_adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getInfoUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {


        Uri baseUri = Uri.parse(prepareSearchQuery());
        Uri.Builder uriBuilder = baseUri.buildUpon();

      return new BookLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        // Hide progress indicator because the data has been loaded
        View progressIndicator = findViewById(R.id.progress_indicator);
        progressIndicator.setVisibility(View.GONE);

        // Set empty state text when no books found
        emptyState.setText(R.string.info_no_books);

        // Clear the adapter of previous book data
        book_adapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            book_adapter.addAll(books);
        }
    }

    /**
     * This method prepares the final query used to fetch data from Google API
     * @return search query
     */
    public String prepareSearchQuery() {

        StringBuilder stringBuilder = new StringBuilder(BOOKS_REQUEST_BASE_URL);
        stringBuilder.append("+intitle:").append(bookSearched).append("&").append("maxResults=").append(NUM_RESULTS_DEFAULT);

        return(stringBuilder.toString());
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        book_adapter.clear();
    }



}
