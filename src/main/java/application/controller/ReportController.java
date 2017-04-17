package application.controller;

import application.report.Report;
import application.report.ReportFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mihnea on 15/04/2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class ReportController {

    @RequestMapping(value = "/pdfReport", method = RequestMethod.GET)
    public String pdfReport(){
        ReportFactory reportFactory = new ReportFactory();

        Report pdf = reportFactory.getReport("pdf");
        pdf.generate();

        return "redirect:/admin";
    }

    @RequestMapping(value = "/csvReport", method = RequestMethod.GET)
    public String csvReport(){
        ReportFactory reportFactory = new ReportFactory();

        Report csv = reportFactory.getReport("csv");
        csv.generate();

        return "redirect:/admin";
    }
}
