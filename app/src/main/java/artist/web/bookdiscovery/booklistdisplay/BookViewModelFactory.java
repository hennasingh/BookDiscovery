package artist.web.bookdiscovery.booklistdisplay;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class BookViewModelFactory implements ViewModelProvider.Factory {

    String mAuthor;
    String mTitle;

    public BookViewModelFactory(String author, String title) {
        mAuthor = author;
        mTitle = title;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookListViewModel.class)) {
            return (T) new BookListViewModel(mAuthor, mTitle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
