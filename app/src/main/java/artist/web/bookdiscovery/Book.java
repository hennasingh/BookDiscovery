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
