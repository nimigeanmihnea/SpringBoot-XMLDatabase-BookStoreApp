package application.report;

import application.controller.UnmarshalController;
import application.entity.Book;
import application.repository.Books;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by Mihnea on 15/04/2017.
 */
public class PDFReport implements Report {

    @Autowired
    private static Books books;

    private static String file = "src/main/resources/report/PDFReport.pdf";

    @Override
    public void generate() {
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            addContnent(document);
            document.close();

        }catch (Exception e){ e.printStackTrace(); }
    }

    private static void addMetaData(Document document) {
        document.addTitle("PDF Stock Report");
        document.addAuthor("ADMIN");
        document.addCreator("ADMIN");
    }

    private static void addContnent(Document document) throws JAXBException, DocumentException {
        UnmarshalController controller = new UnmarshalController();
        books = controller.getBooks();
        List<Book> bookList = books.getBooks();
        for(Book book:bookList){
            if (book.getQuantity() == 0){
                Paragraph paragraph = new Paragraph(book.toString()+"\n");
                document.add(paragraph);
            }
        }
    }
}
