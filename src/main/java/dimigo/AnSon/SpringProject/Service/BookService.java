package dimigo.AnSon.SpringProject.Service;

import dimigo.AnSon.SpringProject.Domain.Book;
import dimigo.AnSon.SpringProject.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

// 상세한 비즈니스 로직
public class BookService {
    private BookRepository bookRepository;

    // DI
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책정보 추가 메소드, BookRepository의 save 메소드 활용
    public int join(Book b){
        try {
            Book res = bookRepository.save(b);
            if (res != null) return res.getId();
            else return -2; // 같은 책 제목 입력 때만 특별히 따로 처리
        } catch (Exception e) {
            return -1;
        }
    }

    // 검색 메소드, BookRepository의 findBy~~ 메소드 활용
    public ArrayList<Book> search(String field, String content) {
        switch (field) { // 어떤 필드로 찾는지 받은 후 그에 맞는 메소드 호출
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

    // 책 정보 수정하는 메소드, BookRepository의 changeBookInfo 활용
    public void modify(Book book) {
        bookRepository.changeBookInfo(book);
    }

    // 책 정보 삭제하는 메소드, BookRepository의 deleteBook 활용
    public void delete(Book book) {
        bookRepository.deleteBook(book);
    }

}
