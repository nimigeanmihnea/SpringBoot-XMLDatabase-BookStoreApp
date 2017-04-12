package application.repository;

import application.entity.Book;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Repository
@XmlRootElement(name = "Books")
public class Books {

    private List<Book> books = null;

    @XmlElement(name = "Book")
    public void setBooks(List<Book> books){
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
