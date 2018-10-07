package artist.web.bookdiscovery.network;

import artist.web.bookdiscovery.model.ApiBaseInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BooksApi {

    @GET("volumes?q={search-terms}")
    Call<ApiBaseInfo> getBookApiResult(@Path("search-terms") String search);

}
