package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;


public class AccessInfo {

    @SerializedName("country")
    private String mCountry;

    @SerializedName("viewability")
    private String mViewability;

    @SerializedName("embeddable")
    private Boolean mEmbeddable;

    @SerializedName("publicDomain")
    private Boolean mPublicDomain;

    @SerializedName("epub")
    private Epub mEpub;

    @SerializedName("pdf")
    private Pdf mPdf;

    @SerializedName("webReaderLink")
    private String mWebReaderLink;

    @SerializedName("accessViewStatus")
    private String mAccessViewStatus;

}

