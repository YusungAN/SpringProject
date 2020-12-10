package dimigo.AnSon.SpringProject.Controller;

import dimigo.AnSon.SpringProject.BookForm;
import dimigo.AnSon.SpringProject.Domain.Book;
import dimigo.AnSon.SpringProject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class bookController {
    public int id = 1;
    private BookService bs;

    @Autowired
    public bookController(BookService bs) {
        this.bs = bs;
    }

    @GetMapping("/add")
    public String AddPage() {
        return "add";
    }

    @PostMapping("/add")
    public String PostBook(BookForm bf) {
        Book b = new Book();
        System.out.println(bf.getBookName());
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublicationYear(bf.getPublicationYear());
        b.setPublisher(bf.getPublisher());
        int res = bs.join(b);
        System.out.println(res);
        if (res != -1) return "redirect:read";
        else return "error";
    }

    @GetMapping("/read")
    public String ReadPage() {
        return "read";
    }

    @GetMapping("/search")
    public String Search(@RequestParam("field") String field, @RequestParam("book_title") String content, Model model) {
        ArrayList<Book> arr;
        arr = bs.search(field, content);
        model.addAttribute("arr", arr);
        return "read";
    }

    @GetMapping("/modify")
    public String ModifyPage() {
        return "modify";
    }

    @GetMapping("/delete")
    public String DeletePage() {
        return "delete";
    }

    @PostMapping("/books/add")
    public String BookAdd(BookForm bf){
        Book b = new Book();
        b.setId(id++);
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublisher(bf.getPublisher());
        b.setPublicationYear(bf.getPublicationYear());

        bs.join(b);

        return "redirect:/";
    }

}
