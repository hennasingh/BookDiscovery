package artist.web.bookdiscovery.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "books")
public class BookWareHouse {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;

    @ColumnInfo(name = "date_published")
    private String publishedDate;

    private String language;

    private String author;

    public BookWareHouse(@NonNull String id, String title, String publishedDate, String language, String author) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.language = language;
        this.author = author;
    }
}
