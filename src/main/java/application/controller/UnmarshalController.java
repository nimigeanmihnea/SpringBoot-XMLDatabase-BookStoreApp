package application.controller;

import application.entity.Book;
import application.entity.Sale;
import application.entity.User;
import application.repository.Books;
import application.repository.Sales;
import application.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Controller
public class UnmarshalController {

    @Autowired
    private Users users;

    @Autowired
    private Books books;

    @Autowired
    private Sales sales;

    private File file;

    public Users getUsers() throws JAXBException {

        file = new File("src/main/resources/entity/user.xml");
        if(file.exists() && !file.isDirectory()){
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            users = (Users)unmarshaller.unmarshal(file);
            return users;
        }
        else
            return null;
    }

    public Books getBooks() throws JAXBException {

        file = new File("src/main/resources/entity/book.xml");
        if(file.exists() && !file.isDirectory()){
            JAXBContext context = JAXBContext.newInstance(Books.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            books = (Books)unmarshaller.unmarshal(file);
            return books;
        }
        else
            return null;
    }

    public Sales getSales() throws JAXBException {

        file = new File("src/main/resources/entity/sale.xml");
        if(file.exists() && !file.isDirectory()){
            JAXBContext context = JAXBContext.newInstance(Sales.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            sales = (Sales) unmarshaller.unmarshal(file);
            return sales;
        }
        else
            return null;
    }
}
