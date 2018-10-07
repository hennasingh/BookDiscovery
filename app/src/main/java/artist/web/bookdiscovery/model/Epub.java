package artist.web.bookdiscovery.model;

import com.google.gson.annotations.SerializedName;

public class Epub {

    @SerializedName("isAvailable")
    private Boolean isAvailable;

    @SerializedName("downloadLink")
    private String downloadLink;


}
