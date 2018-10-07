package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiBaseInfo {

    @SerializedName("kind")
    private String mKind;

    @SerializedName("totalItems")
    private int mTotalItems;

    @SerializedName("items")
    private List<BookItem> mBookItemList = null;
}
