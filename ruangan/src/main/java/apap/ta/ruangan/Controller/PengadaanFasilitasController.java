package apap.ta.ruangan.Controller;

import apap.ta.ruangan.Model.PengadaanFasilitasModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Service.PengadaanFasilitasService;
import apap.ta.ruangan.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pengadaan-fasilitas")
public class PengadaanFasilitasController {

    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewAllPengadaanFasilitas(Model model){
        UserModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PengadaanFasilitasModel> listPengadaanFasilitas;
        if(user.getRole().getId() == Long.valueOf(2)){
            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
        }else{
            listPengadaanFasilitas = user.getPengadaanFasilitasList();        
        }
        model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
        return "view-all-pengadaan-fasilitas";
    }

    
    @RequestMapping(value = "/delete/{idPengadaanFasilitas}")
    public String delete(
        @PathVariable(value = "idPengadaanFasilitas") Long id, Model model){
        PengadaanFasilitasModel pengadaanFasilitas = pengadaanFasilitasService.getPengadaanFasilitasById(id);
        if(pengadaanFasilitas == null){
            return "404";
        }
        else{
        String nama = pengadaanFasilitas.getNama();
        pengadaanFasilitasService.deletePengadaanFasilitas(id);
        model.addAttribute("nama", nama);
        return "delete-pengadaan-fasilitas";
        }
    }

    @RequestMapping(value = "/add-form")
    public String addForm(Model model){
        PengadaanFasilitasModel pengadaanFasilitas = new PengadaanFasilitasModel();
        model.addAttribute("pengadaanFasilitas", pengadaanFasilitas);
        return "add-pengadaan-fasilitas-form";
    }

    @RequestMapping(value = "/add")
    public String submitForm(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, Model model){
        UserModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getRole().getId() == Long.valueOf(3)){
            List<PengadaanFasilitasModel> pengadaanFasilitasList= user.getPengadaanFasilitasList();
            pengadaanFasilitas.setStatus(0);
            pengadaanFasilitas.setUserModel(user);
            pengadaanFasilitasList.add(pengadaanFasilitas);
            user.setPengadaanFasilitasList(pengadaanFasilitasList);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);    
        }else{
            pengadaanFasilitas.setStatus(1);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
        }
        model.addAttribute("pengadaanFasilitas", pengadaanFasilitas);
        return "add-pengadaan-fasilitas";
    }

}