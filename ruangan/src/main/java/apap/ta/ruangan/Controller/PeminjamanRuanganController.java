package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Service.PeminjamanRuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/peminjaman-ruangan")
public class PeminjamanRuanganController {

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

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
