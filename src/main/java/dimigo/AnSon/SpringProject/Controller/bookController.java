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

    // DI
    @Autowired
    public bookController(BookService bs) {
        this.bs = bs;
    }

    // 추가 페이지 라우팅
    @GetMapping("/add")
    public String AddPage() {
        return "add";
    }

    // 책 추가할 때 호출하는 것
    @PostMapping("/add")
    public String PostBook(BookForm bf) {
        Book b = new Book();
        System.out.println(bf.getBookName());
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublicationYear(bf.getPublicationYear());
        b.setPublisher(bf.getPublisher());
        int res = bs.join(b); // BookService의 join 메소드 사용
        System.out.println(res);
        if (res == -1) return "error"; // 에러 페이지
        else if (res == -2) return "alreadyexist"; // 존재하는 책 제목 입력 시 페이지
        else return "redirect:read"; // 기본 페이지
    }

    // 조회 페이지 라우팅
    @GetMapping("/read")
    public String ReadPage() {
        return "read";
    }

    // 검색할 때 부르는 것
    @GetMapping("/search")
    public String Search(@RequestParam("field") String field, @RequestParam("search_input") String content, Model model) {
        ArrayList<Book> arr;
        arr = bs.search(field, content); // BookService의 search 메소드 사용
        model.addAttribute("arr", arr); // view에 정보 전달
        return "read";
    }

    // 수정 페이지 라우팅
    @GetMapping("/modify")
    public String ModifyPage() {return "modify";}

    // 수정 페이지에서 수정할 책 검색할 때 호출하는 것
    @GetMapping("/modify/search")
    public String SearchModify(@RequestParam("field") String field, @RequestParam("search_input") String content, Model model){
        ArrayList<Book> info;
        info = bs.search(field, content);
        model.addAttribute("info", info);
        return "modify";
    }

    // 수정된 책 정보 post할 때 호출하는 것
    @PostMapping("/modify/mod")
    public String SearchModify(BookForm bf){
        Book b = new Book();
        b.setId(bf.getId());
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublisher(bf.getPublisher());
        b.setPublicationYear(bf.getPublicationYear());
        bs.modify(b);
        return "redirect:/modify";
    }

    // 삭제 페이지 라우팅
    @GetMapping("/delete")
    public String DeletePage() {
        return "delete";
    }

    // 삭제 페이지에서 삭제할 책 검색할 때 호출하는 것
    @GetMapping("/delete/search")
    public String SearchDelete(@RequestParam("field") String field, @RequestParam("search_input") String content, Model model){
        ArrayList<Book> info;
        info = bs.search(field, content);
        model.addAttribute("info", info);
        return "delete";
    }

    // 삭제 페이지에서 삭제할 책 보내줄 때 쓰는 것
    @PostMapping("/delete/del")
    public String DeleteBook(BookForm bf){
        Book b = new Book();
        b.setId(bf.getId());
        b.setBookName(bf.getBookName());
        b.setAuthor(bf.getAuthor());
        b.setPublisher(bf.getPublisher());
        b.setPublicationYear(bf.getPublicationYear());
        bs.delete(b);
        return "redirect:/delete";
    }

}
