package artist.web.bookdiscovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 7/9/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        BookViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.books_list_item, parent, false);
            holder = new BookViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BookViewHolder) convertView.getTag();
        }

        Book currentBook = getItem(position);

        String title = currentBook.getTitle();
        holder.bookTitleView.setText(title);

        String author = currentBook.getAuthor();
        holder.bookAuthorView.setText(author);

        String publisherBook = currentBook.getBookPublisher();
        holder.bookPublisherView.setText(publisherBook);

        String publishedDate = currentBook.getBookPublishedDate();
        holder.bookPublishedDateView.setText(publishedDate);


        //set Image if Applicable
        String image = currentBook.getImageThumbnail();

        if (image != null && image.length() > 0) {
            Picasso.with(getContext()).load(currentBook.getImageThumbnail()).into(holder.bookImageView);
        } else {
            Picasso.with(getContext()).load(R.drawable.image_not_found).into(holder.bookImageView);
        }

        return convertView;
    }

    public static class BookViewHolder {

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


        public BookViewHolder(View listItemView) {
            ButterKnife.bind(this, listItemView);

        }
    }
}
