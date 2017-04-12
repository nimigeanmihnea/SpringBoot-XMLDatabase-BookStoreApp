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
@RequestMapping(value = "/home")
public class SearchController {

    @Autowired
    private Books books;

    @RequestMapping(method = RequestMethod.GET)
    public String show(){
        return "/home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(HttpServletRequest request){
        String search = request.getParameter("search");
        return "redirect:/view?search="+search.replaceAll(" ","_");

    }
}
