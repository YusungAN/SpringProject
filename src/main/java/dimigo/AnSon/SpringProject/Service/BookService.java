package dimigo.AnSon.SpringProject.Service;

import dimigo.AnSon.SpringProject.Domain.Book;
import dimigo.AnSon.SpringProject.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int join(Book b){

        bookRepository.save(b);
        return b.getId();
    }

}
