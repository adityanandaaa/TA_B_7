package apap.ta.ruangan.RestController;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Service.FasilitasRestService;
import apap.ta.ruangan.Service.RuanganFasilitasRestService;
import apap.ta.ruangan.Service.RuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class FasilitasRestController {


    @Autowired
    private FasilitasRestService fasilitasRestService;

    @Autowired
    private RuanganRestService ruanganRestService;

    @Autowired
    private RuanganFasilitasRestService ruanganFasilitasRestService;

    @GetMapping(value = "/fasilitas")
    private List<FasilitasModel> retrieveFasilitas(@RequestParam("namaRuangan")String namaRuangan){
        try {
            RuanganModel ruangan = ruanganRestService.getRuanganByNama(namaRuangan);
            List<FasilitasModel> listFasilitas = new ArrayList<>();
            List<RuanganFasilitasModel> ruanganFasilitas = ruanganFasilitasRestService.getRuanganFasilitasByRuangan(ruangan);
            for(RuanganFasilitasModel ruanganFasilitasModel : ruanganFasilitas){
                listFasilitas.add(ruanganFasilitasModel.getFasilitasModel());
            }
            return listFasilitas;
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"ID Store " + namaRuangan + "Not Found");
        }
    }



//    @GetMapping(value = "/fasilitas")
//    private List<FasilitasModel> retrieveStore(@RequestParam("namaRuangan")String namaRuangan){
//        try {
//            RuanganModel ruangan = ruanganRestService.getRuanganByNama(namaRuangan);
//            List<FasilitasModel> listFasilitas = new ArrayList<>();
//            List<RuanganFasilitasModel> ruanganFasilitas = ruanganFasilitasRestService.getRuanganFasilitasByRuangan(ruangan);
//            for(RuanganFasilitasModel ruanganFasilitasModel : ruanganFasilitas){
//                FasilitasModel fasilitasModel = ruanganFasilitasModel.getFasilitasModel();
//                listFasilitas.add(fasilitasModel);
//            }
//
//            for(FasilitasModel fasilitasModel : listFasilitas){
//                fasilitasRestService.retrieveBranch(fasilitasModel);
//            }
//            System.out.println(listFasilitas);
//            return listFasilitas;
//        }catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"ID Store " + namaRuangan + "Not Found");
//        }
//    }
//    /api/v1/fasilitas?namaRuangan=Koperasi

    @GetMapping(value = "/allfasilitas")
    private List<FasilitasModel> retrieveListFasilitias(){
        return fasilitasRestService.retrieveListFasilitasModel();
    }
}
