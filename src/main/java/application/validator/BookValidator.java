package application.validator;

import application.controller.UnmarshalController;
import application.entity.Book;
import application.repository.Books;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Mihnea on 15/04/2017.
 */
public class BookValidator {

    private Book book;

    @Autowired
    private Books books;

    public BookValidator(Book book){
        this.book = book;
    }

    public boolean validate() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        List<Book> bookList = books.getBooks();
        for (Book aux:bookList) {
            if(this.book.getId() == aux.getId() || (this.book.getTitle().equals(aux.getTitle()) && this.book.getAuthor().equals(aux.getAuthor())))
                return false;
        }
        return true;
    }
}
