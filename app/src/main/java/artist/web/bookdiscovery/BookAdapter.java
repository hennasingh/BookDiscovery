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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import artist.web.bookdiscovery.model.DisplayBookData;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {


    private List<DisplayBookData> mDisplayBookData;

    BookAdapter(List<DisplayBookData> BookData) {
        mDisplayBookData = BookData;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.books_list_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        DisplayBookData bookData = mDisplayBookData.get(position);
        holder.bind(bookData);

    }

    @Override
    public int getItemCount() {
        return mDisplayBookData.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book_title)
        TextView bookTitleView;
        @BindView(R.id.book_author)
        TextView bookAuthorView;
        @BindView(R.id.image_book)
        ImageView bookImageView;
        @BindView(R.id.book_publisher)
        TextView bookPublisherView;
        @BindView(R.id.book_publishedDate)
        TextView bookPublishedDateView;
        @BindView(R.id.rating)
        RatingBar bookRating;


        BookHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        void bind(DisplayBookData bookData) {
            bookTitleView.setText(bookData.getTitle());
            bookAuthorView.setText(bookData.getAuthor());
            bookPublisherView.setText(bookData.getPublisher());
            bookPublishedDateView.setText(bookData.getDate());
            bookRating.setRating(bookData.getRating());

            String image = bookData.getImageSmall();

            if (image != null && image.length() > 0) {
                Picasso.get().load(image).into(bookImageView);
            } else {
                Picasso.get().load(R.drawable.image_not_found).into(bookImageView);

            }
        }
    }
}
