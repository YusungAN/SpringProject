package dimigo.AnSon.SpringProject.Service;

import dimigo.AnSon.SpringProject.Domain.Book;
import dimigo.AnSon.SpringProject.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int join(Book b){
        try {
            Book res = bookRepository.save(b);
            if (res != null) return res.getId();
            else return -2;
        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Book> search(String field, String content) {
        switch (field) {
            case "title":
                return bookRepository.findByBookName(content);
            case "author":
                return bookRepository.findByAuthor(content);
            case "publisher":
                return bookRepository.findByPublisher(content);
            case "publication_year":
                return bookRepository.findByPubYear(Integer.parseInt(content));
            default:
                System.out.println("정보를 조회할 수 없습니다.");
        }
        return null;
    }

    //그냥 일단 만들어둠
    public void modify(Book book) {
        bookRepository.changeBookInfo(book);
    }

    public void delete(Book book) {
        bookRepository.deleteBook(book);
    }

}
