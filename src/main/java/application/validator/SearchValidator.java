package application.validator;

import application.entity.Book;
import application.entity.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by MIHONE on 4/10/2017.
 */

public class SearchValidator {

    private String search;
    private Book book;
    private User user;

    public SearchValidator(Book book, String search){
        this.book = book;
        this.search = search;
    }
    public SearchValidator(User user, String search){
        this.user = user;
        this.search = search;
    }

    public boolean validateUser(){
        return this.containsUser() || this.lowerCaseUser() || this.upperCaseUser();
    }
    private boolean containsUser(){
        return user.getUsername().contains(search) || user.getEmail().contains(search) || user.getName().contains(search);
    }

    private boolean lowerCaseUser(){
        return user.getUsername().toLowerCase().contains(search) || user.getEmail().toLowerCase().contains(search) || user.getName().toLowerCase().contains(search);
    }

    private boolean upperCaseUser(){
        return user.getUsername().toUpperCase().contains(search) || user.getEmail().toUpperCase().contains(search) || user.getName().toUpperCase().contains(search);
    }

    public boolean validate(){
        return this.contains() || this.lowerCase() || this.upperCase();
    }
    private boolean contains(){
        return book.getTitle().contains(search) || book.getAuthor().contains(search) || book.getGenre().contains(search);
    }
    private boolean lowerCase(){
        return book.getTitle().toLowerCase().contains(search) || book.getAuthor().toLowerCase().contains(search) || book.getGenre().toLowerCase().contains(search);
    }

    private boolean upperCase(){
        return book.getTitle().toUpperCase().contains(search) || book.getAuthor().toUpperCase().contains(search) || book.getGenre().toUpperCase().contains(search);
    }

}
