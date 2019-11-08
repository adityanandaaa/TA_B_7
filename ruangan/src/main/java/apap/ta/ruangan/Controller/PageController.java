package apap.ta.ruangan.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(Model model, Authentication authentication){
        String role = authentication.getAuthorities().toString();
        model.addAttribute("pageTitle","Home");
        if(role.equals("[Admin TU]")){
            return "home-admin";
        }
        else if(role.equals("[Siswa]"))
            return "home-siswa";
        else if(role.equals("[Guru]")){
            return "home-guru";
        }
        return "home-siswa";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("pageTitle","Log in");
        return "login";
    }
}
