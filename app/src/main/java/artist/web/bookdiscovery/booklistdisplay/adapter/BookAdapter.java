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

package artist.web.bookdiscovery.booklistdisplay.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import artist.web.bookdiscovery.R;
import artist.web.bookdiscovery.databinding.BooksListItemBinding;
import artist.web.bookdiscovery.model.BookItem;

public class BookAdapter extends PagedListAdapter<BookItem, BookAdapter.BookHolder> {


    public static final DiffUtil.ItemCallback<BookItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BookItem>() {
                @Override
                public boolean areItemsTheSame(BookItem oldItem, BookItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(BookItem oldItem, BookItem newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public BookAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BooksListItemBinding listItemBinding = DataBindingUtil.inflate(inflater, R.layout.books_list_item,
                parent, false);
        return new BookHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        BookItem bookData = getItem(position);
        holder.bind(bookData);

    }


    class BookHolder extends RecyclerView.ViewHolder {

        private BooksListItemBinding mListItemBinding;


        BookHolder(BooksListItemBinding binding) {
            super(binding.getRoot());
            mListItemBinding = binding;
        }

        void bind(BookItem bookData) {



        }
    }
}
