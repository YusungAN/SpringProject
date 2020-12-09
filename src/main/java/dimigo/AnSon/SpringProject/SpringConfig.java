package dimigo.AnSon.SpringProject;

import dimigo.AnSon.SpringProject.Repository.BookRepository;
import dimigo.AnSon.SpringProject.Repository.JDBCBookRepository;
import dimigo.AnSon.SpringProject.Service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BookService bookService(){
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        return new JDBCBookRepository(dataSource);
    }

}
