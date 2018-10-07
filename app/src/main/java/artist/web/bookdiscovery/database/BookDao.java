package artist.web.bookdiscovery.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM books ORDER BY title ASC")
    List<BookWareHouse> getBookList();

    @Insert
    void insertBook(BookWareHouse bookEntry);

    @Delete
    void deleteBook(BookWareHouse bookEntry);
}
