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

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import artist.web.bookdiscovery.helpers.AppExecutors;
import artist.web.bookdiscovery.model.BookItem;
import artist.web.bookdiscovery.repository.BookDataFactory;
import artist.web.bookdiscovery.repository.BookDataSource;
import artist.web.bookdiscovery.repository.WebRepository;

public class BookListViewModel extends ViewModel {

    private BookDataFactory mBookDataFactory;
    private String mBookTitle, mBookAuthor;
    private WebRepository mWebRepository;


    private LiveData mNetworkStateLiveData;
    private LiveData<PagedList<BookItem>> mPagedListBookLiveData;

    public BookListViewModel() {
        mBookDataFactory = new BookDataFactory();
        mWebRepository = new WebRepository();
        initializePaging();
    }

    private void initializePaging() {

        setSearchQuery();
        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10)
                        .build();

        mPagedListBookLiveData = new LivePagedListBuilder<>(mBookDataFactory, pagedListConfig)
                .setFetchExecutor(AppExecutors.networkIO())
                .build();
        mNetworkStateLiveData = Transformations.switchMap(mBookDataFactory.getBookDataSourceMutableLiveData(),
                BookDataSource::getNetworkState);
    }

    private void setSearchQuery() {

        StringBuilder stringBuilder = new StringBuilder();
        if(mBookAuthor!=null){

           stringBuilder.append(mBookTitle).append("+").append("inauthor:").append(mBookAuthor);

        }else{
            stringBuilder.append(mBookTitle);
        }
        mWebRepository.setQuery(stringBuilder.toString());
    }

    public LiveData getNetworkState() {
        return mNetworkStateLiveData;
    }

    public LiveData<PagedList<BookItem>> getPagedListBookLiveData() {
        return mPagedListBookLiveData;
    }

    public void setBookTitle(String bookTitle) {
        mBookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        mBookAuthor = bookAuthor;
    }

}
