package application.controller;

import application.entity.User;
import application.repository.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class UserController {

    private File file = new File("src/main/resources/entity/user.xml");
    private static Users users = new Users();
    private static ArrayList<User> userArrayList = new ArrayList<User>();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String convertToXml(HttpServletRequest request) throws JAXBException {

        if(file.exists() && ! file.isDirectory()) {
            JAXBContext context = JAXBContext.newInstance(Users.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //TODO: VERIFICA DACA NU EXISTA ID DE CARTE
            User user = new User();
            user.setId(Long.parseLong(request.getParameter("id")));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setRole("ROLE_USER");

            Users users = new Users();
            users.setUsers(userArrayList);

            users.getUsers().add(user);

            marshaller.marshal(users, file);
        }
        return "redirect:/home";
    }
}
