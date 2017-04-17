package application.report;

/**
 * Created by Mihnea on 15/04/2017.
 */

public class ReportFactory {

    public Report getReport(String reportType){
        if(reportType == null){
            return null;
        }

        if(reportType.equalsIgnoreCase("pdf")){
            return new PDFReport();
        }

        if(reportType.equalsIgnoreCase("csv")){
            return new CSVReport();
        }

        return null;
    }
}
