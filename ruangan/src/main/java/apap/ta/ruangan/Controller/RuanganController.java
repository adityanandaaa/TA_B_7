package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ruangan")
public class RuanganController {

    @Autowired
    private RuanganService ruanganService;

    @RequestMapping(value = "/view-ruangan", method = RequestMethod.GET)
    public String view(@RequestParam(value = "idRuangan", required = true) Long idRuangan, Model model) {

        RuanganModel ruangan = ruanganService.getRuanganById(idRuangan);


        model.addAttribute("ruangan", ruangan);
        model.addAttribute("pageTitle", "View Ruangan");
        model.addAttribute("pageFooter", "View Store");

        // Return view template
        return "view-ruangan";

    }

    @RequestMapping(value = "/ruangan-all", method = RequestMethod.GET)
    public String viewAllruangan(Model model) {

        List<RuanganModel> ruanganList = ruanganService.getRuanganList();

        model.addAttribute("ruanganList", ruanganList);
        model.addAttribute("pageTitle", "All Ruangan");
        model.addAttribute("pageFooter", "View Store");

        return "view-all-ruangan";
    }
}
