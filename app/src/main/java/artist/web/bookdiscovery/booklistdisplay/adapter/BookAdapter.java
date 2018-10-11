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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import artist.web.bookdiscovery.R;
import artist.web.bookdiscovery.databinding.BooksListItemBinding;
import artist.web.bookdiscovery.model.BookItem;
import artist.web.bookdiscovery.model.ImageLinks;
import artist.web.bookdiscovery.model.SaleInfo;
import artist.web.bookdiscovery.model.VolumeInfo;

public class BookAdapter extends PagedListAdapter<BookItem, BookAdapter.BookHolder> {

    private static final String TAG =BookAdapter.class.getSimpleName();

    private static final DiffUtil.ItemCallback<BookItem> DIFF_CALLBACK =
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

        Log.d(TAG, "Binding data");

    }


    class BookHolder extends RecyclerView.ViewHolder {

        private BooksListItemBinding mListItemBinding;


        BookHolder(BooksListItemBinding binding) {
            super(binding.getRoot());
            mListItemBinding = binding;
        }

        void bind(BookItem bookData) {
            VolumeInfo volumeInfo = bookData.getVolumeInfo();
            if (volumeInfo != null) {

                //getting Book Title
                if (volumeInfo.getTitle() != null) {
                    mListItemBinding.bookTitle.setText(volumeInfo.getTitle());
                } else {
                    mListItemBinding.bookTitle.setText(R.string.no_title);
                }

                //getting Authors
                StringBuilder stringText = new StringBuilder();
                if (volumeInfo.getAuthors() != null) {
                    for (int i = 0; i < volumeInfo.getAuthors().size(); i++) {
                        if (i == volumeInfo.getAuthors().size() - 1) {
                            stringText.append(volumeInfo.getAuthors().get(i));
                        } else {
                            stringText.append(volumeInfo.getAuthors().get(i)).append("and ");
                        }
                    }
                    mListItemBinding.bookAuthor.setText(stringText.toString());
                } else {
                    mListItemBinding.bookAuthor.setText(R.string.no_author);
                }

                //getting Publisher
                if (volumeInfo.getPublisher() != null) {
                    mListItemBinding.bookPublisher.setText(volumeInfo.getPublisher());
                } else {
                    mListItemBinding.bookPublisher.setText(R.string.no_publisher);
                }

                //getting Rating
                if (volumeInfo.getAverageRating() != null) {
                    mListItemBinding.rating.setRating(volumeInfo.getAverageRating());
                }else{
                    mListItemBinding.rating.setRating(0);
                }

                //getting Image Thumbnail
                if(volumeInfo.getImageLinks()!=null){
                    ImageLinks imageLinks = volumeInfo.getImageLinks();
                    if(imageLinks.getThumbnail()!=null) {

                        Picasso.get().load(imageLinks.getThumbnail())
                                .placeholder(R.drawable.image_not_found)
                                .error(R.drawable.image_not_found)
                                .into(mListItemBinding.imageBook);

                    }else {
                        Picasso.get().load(R.drawable.image_not_found)
                                .error(R.drawable.image_not_found)
                                .into(mListItemBinding.imageBook);
                    }
                }else{
                    Picasso.get().load(R.drawable.image_not_found)
                            .error(R.drawable.image_not_found)
                            .into(mListItemBinding.imageBook);
                }
            }

            SaleInfo saleInfo = bookData.getSaleInfo();
            if(saleInfo!=null){
                if(saleInfo.getListPrice()!=null){
                    mListItemBinding.listPrice.setText(String.valueOf(saleInfo.getListPrice().getAmount()));
                }else{
                    mListItemBinding.listPrice.setText(R.string.no_price);
                }

            }

        }
    }
}
