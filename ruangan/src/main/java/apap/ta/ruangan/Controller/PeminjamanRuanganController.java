package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Service.PeminjamanRuanganService;
import apap.ta.ruangan.Service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/peminjaman-ruangan")
public class PeminjamanRuanganController {

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @Autowired
    private RuanganService ruanganService;


    @RequestMapping(value = "/tambah", method = RequestMethod.GET)
    public String addPeminjamanRuanganFormPage(Model model) {
        PeminjamanRuanganModel peminjamanRuanganModel = new PeminjamanRuanganModel();



        model.addAttribute("peminjamanruangan", peminjamanRuanganModel);
        model.addAttribute("listOfRuangan",ruanganService.getRuanganList());
        model.addAttribute("pageTitle", "Add Peminjaman Ruangan");
        return "form-add-peminjaman-ruangan";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.POST)
    public String addPeminjamanRuanganPage(@ModelAttribute PeminjamanRuanganModel peminjamanruangan, Model model) throws ParseException {
        String messages ;
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        Date mulai = sdf.parse(peminjamanruangan.getWaktu_mulai());
        Date akhir = sdf.parse(peminjamanruangan.getWaktu_selesai());

        if(peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai()) ) {
            if(mulai.compareTo(akhir) < 0){
                if(peminjamanruangan.getJumlah_peserta()<peminjamanruangan.getRuanganModel().getKapasitas()){
                    peminjamanruangan.setIs_disetujui(false);
                    peminjamanruangan.setUserModelPenyetuju(null);

                    peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                    model.addAttribute("peminjamanruangan", peminjamanruangan);
                    return "add-peminjaman-ruangan";
                }
                else {

                    messages = "Kapasitas tidak sesuai";
                    model.addAttribute("messages",messages);
                    return "gabisa-add-peminjaman-ruangan";
                }
            }
            else {
                messages = "Waktu tidak sesuai";
                model.addAttribute("messages",messages);
                return "gabisa-add-peminjaman-ruangan";
            }
        }else {
            messages = "Tanggal tidak sesuai";
            model.addAttribute("messages",messages);
            return "gabisa-add-peminjaman-ruangan";
        }
    }


    @RequestMapping(value = "/view-peminjaman-ruangan", method = RequestMethod.GET)
    public String view(@RequestParam(value = "idPeminjamanRuangan", required = true) Long idPeminjamanRuangan, Model model) {

        PeminjamanRuanganModel peminjamanruangan = peminjamanRuanganService.getPeminjamanRuanganById(idPeminjamanRuangan);

        model.addAttribute("peminjamanruangan", peminjamanruangan);
        model.addAttribute("pageTitle", "View Peminjaman Ruangan");
        model.addAttribute("pageFooter", "View Store");

        // Return view template
        return "view-peminjaman-ruangan";

    }

    @RequestMapping(value = "/peminjaman-ruangan-all", method = RequestMethod.GET)
    public String viewAllruangan(Model model) {

        List<PeminjamanRuanganModel> peminjamanRuanganList = peminjamanRuanganService.getPeminjamanRuanganList();

        model.addAttribute("peminjamanRuanganList", peminjamanRuanganList);
        model.addAttribute("pageTitle", "All Peminjaman Ruangan");
        model.addAttribute("pageFooter", "View Store");

        return "view-all-peminjaman-ruangan";
    }

}
