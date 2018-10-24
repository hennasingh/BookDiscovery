package artist.web.bookdiscovery.database;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BookWareHouseTest {

    BookDao mBookDao;
    BookDatabase mBookDatabase;

    @Before
    public void setUp() {
        mBookDatabase=BookDatabase.getInstance(InstrumentationRegistry.getTargetContext(), true);
        mBookDao=mBookDatabase.getBookDav();
    }
    @After
    public void tearDown() {
        mBookDatabase.close();
    }

    @Test
    public void basics(){
        assertEquals(0,mBookDao.getBookList().size());

        BookWareHouse book = new BookWareHouse("37366","Java","July 4,2006",
                "eng","Herbert");

        assertNotNull(book.getAuthor());
        assertNotEquals(0,book.getAuthor().length());

        mBookDao.insertBook(book);

        assertBookHouse(mBookDao,book);

        mBookDao.deleteBook(book);

        assertEquals(0, mBookDao.getBookList().size());
    }

    private void assertBookHouse(BookDao bookDao, BookWareHouse book) {
        List<BookWareHouse> results = mBookDao.getBookList();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertTrue(areIdentical(book, results.get(0)));


    }

    private boolean areIdentical(BookWareHouse one, BookWareHouse two) {
        return(one.getId().equals(two.getId())&&
        one.getTitle().equals(two.getTitle())&&
        one.getAuthor().equals(two.getAuthor())&&
        one.getLanguage().equals(two.getLanguage())&&
        one.getPublishedDate().equals(two.getPublishedDate())
        );
    }

}