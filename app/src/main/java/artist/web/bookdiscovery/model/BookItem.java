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


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEtag() {
        return mEtag;
    }

    public void setEtag(String etag) {
        mEtag = etag;
    }

    public String getWebLink() {
        return mWebLink;
    }

    public void setWebLink(String webLink) {
        mWebLink = webLink;
    }

    public VolumeInfo getVolumeInfo() {
        return mVolumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        mVolumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return mSaleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        mSaleInfo = saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return mAccessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        mAccessInfo = accessInfo;
    }

    public SearchInfo getSearchInfo() {
        return mSearchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        mSearchInfo = searchInfo;
    }
}
