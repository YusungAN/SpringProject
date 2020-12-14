package dimigo.AnSon.SpringProject.Repository;

import dimigo.AnSon.SpringProject.Domain.Book;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// DB 관련된 메소드들 있는 Repository
public class JDBCBookRepository implements BookRepository {

    private DataSource dataSource;

    public JDBCBookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 매개변수로 Book형 변수를 받아서 DB에 정보를 추가한다.
    @Override
    public Book save(Book book) {
        boolean cantsave = bookAlreadyExist(book.getBookName()); //입력받은 책 이름이 이미 존재하는지 확인한다.
        if (cantsave) {
            System.out.println(cantsave);
            Book b = new Book();
            b.setId(-2);
            return b;
        }
        String sql = String.format("insert into dimigo_book(book_name, author, publication_year, publisher) values('%s', '%s', '%d', '%s')",
                        book.getBookName(), book.getAuthor(), book.getPublicationYear(), book.getPublisher());
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 받아오기
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Statement 객체 받아오기
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // sql을 실행, 자동 생성된 id도 받아온다.
            rs = stmt.getGeneratedKeys();
            if (rs.next()){
                book.setId(rs.getInt(1)); // id 받아오기
            } else {
                System.out.println("ID 조회 실패");
            }
        } catch (SQLException e) {
            System.out.println("err ");
            System.out.println(e);
        } finally {
            closeConnection(conn, stmt, rs); // DB 연결 해제
        }

        return book;
    }

    //책 이름을 매개변수로 받아서 해당하는 책을 반환
    @Override
    public ArrayList<Book> findByBookName(String bookName) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where book_name = '%s'", bookName); // sql 작성
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 만들기
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeQuery(sql); // sql 실행

            while (rs.next()) { // 불러온 정보를 ArrayList에 저장 (사실 책 제목은 중복되는거 없음 ㅎㅎ)
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPublicationYear(rs.getInt(4));
                b.setPublisher(rs.getString(5));
                arr.add(b);
            }
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, rs); //DB 연결 해제
        }
        if (arr.size() != 0) return arr;
        else return null; // ArrayList에 원소가 있으면 arr을, 아니면 그냥 null을 반환
    }

    // 작가 이름을 매개변수로 받아서 이에 해당하는 책을 반환
    @Override
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where author = '%s'", author); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 생성
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeQuery(sql); // sql 실행

            while (rs.next()) { // 불러온 데이처 ArrayList로 만듦
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPublicationYear(rs.getInt(4));
                b.setPublisher(rs.getString(5));
                arr.add(b);
            }
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, rs); // DB 연결 해제
        }
        if (arr.size() != 0) return arr; // arr 반환
        else return null;
    }

    //출판 연도를 매개변수로 받아서 해당하는 책 정보 반환
    @Override
    public ArrayList<Book> findByPubYear(int year) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where publication_year = '%d'", year); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 생성
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); //Statement 객체 생성
            rs = stmt.executeQuery(sql); // sql 실행

            while (rs.next()) { // 불러온 데이터 ArrayList에 저장
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPublicationYear(rs.getInt(4));
                b.setPublisher(rs.getString(5));
                arr.add(b);
            }
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, rs);
        }
        if (arr.size() != 0) return arr; // arr 반환
        else return null;
    }

    // 출판사 이름을 매개변수로 받아 해당하는 책 정보 반환
    @Override
    public ArrayList<Book> findByPublisher(String publisher) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where publisher = '%s'", publisher); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeQuery(sql); // sql 실행

            while (rs.next()) { // 불러온 정보 ArrayList에 저장
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPublicationYear(rs.getInt(4));
                b.setPublisher(rs.getString(5));
                arr.add(b);
            }
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, rs);
        }
        if (arr.size() != 0) return arr; // arr 반환
        else return null;
    }

    // 수정된 책 정보를 매개변수로 받아서 수정
    @Override
    public void changeBookInfo(Book book) {
        String sql = String.format(
                "update dimigo_book set book_name = '%s', author = '%s', publication_year = '%d', publisher = '%s' where id = '%d'",
                book.getBookName(), book.getAuthor(), book.getPublicationYear(), book.getPublisher(), book.getId()); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 생성
        Statement stmt = null;
        int rs = 0;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeUpdate(sql); // sql 생성
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, null); // DB 닫기
        }
    }

    // 책 삭제 메소드
    @Override
    public void deleteBook(Book book) {
        String sql = String.format("delete from dimigo_book where id = '%d'", book.getId()); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 생성
        Statement stmt = null;
        int rs = 0;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeUpdate(sql); // sql 생성
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, null); // DB 해제
        }
    }

    // 입력받은 책 제목이 이미 존재하는지 확인하는 메소드
    public boolean bookAlreadyExist(String title) {
        String sql = String.format("select * from dimigo_book where book_name = '%s'", title); // sql
        Connection conn = DataSourceUtils.getConnection(dataSource); // Connection 객체 생성
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeQuery(sql); // sql 실행
            return rs.next(); // 불러와진게 있으면 true, 없으면 false 반환
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection(conn, stmt, null); // DB 닫기
        }
        return true;
    }

    // DB 연결 해제하는 메소드
    public void closeConnection(Connection c, Statement s, ResultSet r){
        if(r != null){
            try {
                r.close(); // ResultSet 해제
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(s != null){
            try {
                s.close(); // Statement 해제
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(c != null){
            try {
                c.close(); // Connection 해제
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
