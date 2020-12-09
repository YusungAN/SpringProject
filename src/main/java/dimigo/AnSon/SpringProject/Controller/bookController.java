package dimigo.AnSon.SpringProject.Controller;

import dimigo.AnSon.SpringProject.BookForm;
import dimigo.AnSon.SpringProject.Domain.Book;
import dimigo.AnSon.SpringProject.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bookController {
    public int id = 1;
    private BookService bs;

    @PostMapping("/books/add")
    public String BookAdd(BookForm bf){
        Book b = new Book();
        b.setId(id++);
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublisher(bf.getPublisher());
        b.setPublicationYear(bf.getPublicationYear());

        bs.join(b);

        return "redirect/";
    }

}
