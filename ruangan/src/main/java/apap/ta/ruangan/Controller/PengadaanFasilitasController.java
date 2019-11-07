package apap.ta.ruangan.Controller;

import apap.ta.ruangan.Model.PengadaanFasilitasModel;
import apap.ta.ruangan.Service.PengadaanFasilitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping(value = "/pengadaan-fasilitas-all", method = RequestMethod.GET)
    public String viewAllPengadaanFasilitas(Model model){
        List<PengadaanFasilitasModel> listPengadaanFasilitas = pengadaanFasilitasService.getAllPengadaanFasilitas();
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
}