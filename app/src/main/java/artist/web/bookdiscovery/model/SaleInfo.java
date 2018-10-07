package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;


public class SaleInfo {

    @SerializedName("country")
    private String mCountry;

    @SerializedName("saleability")
    private String mSaleability;

    @SerializedName("isEbook")
    private Boolean mIsEbook;

    @SerializedName("buyLink")
    private String mBuyLink;
}

