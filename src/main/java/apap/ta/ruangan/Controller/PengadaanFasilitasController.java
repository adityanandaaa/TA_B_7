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
        int update = 0;
        UserModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PengadaanFasilitasModel> listPengadaanFasilitas;
        if(user.getRole().getId() == Long.valueOf(2)){
            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
        }else{
            listPengadaanFasilitas = user.getPengadaanFasilitasList();        
        }
        model.addAttribute("update", update);
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
        String deleted = "Pengadaan fasilitas " + nama + " berhasil dihapus";
        pengadaanFasilitasService.deletePengadaanFasilitas(id);
        List<PengadaanFasilitasModel> listPengadaanFasilitas;
        UserModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getRole().getId() == Long.valueOf(2)){
            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
        }else{
            listPengadaanFasilitas = user.getPengadaanFasilitasList();        
        }
        int update = 1;
        model.addAttribute("update", update);
        model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
        model.addAttribute("berhasil", deleted);
        return "view-all-pengadaan-fasilitas";
        }
    }

    @RequestMapping(value = "/add-form")
    public String addForm(Model model){
        PengadaanFasilitasModel pengadaanFasilitas = new PengadaanFasilitasModel();
        int update = 0;
        model.addAttribute("update", update);
        model.addAttribute("pengadaanFasilitas", pengadaanFasilitas);
        return "add-pengadaan-fasilitas-form";
    }

    @RequestMapping(value = "/add")
    public String submitForm(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, Model model){
        UserModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PengadaanFasilitasModel> listPengadaanFasilitas;
        if(user.getRole().getId() == Long.valueOf(3)){
            listPengadaanFasilitas = user.getPengadaanFasilitasList();
            for(PengadaanFasilitasModel u : listPengadaanFasilitas){
                if(u.getNama().equals(pengadaanFasilitas.getNama())){
                    int update = 2;
                    String berhasil = "Pengadaan fasilitas sudah diajukan!";
                    model.addAttribute("update", update);
                    model.addAttribute("berhasil", berhasil);
                    model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
                    return "view-all-pengadaan-fasilitas";
                }
            }
            pengadaanFasilitas.setStatus(0);
            pengadaanFasilitas.setUserModel(user);
            listPengadaanFasilitas.add(pengadaanFasilitas);
            user.setPengadaanFasilitasList(listPengadaanFasilitas);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);    
        }else{
            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
            for(PengadaanFasilitasModel u : listPengadaanFasilitas){
                if(u.getNama().equals(pengadaanFasilitas.getNama())){
                    if(u.getHarga() != pengadaanFasilitas.getHarga()){
                        u.setHarga(pengadaanFasilitas.getHarga());
                        if(user.getRole().getId() == Long.valueOf(2)){
                            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
                        }else{
                            listPengadaanFasilitas = user.getPengadaanFasilitasList();        
                        }
                        int update = 1;
                        String berhasil = "Harga pengadaan " + pengadaanFasilitas.getNama() + " berhasil diubah";
                        model.addAttribute("update", update);
                        model.addAttribute("berhasil", berhasil);
                        model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
                        return "view-all-pengadaan-fasilitas";
                         }
                    u.setJumlah(u.getJumlah() + pengadaanFasilitas.getJumlah());
                    if(user.getRole().getId() == Long.valueOf(2)){
                        listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
                    }else{
                        listPengadaanFasilitas = user.getPengadaanFasilitasList();        
                    }
                    int update = 1;
                    String berhasil = "Jumlah pengadaan fasilitas " + pengadaanFasilitas.getNama() + " berhasil diubah";
                    model.addAttribute("update", update);
                    model.addAttribute("berhasil", berhasil);
                    model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
                    return "view-all-pengadaan-fasilitas";
                }
                
            }
            pengadaanFasilitas.setUserModel(user);
            pengadaanFasilitas.setStatus(1);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
        }
        if(user.getRole().getId() == Long.valueOf(2)){
            listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
        }else{
            listPengadaanFasilitas = user.getPengadaanFasilitasList();        
        }
        int update = 1;
        String berhasil = "Pengadaan fasilitas berhasil ditambahkan";
        model.addAttribute("update", update);
        model.addAttribute("berhasil", berhasil);
        model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
        return "view-all-pengadaan-fasilitas";
    }

}