package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Service.FasilitasService;
import apap.ta.ruangan.Service.RuanganFasilitasService;
import apap.ta.ruangan.Service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ruangan")
public class RuanganController {

    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private RuanganFasilitasService ruanganFasilitasService;

    @Autowired
    private FasilitasService fasilitasService;
    @RequestMapping(value = "/view-ruangan", method = RequestMethod.GET)
    public String view(@RequestParam(value = "idRuangan", required = true) Long idRuangan, Model model) {
        RuanganModel ruangan = ruanganService.getRuanganById(idRuangan);

        List<RuanganFasilitasModel> listRuanganbeneran = new ArrayList<>();
        List<RuanganFasilitasModel> ruanganFasilitas = ruanganFasilitasService.getRuanganFasilitasByRuangan(ruangan);

//        int counter = 1;
//        for(int i = 0; i <= ruanganFasilitas.size()-1; i++){
//            listRuanganbeneran.add(ruanganFasilitas.get(i));
//            for(int j = 0; i <= listRuanganbeneran.size()-1; j++) {
//                if (ruanganFasilitas.get(i).getFasilitasModel().getId() == ruanganFasilitas.get(j).getFasilitasModel().getId()) {
//                    listRuanganbeneran.remove(ruanganFasilitas.get(j));
//                    counter++;
//                }
//            }
//        }
//        System.out.println("total" + counter);
//        model.addAttribute("counter",counter);
//        model.addAttribute("ruanganFasilitas",listRuanganbeneran);

        model.addAttribute("ruanganFasilitas",ruanganFasilitas);
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
