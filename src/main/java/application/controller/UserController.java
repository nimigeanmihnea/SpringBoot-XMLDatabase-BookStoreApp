package application.controller;

import application.entity.User;
import application.repository.Users;
import application.validator.SearchValidator;
import application.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    private Users users;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String show(){ return "/admin/new"; }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String add(HttpServletRequest request) throws JAXBException {
        Random random = new Random();
        long id = random.nextInt(1000) + 1;
        User user = new User();
        user.setId(id);
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));

        UserValidator validator = new UserValidator(user);

        if(validator.validate()){
            UnmarshalController controller = new UnmarshalController();
            users = controller.getUsers();
            List<User> userList = users.getUsers();
            userList.add(user);
            users.setUsers(userList);
            MarshalController.setUsers(users);
            return "redirect:/admin";
        }else return "redirect:/error";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String view(@PathParam("search") String search, Model model) throws JAXBException {
        search = search.replaceAll("_", " ");
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        List<User> userList = users.getUsers();
        List<User> result = new ArrayList<User>();

        for(User user:userList){
            SearchValidator validator = new SearchValidator(user, search);
            if(validator.validateUser()){
                result.add(user);
            }
        }

        model.addAttribute("result", result);
        return "/admin/user";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String delete(@PathParam("param") String param) throws JAXBException {
        User user = null;
        long id = Long.parseLong(param);
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        List<User> usersList = users.getUsers();
        for(User aux:usersList){
            if(aux.getId() == id){
                user = aux;
                break;
            }
        }
        if(user != null){
            usersList.remove(user);
            users.setUsers(usersList);
            MarshalController.setUsers(users);
            return "redirect:/admin";
        }else return "redirect:/error";
    }
}
