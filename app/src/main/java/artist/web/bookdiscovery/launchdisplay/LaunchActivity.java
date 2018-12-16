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

package artist.web.bookdiscovery.launchdisplay;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import artist.web.bookdiscovery.R;
import artist.web.bookdiscovery.booklistdisplay.BookListActivity;
import artist.web.bookdiscovery.booklistdisplay.BookListViewModel;
import artist.web.bookdiscovery.databinding.ActivityLaunchBinding;

public class LaunchActivity extends AppCompatActivity {

    private ActivityLaunchBinding mLaunchActivityBinding;
    private String mTitle, mAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLaunchActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);

        mLaunchActivityBinding.bookTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mTitle = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mTitle.length() == 0) {
                    mLaunchActivityBinding.bookTitle.setError(getString(R.string.invalid_title));
                }
            }
        });

    }

    public void showSearchResults(View view) {

        if(validateInput()) {
            Intent intent = BookListActivity.newIntent(this,mTitle,mAuthor);
            startActivity(intent);
        }
    }

    private boolean validateInput() {
        mTitle = mLaunchActivityBinding.bookTitle.getText().toString();
        mAuthor = mLaunchActivityBinding.bookAuthor.getText().toString();

        return mTitle.length() != 0 && mAuthor.length() != 0 || mTitle.length() != 0;
    }


}
