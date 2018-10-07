package artist.web.bookdiscovery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BookWareHouse.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {


    private static final String DB_NAME = "booklist.db";
    private static volatile BookDatabase sBookDatabase;

    static synchronized BookDatabase getInstance(Context context) {
        if (sBookDatabase == null) {
            sBookDatabase = createInstance(context);
        }
        return sBookDatabase;
    }

    private static BookDatabase createInstance(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                BookDatabase.class,
                DB_NAME
        ).build();
    }


    public abstract BookDao getBookDav();
}
