package artist.web.bookdiscovery.repository;

import android.util.Log;

import artist.web.bookdiscovery.model.ApiBaseInfo;
import artist.web.bookdiscovery.network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;

public class WebRepository {


    private static final String TAG = WebRepository.class.getSimpleName();
    private String mQuery;

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    public void getBookApiResult( int maxResult, Callback<ApiBaseInfo> callback) {

        Log.d(TAG,"Query received"+mQuery);
        Call<ApiBaseInfo> resultCall = ApiManager.getBooksApi().getBookApiResult(mQuery, maxResult);
         resultCall.enqueue(callback);
    }

}
