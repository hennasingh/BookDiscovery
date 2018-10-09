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

package artist.web.bookdiscovery.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import artist.web.bookdiscovery.EntryApplication;
import artist.web.bookdiscovery.model.ApiBaseInfo;
import artist.web.bookdiscovery.model.BookItem;
import artist.web.bookdiscovery.network.NetworkState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDataSource extends PageKeyedDataSource<Integer, BookItem> {

    private static final String TAG = BookDataSource.class.getSimpleName();
    private String mQueryData;

    private static final int FIRST_PAGE = 1;

    /**
     * The networkState and initialLoading variables
     * are for updating the UI when data is being fetched
     * by displaying a progress bar
     */
    private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();
    private MutableLiveData<NetworkState> initialLoading = new MutableLiveData<>();


    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }


    public void setQueryData(String queryData) {
        mQueryData = queryData;
    }

    /*
     * Step 2: This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, BookItem> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        EntryApplication.sApiManager.getBookApiResult(mQueryData, params.requestedLoadSize,
                new Callback<ApiBaseInfo>() {
                    @Override
                    public void onResponse(Call<ApiBaseInfo> call, Response<ApiBaseInfo> response) {
                        if (response.isSuccessful()) {
                            callback.onResult(response.body().getBookItemList(), null, FIRST_PAGE + 10);
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);
                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));

                            Log.e(TAG, "Response code from initial load: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiBaseInfo> call, Throwable t) {
                        Log.e(TAG, "Response code from initial load, onFailure: " + t.getMessage());
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, BookItem> callback) {
        // Ignore this, because we don't need to load anything before the initial load of data
    }

    /*
     * Step 3: This method it is responsible for the subsequent call to load the data page wise.
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, BookItem> callback) {

        Log.i(TAG, "Loading: " + params.key + " Count: " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        EntryApplication.sApiManager.getBookApiResult(mQueryData, params.key,
                new Callback<ApiBaseInfo>() {
                    @Override
                    public void onResponse(Call<ApiBaseInfo> call, Response<ApiBaseInfo> response) {
                        if (response.isSuccessful()) {
                            callback.onResult(response.body().getBookItemList(), params.key + 10);
                            networkState.postValue(NetworkState.LOADED);
                        } else {
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            Log.e(TAG, "Response code from initial load: " + response.code());
                        }

                    }

                    @Override
                    public void onFailure(Call<ApiBaseInfo> call, Throwable t) {
                        Log.e(TAG, "Response code from initial load, onFailure: " + t.getMessage());
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
                    }
                });

    }
}
