package artist.web.bookdiscovery.network;

import artist.web.bookdiscovery.model.ApiBaseInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static BooksApi sBooksApi;
    private static ApiManager sApiManager;

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sBooksApi = retrofit.create(BooksApi.class);
    }

    public static ApiManager getInstance() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    public void getBookApiResult(String search, Callback<ApiBaseInfo> callback) {

        Call<ApiBaseInfo> resultCall = sBooksApi.getBookApiResult(search);
        resultCall.enqueue(callback);
    }
}
