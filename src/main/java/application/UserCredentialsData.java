package application;

import application.controller.UnmarshalController;
import application.entity.User;
import application.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;


/**
 * Created by MIHONE on 4/8/2017.
 */

@Service
public class UserCredentialsData implements UserDetailsService {

    @Autowired
    private Users users;

    @Autowired
    public UserCredentialsData(){}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UnmarshalController controller = new UnmarshalController();
            users = controller.getUsers();
        }catch (JAXBException e){}

        List<User> usersList = users.getUsers();
        User user = null;
        for (User aux:usersList) {
            if(aux.getUsername().equals(username))
                user = aux;
        }

        user.isEnabled();
        return new User(user.getUsername(),user.getPassword(),user.getRole());
    }
}
