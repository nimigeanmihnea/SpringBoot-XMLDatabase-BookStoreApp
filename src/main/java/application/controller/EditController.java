package application.controller;

import application.entity.Book;
import application.repository.Books;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihnea on 14/04/2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class EditController {

    @Autowired
    private Books books;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String show(@PathParam("param") String param, Model model) throws JAXBException {

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
            model.addAttribute("book", book);
            return "/admin/edit";
        }
        return "redirect:/error";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request) throws JAXBException {
        Book book = null;
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();

        List<Book> bookList = books.getBooks();
        for (Book aux:bookList) {
            if(aux.getAuthor().equals(request.getParameter("authorh")) && aux.getTitle().equals(request.getParameter("titleh"))){
                book = aux;
                break;
            }
        }
        if(book != null) {
            book.setQuantity(Integer.parseInt(request.getParameter("quantityh")));
            book.setPrice(Float.parseFloat(request.getParameter("priceh")));
            books.setBooks(bookList);
            MarshalController.setBooks(books);
            return "redirect:/admin";
        }else return "redirect:/error";
    }
}
