package application.entity;

import application.misc.DateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.UUID;

/**
 * Created by MIHONE on 4/8/2017.
 */

@XmlRootElement(name = "Sale")
@XmlType(propOrder = { "id","date", "book", "quantity", "user", "buyer" } )
public class Sale {

    private UUID id;
    private String date;
    private Book book;
    private int quantity;
    private User user;
    private String buyer;

    public Sale(){}

    @XmlAttribute(name = "id", required = true)
    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement(name = "date", required = true)
    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "book", required = true)
    public void setBook(Book book) {
        this.book = book;
    }

    @XmlElement(name = "quantity", required = true)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "user", required = true)
    public void setUser(User user) {
        this.user = user;
    }

    @XmlElement(name = "buyer", required = true)
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public UUID getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getUser() {
        return user;
    }

    public String getBuyer() {
        return buyer;
    }
}
