package dimigo.AnSon.SpringProject.Repository;

import dimigo.AnSon.SpringProject.Domain.Book;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCBookRepository implements BookRepository {

    private DataSource dataSource;

    public JDBCBookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book save(Book book) {
        String sql = String.format("insert into dimigo_book values('%s', '%s', '%d', '%s')", book.getBookName(), book.getAuthor(), book.getPublicationYear(), book.getPublisher());
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            if (rs.next()){
                book.setId(rs.getInt(1));
            } else {
                System.out.println("ID 조회 실패");
            }
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, rs);
        }

        return book;
    }

    @Override
    public ArrayList<Book> findByBookName(String bookName) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where book_name = '%s'", bookName);
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
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
        if (arr.size() != 0) return arr;
        else return null;
    }

    @Override
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where author = '%s'", author);
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
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
        if (arr.size() != 0) return arr;
        else return null;
    }

    @Override
    public ArrayList<Book> findByPubYear(int year) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where publication_year = '%d'", year);
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
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
        if (arr.size() != 0) return arr;
        else return null;
    }

    @Override
    public ArrayList<Book> findByPublisher(String publisher) {
        ArrayList<Book> arr = new ArrayList<Book>();
        String sql = String.format("select * from dimigo_book where publisher = '%s'", publisher);
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
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
        if (arr.size() != 0) return arr;
        else return null;
    }

    @Override
    public void changeBookInfo(Book book) {
        String sql = String.format(
                "update dimigo_book set book_name = '%s', author = '%s', publication_year = '%d', publisher = '%s' where id = '%d'",
                book.getBookName(), book.getAuthor(), book.getPublicationYear(), book.getPublisher(), book.getId());
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        int rs = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, null);
        }
    }

    @Override
    public void deleteBook(Book book) {
        String sql = String.format("delete from dimigo_book where id = '%d'", book.getId());
        Connection conn = null;
        conn = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        int rs = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
        } finally {
            closeConnection(conn, stmt, null);
        }
    }

    public void closeConnection(Connection c, Statement s, ResultSet r){
        if(r != null){
            try {
                r.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(s != null){
            try {
                s.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(c != null){
            try {
                c.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
