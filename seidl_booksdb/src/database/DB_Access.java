/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Book;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;
    private final String ins = "INSERT INTO student (catlognr, firstname, lastname, dateofbirth)\n"
            + "VALUES(?, ?, ?, ?)";
    private PreparedStatement insertS = null;

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    private DB_Access() {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void connect() throws SQLException {
        db.connect();
    }

    public void disconnect() throws SQLException {
        db.disconnect();
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        String sqlString = "SELECT b.book_id, b.title, b.isbn, b.total_pages, b.rating, p.name, b.published_date\n"
                + "FROM books b \n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "ORDER BY b.title";
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            String publisher = rs.getString("name");
            List<String> authors = getAuthorsList(book_id);
            LocalDate date = rs.getDate("published_date").toLocalDate();

            String genre = null;
            try {
                genre = getGenresForBook(book_id).get(0);
            } catch (IndexOutOfBoundsException ex) {
            }

            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        db.releaseStatement(statement);

        return books;
    }

    private List<String> getAuthorsList(int book_id) throws SQLException {
        List<String> authors = new ArrayList<>();
        String sqlString = String.format(
                "SELECT a.first_name, a.middle_name, a.last_name \n"
                + "FROM authors a \n"
                + "WHERE a.author_id IN \n"
                + "(\n"
                + "	SELECT author_id from book_authors ba WHERE ba.book_id = %d\n"
                + ");",
                book_id);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            String middlename = rs.getString("middle_name");
            String author = "";
            if (middlename != null) {
                if (lastname != null) {
                    author = firstname + " " + middlename + " " + lastname;
                } else {
                    author = firstname;
                }
            } else {
                author = firstname + " " + lastname;
            }
            authors.add(author);
        }
        db.releaseStatement(statement);
        return authors;
    }

    public List<String> getGenresForBook(int book_id) throws SQLException {
        List<String> genres = new ArrayList<>();
        String sqlString = String.format("SELECT g.genre FROM genres g WHERE genre_id IN\n"
                + "(\n"
                + "	SELECT bg.genre_id FROM book_genres bg WHERE bg.book_id=%d\n"
                + ")", book_id);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String genre = rs.getString("genre");
            genres.add(genre);
        }
        db.releaseStatement(statement);
        return genres;
    }

    public List<String> getGenres() throws SQLException {
        List<String> genres = new ArrayList<>();
        String sqlString = String.format("SELECT genre FROM genres ORDER BY genre");
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String genre = rs.getString("genre");
            if (!genre.equals("Genres")) {
                genres.add(genre);
            }
        }
        db.releaseStatement(statement);
        return genres;
    }

    public List<String> getPublishers() throws SQLException {
        List<String> publishers = new ArrayList<>();
        String sqlString = String.format("SELECT name FROM publishers ORDER BY name");
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String publisher = rs.getString("name");
            publishers.add(publisher);
        }
        db.releaseStatement(statement);
        return publishers;
    }

    public List<Book> getBooksForPublisher(String publisher) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sqlString = String.format("SELECT b.book_id, b.title, b.isbn, b.total_pages, b.rating, b.published_date\n"
                + "FROM books b \n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE p.name = '%s'"
                + "ORDER BY b.title", publisher);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            List<String> authors = getAuthorsList(book_id);
            LocalDate date = rs.getDate("published_date").toLocalDate();
            String genre = null;
            try {
                genre = getGenresForBook(book_id).get(0);
            } catch (IndexOutOfBoundsException ex) {
            }

            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        db.releaseStatement(statement);

        return books;
    }

    public List<Book> getBooksForGenre(String genre) throws SQLException {
        List<Book> books = new ArrayList<>();

        String sqlString = String.format("SELECT b.book_id, b.title, b.isbn, b.total_pages, b.rating, p.name, b.published_date\n"
                + "FROM books b \n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE b.book_id IN \n"
                + "(\n"
                + "	SELECT bg.book_id \n"
                + "	FROM book_genres bg \n"
                + "	WHERE bg.genre_id =\n"
                + "	(\n"
                + "		SELECT g.genre_id\n"
                + "		FROM genres g\n"
                + "		WHERE g.genre = '%s'\n"
                + "	)\n"
                + ")\n"
                + "ORDER BY b.title", genre);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            String publisher = rs.getString("name");
            List<String> authors = getAuthorsList(book_id);
            LocalDate date = rs.getDate("published_date").toLocalDate();

            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        db.releaseStatement(statement);

        return books;
    }

    public List<Book> getBooksForGenreAndPublisher(String genre, String publisher) throws SQLException {
        List<Book> books = new ArrayList<>();

        String sqlString = String.format("SELECT b.book_id, b.title, b.isbn, b.total_pages, b.rating, b.published_date\n"
                + "FROM books b \n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE b.book_id IN \n"
                + "(\n"
                + "	SELECT bg.book_id \n"
                + "	FROM book_genres bg \n"
                + "	WHERE bg.genre_id =\n"
                + "	(\n"
                + "		SELECT g.genre_id\n"
                + "		FROM genres g\n"
                + "		WHERE g.genre = '%s'\n"
                + "	)\n"
                + ") AND p.name='%s'\n"
                + "ORDER BY b.title", genre, publisher);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            List<String> authors = getAuthorsList(book_id);
            LocalDate date = rs.getDate("published_date").toLocalDate();

            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        db.releaseStatement(statement);

        return books;
    }

    public List<String> getGenresForPublisher(String publisher) throws SQLException {
        List<String> genres = new ArrayList<>();

        String sqlString = String.format(
                "SELECT g.genre\n"
                + "FROM books b\n"
                + "INNER JOIN book_genres bg ON bg.book_id = b.book_id\n"
                + "INNER JOIN genres g ON g.genre_id = bg.genre_id\n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE p.name='%s'"
                        + "ORDER BY g.genre", publisher);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String genre = rs.getString("genre");
            genres.add(genre);
        }
        db.releaseStatement(statement);
        Set<String> set2RemoveDuplicates = new HashSet<>(genres);
        genres = new ArrayList<>(set2RemoveDuplicates);
        return genres;
    }

    public List<String> getPublishersForGenre(String genre) throws SQLException {
        List<String> publishers = new ArrayList<>();

        String sqlString = String.format("SELECT p.name\n"
                + "FROM books b\n"
                + "INNER JOIN book_genres bg ON bg.book_id = b.book_id\n"
                + "INNER JOIN genres g ON g.genre_id = bg.genre_id\n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE g.genre='%s'"
                + "ORDER BY p.name", genre);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String publisher = rs.getString("name");
            publishers.add(publisher);
        }
        db.releaseStatement(statement);
        Set<String> set2RemoveDuplicates = new HashSet<>(publishers);
        publishers = new ArrayList<>(set2RemoveDuplicates);
        return publishers;
    }

    public List<Book> searchForAuthor(String authorString) throws SQLException {
        List<Book> books = new ArrayList<>();
        authorString = authorString.replace(" ", "").toUpperCase();
        String sqlString = String.format(
                "SELECT DISTINCT b.book_id, b.title, b.isbn, b.total_pages, b.rating, b.published_date, p.name\n"
                + "FROM books b\n"
                + "INNER JOIN book_authors ba ON ba.book_id = b.book_id\n"
                + "INNER JOIN authors a ON ba.author_id = a.author_id\n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE UPPER(REPLACE(CONCAT(a.first_name, a.middle_name, a.last_name),' ','')) LIKE '%%%s%%'\n"
                + "ORDER BY b.title;", authorString);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            List<String> authors = getAuthorsList(book_id);
            Set<String> set = new HashSet<>(authors);
            authors = new ArrayList<>(set);
            LocalDate date = rs.getDate("published_date").toLocalDate();
            String publisher = rs.getString("name");
            String genre = null;
            try {
                genre = getGenresForBook(book_id).get(0);
            } catch (IndexOutOfBoundsException ex) {
            }
            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        Set<Book> setToRemoveDuplicates = new HashSet<>(books);
        books = new ArrayList<>(setToRemoveDuplicates);
        return books;
    }

    public List<Book> searchForTitle(String title) throws SQLException {
        List<Book> books = new ArrayList<>();
        title = title.replace(" ", "").toUpperCase();
        String sqlString = String.format(
                "SELECT b.title, b.book_id, b.isbn, b.total_pages, b.rating, b.published_date, p.name\n"
                + "FROM books b\n"
                + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
                + "WHERE UPPER(b.title) LIKE '%%%s%%'\n"
                + "ORDER BY b.title;", title);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            title = rs.getString("title");
            String isbnNr = rs.getString("isbn");
            int total_pages = rs.getInt("total_pages");
            float rating = rs.getFloat("rating");
            List<String> authors = getAuthorsList(book_id);
            Set<String> set = new HashSet<>(authors);
            authors = new ArrayList<>(set);
            LocalDate date = rs.getDate("published_date").toLocalDate();
            String publisher = rs.getString("name");
            String genre = null;
            try {
                genre = getGenresForBook(book_id).get(0);
            } catch (IndexOutOfBoundsException ex) {
            }
            Book book = new Book(title, isbnNr, authors, publisher, total_pages, rating, genre, date);
            books.add(book);
        }
        return books;
    }

    public static void main(String[] args) {
        DB_Access dba = DB_Access.getInstance();
        try {
            dba.connect();
            dba.getAllBooks();
            List<Book> books = dba.searchForAuthor("ARY");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
