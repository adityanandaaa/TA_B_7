package apap.ta.ruangan.Controller;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Rest.Base;
import apap.ta.ruangan.Rest.PengadaanBukuDetail;
import apap.ta.ruangan.Service.*;
import org.dom4j.rule.Mode;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/fasilitas")
public class FasilitasController {
    @Autowired
    FasilitasService fasilitasService;

    @Autowired
    RuanganService ruanganService;

    @Autowired
    RuanganFasilitasService ruanganFasilitasService;

    @Autowired
    PengadaanBukuRestService pengadaanBukuRestService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFasilitas(Model model, Authentication authentication){
        String message = "";
        RuanganFasilitasModel fasilitasRuanganObj = new RuanganFasilitasModel();

        String namaFasilitas = "";

        List<RuanganModel> listRuangan = ruanganService.getRuanganList();

        model.addAttribute("message", message);
        model.addAttribute("fasilitasRuangan", fasilitasRuanganObj);
        model.addAttribute("listRuangan", listRuangan);
        model.addAttribute("namaFasilitas", namaFasilitas);
        model.addAttribute("pageTitle","Tambah fasilitas");
        return "form-add-fasilitas";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFasilitasSubmit(@ModelAttribute("fasilitasRuangan") RuanganFasilitasModel fasilitasRuangan, @ModelAttribute("namaFasilitas") String namaFasilitas, Model model){
        String namaFasilitasLower = namaFasilitas.toLowerCase();
        ruanganFasilitasService.addFasilitas(fasilitasRuangan, namaFasilitasLower);
        RuanganModel ruangan = ruanganService.getRuanganById(fasilitasRuangan.getRuanganModel().getId());
        String message = fasilitasRuangan.getJumlah_fasilitas() + " " + namaFasilitas + " berhasil ditambahkan ke ruangan " + ruangan.getNama()  + "!";
//        FasilitasModel fasilitas = fasilitasService.getFasilitasById(fasilitasRuangan.getFasilitasModel().getId());
//        Long jmlFasilitasTersedia = fasilitasService.getAvailableStok(fasilitas);
//        if(jmlFasilitasTersedia == 0){
//            String message = "Tidak ada " + fasilitas.getNama() + " yang tersedia untuk ditambahkan ke ruangan";
//            model.addAttribute("message", message);
//            return "form-add-fasilitas";
//        }
//        else{
//            if(fasilitasRuangan.getJumlah_fasilitas() > jmlFasilitasTersedia){
//                String message = "Fasilitas tidak boleh melebihi " + jmlFasilitasTersedia + " buah";
//                model.addAttribute("message", message );
//                return "form-add-fasilitas";
//            }
//            else{
//                ruanganFasilitasService.addFasilitasRuangan(fasilitasRuangan);
//                RuanganModel ruangan = ruanganService.getRuanganById(fasilitasRuangan.getRuanganModel().getId());
//                if(ruangan.getListFasilitas()== null) {
//                    ruangan.setListFasilitas(new ArrayList<RuanganFasilitasModel>());
//                }
//                ruangan.getListFasilitas().add(fasilitasRuangan);
//
//                FasilitasModel fasilitasfix = fasilitasService.getFasilitasById(fasilitasRuangan.getRuanganModel().getId());
//                if(fasilitasfix.getListRuangan()== null) {
//                    fasilitasfix.setListRuangan(new ArrayList<RuanganFasilitasModel>());
//                }
//                fasilitasfix.getListRuangan().add(fasilitasRuangan);
//
//                String message = fasilitasRuangan.getJumlah_fasilitas() +" " + fasilitasfix.getNama() + " berhasil ditambahkan ke ruangan " + ruangan.getNama();
//                model.addAttribute("message", message);
//                return "form-add-fasilitas";
//            }
//        }
        RuanganFasilitasModel fasilitasRuanganObj = new RuanganFasilitasModel();

        String namaFasilitas2 = "";

        List<RuanganModel> listRuangan = ruanganService.getRuanganList();

        model.addAttribute("message", message);
        model.addAttribute("fasilitasRuangan", fasilitasRuanganObj);
        model.addAttribute("listRuangan", listRuangan);
        model.addAttribute("namaFasilitas", namaFasilitas2);
        model.addAttribute("pageTitle","Tambah Fasilitas");
        return "add-fasilitas";
    }

    @RequestMapping(value="/pengadaanBuku", method=RequestMethod.GET)
    private String addPengadaanPage(Model model) {
        PengadaanBukuDetail pengadaanBuku = new PengadaanBukuDetail();
        model.addAttribute("pengadaanBuku", pengadaanBuku);
        model.addAttribute("pageTitle","Pengadaan Buku");
        return "form-add-pengadaan";
    }

    @RequestMapping(value="/pengadaanBuku", method=RequestMethod.POST)
    private String addPengadaanPageSubmit(@ModelAttribute("pengadaanBuku") PengadaanBukuDetail pengadaanBukuDetail, Model model, Authentication authentication, RedirectAttributes redirect){
        String nama = authentication.getName();
        String id = userService.getUserByUSername(nama).getId();
        pengadaanBukuDetail.setUserId(id);
        try{
            if(pengadaanBukuRestService.postStatus(pengadaanBukuDetail).block().getStatus() == 200){
                redirect.addFlashAttribute("message", "Pengadaan Buku berhasil dibuat!");
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("message", "Pengadaan Buku berhasil dibuat!");
        }

        model.addAttribute("pageTitle","Pengadaan Buku");
        return "add-pengadaan-buku";
    }




}

