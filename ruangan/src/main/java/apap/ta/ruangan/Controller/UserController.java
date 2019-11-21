package apap.ta.ruangan.Controller;

import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Service.UserRestService;
import apap.ta.ruangan.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    private String viewProfile(Model model){
        model.addAttribute("pageTitle", "Profil");
        UserModel loggedUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        String role = "default";
        long idRole = loggedUser.getRole().getId();
        if(idRole==1 | idRole==3){
            role = "teachers";
        }
        else if(idRole==2 | idRole==5){
            role = "employees";
        }
        else if(idRole==6 | idRole==7){
            role="decide";
        }
        else {
            role = "students";
        }
        Map<String, String> profile = null;
        model.addAttribute("loggedUser", loggedUser);
        if( role.equals("teachers") || role.equals("students") || role.equals("employees")){
            Map<String, Object> allUsers = userRestService.getUsers(role);
            ArrayList list = (ArrayList) allUsers.get("result");
            ArrayList<String> uuidList = new ArrayList<String>();
            for(int i = 0; i < list.size(); i++){
                LinkedHashMap<String, Object> ab = (LinkedHashMap<String, Object>) list.get(i);
                uuidList.add((String)ab.get("idUser"));
            }
            if(uuidList.contains(loggedUser.getId())){
                profile = userRestService.getUserProfile(role, loggedUser.getId());
            }
        }
        else if( role.equals("decide")){
            Map<String, Object> allUsers = userRestService.getUsers("teachers");
            ArrayList list = (ArrayList) allUsers.get("result");
            ArrayList<String> uuidList = new ArrayList<String>();
            for(int i = 0; i < list.size(); i++){
                LinkedHashMap<String, Object> ab = (LinkedHashMap<String, Object>) list.get(i);
                uuidList.add((String)ab.get("idUser"));
            }
            if(uuidList.contains(loggedUser.getId())){
                profile = userRestService.getUserProfile("teachers", loggedUser.getId());
                role = "teachers";
            }
            else {
                allUsers = userRestService.getUsers("employees");
                list = (ArrayList) allUsers.get("result");
                uuidList = new ArrayList<String>();
                for(int i = 0; i < list.size(); i++){
                    LinkedHashMap<String, Object> ab = (LinkedHashMap<String, Object>) list.get(i);
                    uuidList.add((String)ab.get("idUser"));
                }
                if(uuidList.contains(loggedUser.getId())){
                    profile = userRestService.getUserProfile("employees", loggedUser.getId());
                    role = "employees";
                }
            }
        }
        model.addAttribute("profile", profile);
        model.addAttribute("role", role);
        model.addAttribute("teachers", "teachers");
        model.addAttribute("employees", "employees");

        return "profile";
    }
}
