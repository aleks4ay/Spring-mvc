package ua.aleks4ay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.aleks4ay.dao.UserDAO;
import ua.aleks4ay.model.User;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private UserDAO userDAO;

//    private static List<User> users = new ArrayList<User>();

    @GetMapping("/")
    /*public String view() {
        return "index";
    }*/
    public String view(Model model) {
        model.addAttribute("msg", "Null msg");
        return "index";
    }

    @GetMapping("/view1")
    public String view_1(Model model) {
        model.addAttribute("msg", "Hello world from message!");
        return "index";
    }

    @GetMapping("/view2")
    public String view2(@RequestParam(value = "name", required = false, defaultValue = "strangers") String name, Model model) {
        model.addAttribute("msg", "Hello " + name);
        return "index";
    }

    @GetMapping("/view3/{name}")
    public String view3(@PathVariable("name") String name, Model model) {
        model.addAttribute("msg", "Hello " + name);
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "Raw data";
    }


    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {
//      1.  users.add(new User("John", "Smith", "J_Smith@google.com"));
//      1.  users.add(new User("Nik", "Jagger", "NJ@google.com"));
//      2.  model.addAttribute("users", users);
        model.addAttribute("users", userDAO.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

// ------------- 1. use standart way, not Spring-way ------------------
/*
    @PostMapping("/users/new")
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("email") String email) {
        users.add(new User(name, surname, email));
        return "redirect:/users";
    }
*/

// ------------- 2. use Spring-way -------------------------------------
    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/sign_up";
        }
//        users.add(user);
        return "redirect:/users";
    }
}
