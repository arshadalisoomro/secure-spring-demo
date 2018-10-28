package pk.edu.suk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Arshay on 10/27/2018.
 */

@Controller
public class HomeController {

    @GetMapping(value = "/secure/home")
    public String getSecureHome(){
        return "home";
    }

}
