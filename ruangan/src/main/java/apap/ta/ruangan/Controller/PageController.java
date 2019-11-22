package apap.ta.ruangan.Controller;

import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserDb userDb;

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
