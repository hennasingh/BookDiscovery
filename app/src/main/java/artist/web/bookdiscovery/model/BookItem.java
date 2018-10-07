package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;

public class BookItem {


    @SerializedName("id")
    private String mId;

    @SerializedName("etag")
    private String mEtag;

    @SerializedName("selfLink")
    private String mWebLink;

    @SerializedName("volumeInfo")
    private VolumeInfo mVolumeInfo;

    @SerializedName("saleInfo")
    private SaleInfo mSaleInfo;

    @SerializedName("accessInfo")
    private AccessInfo mAccessInfo;

    @SerializedName("searchInfo")
    private SearchInfo mSearchInfo;


}
