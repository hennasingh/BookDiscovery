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

package artist.web.bookdiscovery;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by User on 7/9/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = BookLoader.class.getName();
    private String book_url;

    /**
     * Constructs a new {@link BookLoader} object
     *
     * @param context
     * @param url
     */
    public BookLoader(Context context, String url) {
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
