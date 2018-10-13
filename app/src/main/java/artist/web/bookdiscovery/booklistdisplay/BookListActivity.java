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

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import artist.web.bookdiscovery.SingleFragmentActivity;

/**
 * Created by User on 7/10/2017.
 */

public class BookListActivity extends SingleFragmentActivity {

    private static final String TITLE = "book_title";
    private static final String AUTHOR = "book_author";

    public static Intent newIntent(Context context, String title, String author) {
        Intent intent = new  Intent(context, BookListActivity.class);
        intent.putExtra(TITLE,title);
        intent.putExtra(AUTHOR,author);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String title = getIntent().getStringExtra(TITLE);
        String author = getIntent().getStringExtra(AUTHOR);

        return BookListFragment.newInstance(title,author);
    }

}
