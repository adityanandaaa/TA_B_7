package apap.ta.ruangan.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import apap.ta.ruangan.Model.RoleModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Rest.BaseResponse;
import apap.ta.ruangan.Service.RoleService;
import apap.ta.ruangan.Service.UserRestService;
import reactor.core.publisher.Mono;
import apap.ta.ruangan.Rest.GuruResponse;
import apap.ta.ruangan.Rest.SiswaResponse;

@Controller
public class UserController{
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private RoleService roleService;


    @RequestMapping(value="/add-user-form")
    private String createUserForm(Model model){
        UserModel user = new UserModel();
        List<RoleModel> roles = new ArrayList<RoleModel>();
        roles.add(roleService.findById(Long.valueOf(3)));
        roles.add(roleService.findById(Long.valueOf(4)));
        model.addAttribute("roles", roles);
        model.addAttribute("user", user.getId());
        return "form-add-user";
    }


    @RequestMapping(value="/add-user-detail/")
    private String createUser(@ModelAttribute  UserModel user,  Model model){
        Long idrole = user.getRole().getId();
        userRestService.createUser(user);
        if(idrole == 3){
            GuruResponse guru = new GuruResponse();
            guru.setIdUser(user.getId());
            model.addAttribute("guru",guru);
        }
        else{
            SiswaResponse siswa = new SiswaResponse();
            siswa.setIdUser(user.getId());
            model.addAttribute("siswa",siswa);
        }
        String parser = String.valueOf(idrole);
        int newrole = Integer.parseInt(parser);
        model.addAttribute("role", newrole);
        model.addAttribute("user", user);
        return "form-add-user-details";   
    }

    @PostMapping(value = "/submit-user-siswa")
    private SiswaResponse postStatusSiswa(@ModelAttribute SiswaResponse siswa) throws JSONException{
        return userRestService.postStatusSiswa(siswa).block();
    }

    @PostMapping(value = "/submit-user-guru")
    private GuruResponse postStatusGuru(@ModelAttribute GuruResponse guru,UserModel user) throws JSONException{
        return userRestService.postStatusGuru(guru).block();
    }

}