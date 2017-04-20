package application.test;

import application.controller.MarshalController;
import application.controller.UnmarshalController;
import application.entity.User;
import application.repository.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;

/**
 * Created by Mihnea on 20/04/2017.
 */
public class UserTest {

    @Autowired
    private Users users = null;

    private List<User> userList = null;
    private String username = null;

    @Test
    public void read() throws JAXBException {
        String username = "admin";
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        userList = users.getUsers();
        User user = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                user = aux;
                break;
            }
        }
        assert (user.getUsername().equals(username));
    }

    @Test
    public void create() throws JAXBException {
        User user = new User();
        Random random = new Random();
        long id = random.nextInt(10000) + 1;
        user.setId(id);
        username = "Test"+id;
        user.setName("user");
        user.setUsername(username);
        user.setEmail(username);
        user.setPassword(username);
        user.setRole("ROLE_USER");

        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        userList = users.getUsers();
        userList.add(user);
        users.setUsers(userList);
        MarshalController.setUsers(users);

        User test = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                test = aux;
                break;
            }
        }
        assert (test.getUsername().equals(username));
    }

    @Test
    public void update() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        userList = users.getUsers();
        User test = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                test = aux;
                break;
            }
        }
        test.setEmail("test@email.com");
        users.setUsers(userList);
        MarshalController.setUsers(users);
        users = controller.getUsers();
        userList = users.getUsers();
        User user = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                user = aux;
                break;
            }
        }
        assert (user.getEmail().equals("test@email.com"));
    }

    @Test
    public void delete() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        userList = users.getUsers();
        User test = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                test = aux;
                break;
            }
        }
        userList.remove(test);
        users.setUsers(userList);
        MarshalController.setUsers(users);
        users = controller.getUsers();
        userList = users.getUsers();
        User user = null;
        for(User aux:userList){
            if(aux.getUsername().equals(username)){
                user = aux;
                break;
            }
        }
        assert (user == null);
    }
}
