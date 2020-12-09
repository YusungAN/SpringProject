package dimigo.AnSon.SpringProject.Repository;

import dimigo.AnSon.SpringProject.Domain.Book;

import java.util.ArrayList;

public interface BookRepository {

    Book save(Book book);
    ArrayList<Book> findByBookName(String bookName);
    ArrayList<Book> findByAuthor(String author);
    ArrayList<Book> findByPubYear(int year);
    ArrayList<Book> findByPublisher(String publisher);
    void changeBookInfo(Book book);
    void deleteBook(Book book);
    //ArrayList<Book> searchAll();
}
