package dimigo.AnSon.SpringProject.Repository;

import dimigo.AnSon.SpringProject.Domain.Book;

import java.util.ArrayList;

public interface BookRepository {

    void save(Book book);
    Book findByBookName(String bookName);
    Book findByByAuthor(String Author);
    void changeBookInfo();
    void deleteBook();
    ArrayList<Book> searchAll();
}
