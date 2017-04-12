package application.entity;

import javax.xml.bind.annotation.*;

/**
 * Created by MIHONE on 4/7/2017.
 */

@XmlRootElement(name = "Book")
@XmlType(propOrder = { "id", "title", "author", "genre", "quantity", "price" } )
public class Book {

    private long id;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private float price;

    public Book(){}

    @XmlAttribute(name = "id", required = true)
    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "title", required = true)
    public void setTitle(String title){
        this.title = title;
    }

    @XmlElement(name = "author", required = true)
    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement(name = "genre", required = true)
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "quantity", required = true)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "price", required = true)
    public void setPrice(float price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "ID: " + this.getId() + ", Title: " + this.getTitle() +
                ", Author: " + this.getAuthor() + ", Genre: " + this.getGenre() +
                ", Quantity: " + this.getQuantity() + ", Price: " + this.getPrice();
    }
}
