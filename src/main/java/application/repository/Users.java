package application.repository;



import application.entity.User;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Repository
@XmlRootElement(name = "Users")
public class Users{

    private List<User> users;

    @XmlElement(name = "User")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

//    public User getUser(String username){
//        for (User user:this.users) {
//            if(user.getUsername().equals(username))
//                return user;
//        }
//        return null;
//    }
}
