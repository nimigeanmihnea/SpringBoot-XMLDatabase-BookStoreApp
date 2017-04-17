package application.report;

import application.controller.UnmarshalController;
import application.entity.Book;
import application.repository.Books;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihnea on 15/04/2017.
 */

public class CSVReport implements Report {

    @Autowired
    private Books books;

    private static final char DEFAULT_SEPARATOR = ',';
    private final String file = "src/main/resources/report/CSVReport.csv";

    @Override
    public void generate() {
        try {
            FileWriter writer = new FileWriter(file);
            UnmarshalController controller = new UnmarshalController();
            books = controller.getBooks();
            List<Book> bookList = books.getBooks();
            for(Book book:bookList){
                if(book.getQuantity() == 0){
                    List<String> values = new ArrayList<String>();
                    values.add(book.getId()+"");
                    values.add(book.getAuthor());
                    values.add(book.getTitle());
                    values.add(book.getGenre());
                    values.add(book.getQuantity()+"");
                    values.add(book.getPrice()+"");
                    writeLine(writer,values);
                }
            }
            writer.flush();
            writer.close();
        }catch (Exception e){ e.printStackTrace(); }

    }

    public static void writeLine(Writer writer, List<String> values) throws IOException{
        writeLine(writer,values,DEFAULT_SEPARATOR,' ');
    }

    public static void writeLine(Writer writer, List<String> values, char separators) throws IOException{
        writeLine(writer,values,separators,' ');
    }

    public static void writeLine(Writer writer, List<String> values, char separators, char quote) throws IOException{
        boolean first = true;
        if(separators == ' '){
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String value:values){
            if(!first){
                stringBuilder.append(separators);
            }
            if(quote == ' '){
                stringBuilder.append(csvFormat(value));
            } else{
                stringBuilder.append(quote).append(csvFormat(value)).append(quote);
            }
            first = false;
        }
        stringBuilder.append("\n");
        writer.append(stringBuilder.toString());
    }
    private static String csvFormat(String value){
        String result = value;
        if(result.contains("\"")){
            result = result.replace("\"","\"\"");
        }
        return result;
    }
}
