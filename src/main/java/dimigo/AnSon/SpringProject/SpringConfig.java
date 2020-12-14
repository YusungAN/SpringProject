package dimigo.AnSon.SpringProject;

import dimigo.AnSon.SpringProject.Repository.BookRepository;
import dimigo.AnSon.SpringProject.Repository.JDBCBookRepository;
import dimigo.AnSon.SpringProject.Service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // DB 연결
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //BookService Bean 등록
    @Bean
    public BookService bookService(){
        return new BookService(bookRepository());
    }

    //BookRepository Bean 등록
    @Bean
    public BookRepository bookRepository() {
        return new JDBCBookRepository(dataSource);
    }

}
