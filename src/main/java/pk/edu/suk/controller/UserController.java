package pk.edu.suk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pk.edu.suk.model.Role;
import pk.edu.suk.model.User;
import pk.edu.suk.service.RoleService;
import pk.edu.suk.service.UserService;
import pk.edu.suk.service.util.SecurityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arshay on 10/27/2018.
 */

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/register")
    public String getRegister(Model model, @RequestParam(value = "error", required = false) String error){

        model.addAttribute("userObject", new User());
        if (null != error && error.equalsIgnoreCase("true")){
            model.addAttribute("registerError", "Unable to register");
        }
        return "register";
    }

    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute(name = "userObject") User user){

        //Set user role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRoleByName("USER"));
        user.setRoles(roles);
        userService.save(user);
        return (userService.save(user)? "redirect:/login" : "redirect:/register?error=true");
    }

    @GetMapping(value = "/login")
    public String getLogin(Model model,
                           @RequestParam(value = "error", required = false) String error){
        if (null != error && error.equalsIgnoreCase("true")){
            model.addAttribute("loginError", "Unable to Login");
        }
        return "login";
    }

    @PostMapping(value = "/login")
    public String postLogin(@RequestParam(value = "userEmail") String userEmail,
                              @RequestParam(value = "userPassword") String userPassword){
        logger.debug(userEmail + " and " + userPassword );
        boolean loginResult = securityService.login(userEmail, userPassword);
        return (loginResult ? "redirect:/secure/home" : "redirect:/login?error=true");
    }

}
