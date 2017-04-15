package application.controller;

import application.repository.Books;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by MIHONE on 4/9/2017.
 */

@Controller
public class SearchController {

    @Autowired
    private Books books;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String show(){
        return "/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String search(HttpServletRequest request){
        String search = request.getParameter("search");
        return "redirect:/view?search="+search.replaceAll(" ","_");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdmin(){ return "/admin"; }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String searchAdmin(HttpServletRequest request){
        String search = request.getParameter("search");
        String option = request.getParameter("option");
        if(option.equalsIgnoreCase("book"))
            return "redirect:/admin/view?search="+search.replaceAll(" ","_");
        else
            return "redirect:/admin/user?search="+search.replaceAll(" ", "_");
    }
}
