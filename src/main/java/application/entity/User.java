package application.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;

/**
 * Created by MIHONE on 4/7/2017.
 */

@XmlRootElement(name = "User")
@XmlType(propOrder = { "id", "name", "email", "username", "password", "role" } )
public class User implements UserDetails{

    private long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;

    public User(){}

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @XmlAttribute(name = "id", required = true)
    public void setId(long id){ this.id = id; }

    @XmlElement(name = "name", required = true)
    public void setName(String name){ this.name = name; }

    @XmlElement(name = "email", required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "username", required = true)
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "password", required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "role", required = true)
    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.getRole());
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString(){
        return("ID: " + this.id + ", Name: " + this.name);
    }
}
