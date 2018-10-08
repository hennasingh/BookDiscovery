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

package artist.web.bookdiscovery;

/**
 * Created by User on 7/9/2017.
 */

public class Book {

    private String title;
    private String author;
    private String imageThumbnail;
    private String bookPublisher;
    private String bookPublishedDate;
    private String infoUrl;


    public Book(String title, String author, String bookPublisher, String bookPublishedDate, String infoUrl,
                String imageThumbnail) {
        this.title = title;
        this.author = author;
        this.imageThumbnail = imageThumbnail;
        this.bookPublisher = bookPublisher;
        this.bookPublishedDate = bookPublishedDate;
        this.infoUrl = infoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public String getInfoUrl() {
        return infoUrl;
    }


    public String getBookPublisher() {
        return bookPublisher;
    }

    public String getBookPublishedDate() {
        return bookPublishedDate;
    }
}
