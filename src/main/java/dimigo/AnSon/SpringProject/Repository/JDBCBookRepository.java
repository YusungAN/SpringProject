package dimigo.AnSon.SpringProject.Repository;

import dimigo.AnSon.SpringProject.Domain.Book;

import javax.sql.DataSource;
import java.util.ArrayList;

public class JDBCBookRepository implements BookRepository {

    private DataSource dataSource;

    public JDBCBookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public Book findByBookName(String bookName) {
        return null;
    }

    @Override
    public Book findByByAuthor(String Author) {
        return null;
    }

    @Override
    public void changeBookInfo() {

    }

    @Override
    public void deleteBook() {

    }

    @Override
    public ArrayList<Book> searchAll() {
        return null;
    }
}
