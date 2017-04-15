package application.validator;

import application.controller.UnmarshalController;
import application.entity.User;
import application.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Mihnea on 15/04/2017.
 */
public class UserValidator {

    private User user;

    @Autowired
    private Users users;

    public UserValidator(User user){
        this.user = user;
    }

    public boolean validate() throws JAXBException {
        UnmarshalController controller = new UnmarshalController();
        users = controller.getUsers();
        List<User> userList = users.getUsers();
        for (User aux:userList) {
            if(this.user.getId() == aux.getId() || this.user.getEmail().equalsIgnoreCase(aux.getEmail()) || this.user.getUsername().equalsIgnoreCase(aux.getUsername()))
                return false;
        }
        return true;
    }
}
