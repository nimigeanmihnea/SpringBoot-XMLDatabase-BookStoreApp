package application.controller;

import application.repository.Books;
import application.repository.Sales;
import application.repository.Users;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by MIHONE on 4/11/2017.
 */

@Controller
public class MarshalController {

    public static void setBooks(Books books) throws JAXBException {
        File file = new File("src/main/resources/entity/book.xml");
        JAXBContext context = JAXBContext.newInstance(Books.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if(file.exists() && !file.isDirectory()){
            marshaller.marshal(books,file);
        }
    }

    public static void setUsers(Users users) throws JAXBException {
        File file = new File("src/main/resources/entity/user.xml");
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if(file.exists() && !file.isDirectory()){
            marshaller.marshal(users,file);
        }
    }

    public static void setSales(Sales sales) throws JAXBException {
        File file = new File("src/main/resources/entity/sale.xml");
        JAXBContext context = JAXBContext.newInstance(Sales.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if(file.exists() && !file.isDirectory()){
            marshaller.marshal(sales,file);
        }
    }
}
