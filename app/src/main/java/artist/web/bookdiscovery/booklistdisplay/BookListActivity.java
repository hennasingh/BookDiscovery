/*
 * Copyright (c) 2018 Henna Singh
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package artist.web.bookdiscovery.booklistdisplay;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import artist.web.bookdiscovery.booklistdisplay.adapter.BookAdapter;
import artist.web.bookdiscovery.R;
import artist.web.bookdiscovery.SingleFragmentActivity;
import artist.web.bookdiscovery.model.BookItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 7/10/2017.
 */

public class BookListActivity extends SingleFragmentActivity {

    public static final String LOG_TAG = BookListActivity.class.getName();

    private static String mBookSearched;
    @BindView(R.id.recycler_view_booklist)
    RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private BookListViewModel mBookListViewModel;

    private TextView emptyState;

    @Override
    protected Fragment createFragment() {
        return BookListFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        ButterKnife.bind(this);
        mBookListViewModel = ViewModelProviders.of(this).get(BookListViewModel.class);

        // Get Intent Extras
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mBookSearched = bundle.getString("bookTitle");

        }

        setUpUI();


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.

        } else {
            // Hide loading indicator and show empty state view
            View progressIndicator = findViewById(R.id.progress_indicator);
            progressIndicator.setVisibility(View.GONE);
            emptyState.setText(R.string.error_no_connection);
        }

    }

    private void setUpUI() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBookAdapter = new BookAdapter();

        mBookListViewModel.getPagedListBookLiveData().observe(this, new Observer<PagedList<BookItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<BookItem> bookItems) {
                if (bookItems != null) {
                    mBookAdapter.submitList(bookItems);
                }
            }
        });
        mRecyclerView.setAdapter(mBookAdapter);
    }

    /**
     * This method prepares the final query used to fetch data from Google API
     *
     * @return search query
     */
    public String prepareSearchQuery() {

        StringBuilder stringBuilder = new StringBuilder(BOOKS_REQUEST_BASE_URL);
        stringBuilder.append("+intitle:").append(bookSearched).append("&").append("maxResults=").append(NUM_RESULTS_DEFAULT);

        return (stringBuilder.toString());
    }


}
