package application.test;

import application.controller.MarshalController;
import application.controller.UnmarshalController;
import application.entity.Book;
import application.repository.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;

/**
 * Created by Mihnea on 20/04/2017.
 */

public class BookTest {

    @Autowired
    private Books books;

    private List<Book> bookList;
    private String title = null;
    private String author = null;

    @Test
    public void read() throws JAXBException {
        String title = "The Shining";
        String author = "Stephen King";
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        bookList = books.getBooks();
        Book book = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase(title) && aux.getAuthor().equalsIgnoreCase(author)){
                book = aux;
                break;
            }
        }
        assert (book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title));
    }

    @Test
    public void create() throws JAXBException {
        Book book = new Book();
        Random random = new Random();
        long id = random.nextInt(10000) + 1;
        book.setId(id);
        title = "Test"+id;
        author = "Test"+id;
        book.setTitle("Test"+id);
        book.setAuthor("Test"+id);
        book.setGenre("Test");
        book.setQuantity(1);
        book.setPrice(1.0f);

        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        bookList = books.getBooks();
        bookList.add(book);
        books.setBooks(bookList);
        MarshalController.setBooks(books);

        Book test = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase("Test"+id) && aux.getAuthor().equalsIgnoreCase("Test"+id)){
                test = aux;
                break;
            }
        }
        assert (test.getAuthor().equalsIgnoreCase("Test"+id) && test.getTitle().equalsIgnoreCase("Test"+id));
    }

    @Test
    public void update() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        bookList = books.getBooks();
        Book test = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase(title) && aux.getAuthor().equalsIgnoreCase(author)){
                test = aux;
                break;
            }
        }
        test.setQuantity(5);
        books.setBooks(bookList);
        MarshalController.setBooks(books);
        books = controller.getBooks();
        bookList = books.getBooks();
        Book book = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase(title) && aux.getAuthor().equalsIgnoreCase(author)){
                book = aux;
                break;
            }
        }
        assert (book.getQuantity() == 5);
    }

    @Test
    public void delete() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        bookList = books.getBooks();
        Book test = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase(title) && aux.getAuthor().equalsIgnoreCase(author)){
                test = aux;
                break;
            }
        }
        bookList.remove(test);
        books.setBooks(bookList);
        MarshalController.setBooks(books);
        books = controller.getBooks();
        bookList = books.getBooks();
        Book book = null;
        for(Book aux:bookList){
            if(aux.getTitle().equalsIgnoreCase(title) && aux.getAuthor().equalsIgnoreCase(author)){
                book = aux;
                break;
            }
        }
        assert (book == null);
    }
}
