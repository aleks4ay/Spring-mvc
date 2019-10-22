package ua.aleks4ay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String view() {
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
}
