package apap.ta.ruangan.RestController;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Rest.BaseResponse;
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
    private BaseResponse<List<FasilitasModel>> retrieveFasilitas(@RequestParam("namaRuangan")String namaRuangan){
        BaseResponse<List<FasilitasModel>> response = new BaseResponse<>();
        try {
            RuanganModel ruangan = ruanganRestService.getRuanganByNama(namaRuangan);
            List<FasilitasModel> listFasilitas = new ArrayList<>();
            List<RuanganFasilitasModel> ruanganFasilitas = ruanganFasilitasRestService.getRuanganFasilitasByRuangan(ruangan);
            for(RuanganFasilitasModel ruanganFasilitasModel : ruanganFasilitas){
                listFasilitas.add(ruanganFasilitasModel.getFasilitasModel());
            }
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(listFasilitas);
            return response;
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"ID Store " + namaRuangan + "Not Found");
        }
    }


//    /api/v1/fasilitas?namaRuangan=Koperasi

    @GetMapping(value = "/allfasilitas")
    private BaseResponse<List<FasilitasModel>> retrieveListFasilitias(){
        BaseResponse<List<FasilitasModel>> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(fasilitasRestService.retrieveListFasilitasModel());
        return response;
    }
}
