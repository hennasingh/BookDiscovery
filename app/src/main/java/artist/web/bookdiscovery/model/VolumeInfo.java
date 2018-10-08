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

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("subtitle")
    private String mSubtitle;

    @SerializedName("authors")
    private List<String> mAuthors;

    @SerializedName("publisher")
    private String mPublisher;

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


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public List<String> getAuthors() {
        return mAuthors;
    }

    public void setAuthors(List<String> authors) {
        mAuthors = authors;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getPageCount() {
        return mPageCount;
    }

    public void setPageCount(int pageCount) {
        mPageCount = pageCount;
    }

    public String getPrintType() {
        return mPrintType;
    }

    public void setPrintType(String printType) {
        mPrintType = printType;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }

    public double getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(double averageRating) {
        mAverageRating = averageRating;
    }

    public int getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(int ratingCount) {
        mRatingCount = ratingCount;
    }

    public String getContentVersion() {
        return mContentVersion;
    }

    public void setContentVersion(String contentVersion) {
        mContentVersion = contentVersion;
    }

    public ImageLinks getImageLinks() {
        return mImageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        mImageLinks = imageLinks;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getPreviewLink() {
        return mPreviewLink;
    }

    public void setPreviewLink(String previewLink) {
        mPreviewLink = previewLink;
    }
}
