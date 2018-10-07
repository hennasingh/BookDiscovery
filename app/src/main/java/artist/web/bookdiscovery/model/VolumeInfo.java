package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("subtitle")
    private String mSubtitle;

    @SerializedName("authors")
    private List<String> mAuthors;

    @SerializedName("publishedDate")
    private String mPublishedDate;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("pageCount")
    private int mPageCount;

    @SerializedName("printType")
    private String mPrintType;

    @SerializedName("categories")
    private List<String> mCategories;

    @SerializedName("averageRating")
    private double mAverageRating;

    @SerializedName("ratingsCount")
    private int mRatingCount;

    @SerializedName("contentVersion")
    private String mContentVersion;

    @SerializedName("imageLinks")
    private ImageLinks mImageLinks;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("previewLink")
    private String mPreviewLink;


}
