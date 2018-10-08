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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import artist.web.bookdiscovery.booklistdisplay.BookListActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button_search)
    Button buttonSearch;
    @BindView(R.id.book_title)
    EditText editBookTitle;

    private String book_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        buttonSearch.setOnClickListener(this);
    }

    /**
     * Invokes methods for individual call to action buttons
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_search:
                showSearchResults();
                break;
        }
    }

    /**
     * This method launches BookListActivity after validating Book Title and Author inputs
     */
    public void showSearchResults() {
        if (validateInput()) {
            Intent intent = new Intent(this, BookListActivity.class);

            // Replace spaces with + sign for URL to be used to fetch data
            intent.putExtra("bookTitle", book_title.replaceAll(" ", "+"));
            startActivity(intent);
        }
    }

    /**
     * This method checks if an input string contains number or invalid characters.
     */
    public boolean validateInput() {
        book_title = editBookTitle.getText().toString().trim();

        /** Check if Book Title is entered; else set error */
        if (book_title.length() == 0) {
            editBookTitle.setBackgroundResource(R.color.colorError);
            editBookTitle.setError(getString(R.string.error_empty_string));
            return false;
        }
        return true;
    }
}
