package application.controller;

import application.entity.Book;
import application.repository.Books;
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


/**
 * Created by MIHONE on 4/8/2017.
 */

@Controller
@SuppressWarnings(value = "unchecked")
public class BookController {

    private File file = new File("src/main/resources/entity/book.xml");

    @Autowired
    private Books books;

    private static ArrayList<Book> bookArrayList = new ArrayList<Book>();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String convertToXml(HttpServletRequest request) throws JAXBException {

        if(file.exists() && ! file.isDirectory()) {
            JAXBContext context = JAXBContext.newInstance(Books.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //TODO: VERIFICA DACA NU EXISTA ID DE CARTE
            Book book = new Book();
            book.setId(Long.parseLong(request.getParameter("id")));
            book.setTitle(request.getParameter("title"));
            book.setAuthor(request.getParameter("author"));
            book.setGenre(request.getParameter("genre"));
            book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            book.setPrice(Float.parseFloat(request.getParameter("price")));

            Books books = new Books();
            books.setBooks(bookArrayList);

            books.getBooks().add(book);

            marshaller.marshal(books, file);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewBooks(@PathParam("search") String search, Model model){
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
        }else return "redirect/error";
    }
}
