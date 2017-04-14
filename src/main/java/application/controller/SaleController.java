package application.controller;

import application.entity.Book;
import application.entity.Sale;
import application.entity.User;
import application.misc.DateAdapter;
import application.repository.Books;
import application.repository.Sales;
import application.repository.Users;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.websocket.server.PathParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Controller
@RequestMapping(value = "/sell")
public class SaleController {

    @Autowired
    private Books books;

    @Autowired
    private Users users;

    @Autowired
    private Sales sales;

    @RequestMapping(method = RequestMethod.GET)
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
            return "/sell";
        }
        return "redirect:/error";
    }

   @RequestMapping(method = RequestMethod.POST)
    public String sell(HttpServletRequest request) throws JAXBException {
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
           User user = null;
           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           String username = auth.getName();
           users = controller.getUsers();
           List<User> userList = users.getUsers();
           for (User aux:userList) {
               if(aux.getUsername().equals(username)) {
                   user = aux;
                   break;
               }
           }

           int bookStock =Integer.parseInt(request.getParameter("sellQuantity"));
           book.setQuantity(book.getQuantity() - bookStock);
           DateFormat df = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
           Sale sale = new Sale();
           sale.setId(UUID.randomUUID());
           sale.setDate(df.format(new Date()).toString());
           sale.setBook(book);
           sale.setQuantity(bookStock);
           sale.setUser(user);
           sale.setBuyer(request.getParameter("deliver"));

           sales = controller.getSales();
           List<Sale> saleList = sales.getSales();
           saleList.add(sale);
           sales.setSales(saleList);
           books.setBooks(bookList);

           MarshalController.setBooks(books);
           MarshalController.setSales(sales);

           return "redirect:/home";
       }

       return "redirect:/error";
   }

}
