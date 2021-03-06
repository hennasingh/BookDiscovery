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

package artist.web.bookdiscovery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BookWareHouse.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {


    private static final String DB_NAME = "booklist.db";
    private static volatile BookDatabase sBookDatabase;

    static synchronized BookDatabase getInstance(Context context, Boolean inMemory) {
        if (sBookDatabase == null) {
            sBookDatabase = createInstance(context, inMemory);
        }
        return sBookDatabase;
    }

    private static BookDatabase createInstance(Context context, Boolean inMemory) {
        RoomDatabase.Builder<BookDatabase> database;

        if(inMemory){
            database = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),BookDatabase.class);
        }else{
            database = Room.databaseBuilder(context.getApplicationContext(),BookDatabase.class,DB_NAME);
        }
        return database.fallbackToDestructiveMigration().build();
    }


    public abstract BookDao getBookDav();
}
