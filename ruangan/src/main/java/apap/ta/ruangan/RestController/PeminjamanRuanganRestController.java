package apap.ta.ruangan.RestController;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Rest.BaseResponse;
import apap.ta.ruangan.Service.FasilitasRestService;
import apap.ta.ruangan.Service.PeminjamanRuanganRestService;
import apap.ta.ruangan.Service.RuanganFasilitasRestService;
import apap.ta.ruangan.Service.RuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class PeminjamanRuanganRestController {

    @Autowired
    private FasilitasRestService fasilitasRestService;

    @Autowired
    private RuanganRestService ruanganRestService;

    @Autowired
    private RuanganFasilitasRestService ruanganFasilitasRestService;

    @Autowired
    private PeminjamanRuanganRestService peminjamanRuanganRestService;
    

    @PostMapping(value = "/pinjem")
    private BaseResponse<PeminjamanRuanganModel> createPeminjamanRuangan(@Valid @RequestBody PeminjamanRuanganModel peminjamanruangan, BindingResult bindingResult) throws ParseException {
        BaseResponse<PeminjamanRuanganModel> response = new BaseResponse<PeminjamanRuanganModel>();
        RuanganModel calonruangan = null;
        List<RuanganModel> ruanganModels = ruanganRestService.getRuanganList();
        for(RuanganModel ruanganModel : ruanganModels){
            if(peminjamanruangan.getRuanganModel().getId() == ruanganModel.getId()){
                calonruangan = ruanganModel;
            }
        }
        if (bindingResult.hasFieldErrors()) {
                    response.setStatus(500);
                    response.setMessage("error data");
        } else {
            DateFormat sdf = new SimpleDateFormat("hh:mm");
            Date mulai = sdf.parse(peminjamanruangan.getWaktu_mulai());
            Date akhir = sdf.parse(peminjamanruangan.getWaktu_selesai());

            if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                if (mulai.compareTo(akhir) < 0) {
                    if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                        peminjamanruangan.setIs_disetujui(false);
                        peminjamanruangan.setUserModelPenyetuju(null);
                        peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
                        response.setStatus(200);
                        response.setMessage("success");
                        response.setResult(peminjamanruangan);
                    }
                }
            }
        }
        return response;
    }
    @GetMapping(value = "/listruangan")
    private List<RuanganModel> retrieveRuangan(){
            return ruanganRestService.getRuanganList();
    }

//    @PostMapping(value = "/pinjem")
//    private BaseResponse<PeminjamanRuanganModel> createPeminjamanRuangan2(@Valid @RequestBody PeminjamanRuanganModel peminjamanruangan, BindingResult bindingResult) throws ParseException {
//        BaseResponse<PeminjamanRuanganModel> response = new BaseResponse<PeminjamanRuanganModel>();
//        RuanganModel calonruangan;
//        List<RuanganModel> ruanganModels = ruanganRestService.getRuanganList();
//        for(RuanganModel ruanganModel : ruanganModels){
//            if(peminjamanruangan.getRuanganModel().getId() == ruanganModel.getId()){
//                calonruangan = ruanganModel;
//            }
//        }
//        System.out.println(peminjamanruangan.getRuanganModel().getNama());
//        System.out.println(peminjamanruangan.getRuanganModel().getKapasitas());
//        System.out.println(peminjamanruangan.getRuanganModel().getId());
//
//        if (bindingResult.hasFieldErrors()) {
//            response.setStatus(500);
//            response.setMessage("error data");
//        } else {
//            peminjamanruangan.setIs_disetujui(false);
//            peminjamanruangan.setUserModelPenyetuju(null);
//            peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(peminjamanruangan);
//
//
//        }
//        return response;
//    }
}

