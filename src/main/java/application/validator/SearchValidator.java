package application.validator;

import application.entity.Book;

/**
 * Created by MIHONE on 4/10/2017.
 */

public class SearchValidator {

    private String search;
    private Book book;

    public SearchValidator(Book book, String search){
        this.book = book;
        this.search = search;
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
