package application.controller;

import application.entity.Book;
import application.repository.Books;
import application.validator.BookValidator;
import application.validator.SearchValidator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.websocket.server.PathParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by MIHONE on 4/8/2017.
 */

@Controller
@SuppressWarnings(value = "unchecked")
public class BookController {

    @Autowired
    private Books books;

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String show(){ return "/admin/add"; }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request) throws JAXBException {
        Random random = new Random();
        long id = random.nextInt(1000) + 1;
        Book book = new Book();
        book.setId(id);
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setGenre(request.getParameter("genre"));
        book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        book.setPrice(Float.parseFloat(request.getParameter("price")));

        BookValidator validator = new BookValidator(book);

        if(validator.validate()){
            UnmarshalController controller = new UnmarshalController();
            books = controller.getBooks();
            List<Book> bookList = books.getBooks();
            bookList.add(book);
            books.setBooks(bookList);
            MarshalController.setBooks(books);
            return "redirect:/admin";
        }else return "redirect:/error";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@PathParam("search") String search, Model model){
        search = search.replaceAll("_", " ");
        try{
            UnmarshalController controller = new UnmarshalController();
            books = controller.getBooks();
        }catch (JAXBException e){}

        List<Book> result = new ArrayList<Book>();
        List<Book> bookList = books.getBooks();

        for (Book aux:bookList) {
            SearchValidator validator = new SearchValidator(aux, search);
            if (validator.validate()) {
                result.add(aux);
            }
        }

        model.addAttribute("result",result);
        return "/view";
    }

    @RequestMapping(value = "/admin/view", method = RequestMethod.GET)
    public String viewBooksAdmin(@PathParam("search") String search, Model model){
        search = search.replaceAll("_", " ");
        try{
            UnmarshalController controller = new UnmarshalController();
            books = controller.getBooks();
        }catch (JAXBException e){}

        List<Book> result = new ArrayList<Book>();
        List<Book> bookList = books.getBooks();

        for (Book aux:bookList) {
            SearchValidator validator = new SearchValidator(aux, search);
            if (validator.validate()) {
                result.add(aux);
            }
        }

        model.addAttribute("result",result);
        return "/admin/view";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String delete(@PathParam("param") String param) throws JAXBException {
        Book book = null;
        long id = Long.parseLong(param);
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        List<Book> bookList = books.getBooks();
        for (Book aux: bookList) {
            if(aux.getId() == id) {
                book = aux;
                break;
            }
        }
        if(book != null){
            bookList.remove(book);
            books.setBooks(bookList);
            MarshalController.setBooks(books);
            return "redirect:/admin";
        }else return "redirect:/error";
    }
}
