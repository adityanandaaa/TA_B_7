package apap.ta.ruangan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("pageTitle","Home");
        return "home";
    }
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("pageTitle","Log in");
        return "login";
    }
}
