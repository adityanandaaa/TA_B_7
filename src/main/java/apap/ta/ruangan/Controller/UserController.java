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
import apap.ta.ruangan.Service.UserService;
import reactor.core.publisher.Mono;
import apap.ta.ruangan.Rest.GuruResponse;
import apap.ta.ruangan.Rest.SiswaResponse;

import org.springframework.security.core.context.SecurityContextHolder;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController{
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/add-user-form")
    private String createUserForm(Model model){
        UserModel user = new UserModel();
        List<RoleModel> roles = new ArrayList<RoleModel>();
        roles.add(roleService.findById(Long.valueOf(3)));
        roles.add(roleService.findById(Long.valueOf(4)));
        int status = 0;
        model.addAttribute("status", status);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user.getId());
        return "form-add-user";
    }

    @RequestMapping(value="/add-user-detail")
    private String createUser(@ModelAttribute  UserModel user,  Model model){
        Long idrole = user.getRole().getId();
        List<UserModel> users = userService.getAllUsers();
        for(UserModel a : users){
            if(user.getUsername().equals(a.getUsername())){
                int status = 1;
                String gagal = "USERNAME SUDAH ADA DI DATABASE";
                model.addAttribute("status", status);
                model.addAttribute("gagal", gagal);
                return "form-add-user";
            }
        }
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
    private String postStatusSiswa(@ModelAttribute SiswaResponse siswa) throws JSONException{
        userRestService.postStatusSiswa(siswa).block();
        return "add-user";
    }

    @PostMapping(value = "/submit-user-guru")
    private String postStatusGuru(@ModelAttribute GuruResponse guru,UserModel user) throws JSONException{
        userRestService.postStatusGuru(guru).block();
        return "add-user";
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
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

