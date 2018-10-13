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
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import artist.web.bookdiscovery.R;
import artist.web.bookdiscovery.booklistdisplay.adapter.BookAdapter;
import artist.web.bookdiscovery.databinding.FragmentBooklistBinding;
import artist.web.bookdiscovery.model.BookItem;

public class BookListFragment extends Fragment {

    private static final String TAG = BookListFragment.class.getSimpleName();
    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_AUTHOR = "arg_author";

    private BookAdapter mPageListAdapter;
    private BookListViewModel mBookListViewModel;

    public static BookListFragment newInstance(String title, String author) {

        Bundle args = new Bundle();
        args.putString(ARG_TITLE,title);
        args.putString(ARG_AUTHOR, author);

        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookListViewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        String title = getArguments().getString(ARG_TITLE);
        String author = getArguments().getString(ARG_AUTHOR);
        Log.d(TAG, "Title and author are  "+ title+ ", " +author);
        mBookListViewModel.setBookTitle(title);
        mBookListViewModel.setBookAuthor(author);
        Log.d(TAG, "In onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "In onCreateView");

        FragmentBooklistBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booklist,
                container, false);

        binding.recyclerViewBooklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPageListAdapter = new BookAdapter();

        mBookListViewModel.getPagedListBookLiveData().observe(this, new Observer<PagedList<BookItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<BookItem> bookItems) {
                if (bookItems != null) {
                    mPageListAdapter.submitList(bookItems);
                    Log.d(TAG, "bookItems not Empty, Adapter set");
                }
            }
        });

        binding.recyclerViewBooklist.setAdapter(mPageListAdapter);
        return binding.getRoot();

    }
}
