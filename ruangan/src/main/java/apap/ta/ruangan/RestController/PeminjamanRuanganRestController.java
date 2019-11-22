package apap.ta.ruangan.RestController;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Rest.BaseResponse;
import apap.ta.ruangan.Rest.PengajuanSurat;
import apap.ta.ruangan.Rest.PengajuanSuratResponse;
import apap.ta.ruangan.Rest.Setting;
import apap.ta.ruangan.Service.FasilitasRestService;
import apap.ta.ruangan.Service.PeminjamanRuanganRestService;
import apap.ta.ruangan.Service.PeminjamanRuanganService;
import apap.ta.ruangan.Service.RuanganFasilitasRestService;
import apap.ta.ruangan.Service.RuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

//    @Autowired
//    RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate rest() {
//        return new RestTemplate();
//    }

//    @GetMapping(value= "/listsurat")
//    public String getStatus() {
////        String path = Setting.pilotUrl+"/pilot?licenseNumber="+ licenseNumber;
//        List<PengajuanSurat> pengajuanSuratList = new ArrayList<>();
//        String path = "https://d3358147-6e01-490c-a290-3d8c320c4f93.mock.pstmn.io/rest/situ/pengajuanSurat/3" ;
//        PengajuanSuratResponse response = restTemplate.getForObject(path, PengajuanSuratResponse.class);
//        pengajuanSuratList = response.getResult();
//        System.out.println(pengajuanSuratList);
//        return pengajuanSuratList.toString();
////        return restTemplate.getForEntity(path, PengajuanSuratResponse.class).getBody().getResult().toString();
//    }

//    @GetMapping(value = "/listsurat")
//    private Mono<PengajuanSurat> getStatus(){
//        return peminjamanRuanganRestService.retrieveListSurat();
//    }


    @PostMapping(value = "/pinjem")
    private BaseResponse<PeminjamanRuanganModel> createPeminjamanRuangan(@Valid @RequestBody PeminjamanRuanganModel peminjamanruangan, BindingResult bindingResult) throws ParseException {
        BaseResponse<PeminjamanRuanganModel> response = new BaseResponse<PeminjamanRuanganModel>();
        RuanganModel calonruangan = null;
        List<RuanganModel> ruanganModels = ruanganRestService.getRuanganList();
        for (RuanganModel ruanganModel : ruanganModels) {
            if (peminjamanruangan.getRuanganModel().getId() == ruanganModel.getId()) {
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

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalmulai = formatter.format(peminjamanruangan.getTanggal_mulai());

            List<PeminjamanRuanganModel> peminjamanRuanganModelList = peminjamanRuanganService.getPeminjamanRuanganList();
            List<PeminjamanRuanganModel> peminjamanRuanganListsama = new ArrayList<>();
            List<PeminjamanRuanganModel> peminjamanRuanganListbeda = new ArrayList<>();

            for (PeminjamanRuanganModel peminjamanRuanganModel : peminjamanRuanganModelList) {
                if (Objects.equals(peminjamanruangan.getRuanganModel().getId(), peminjamanRuanganModel.getRuanganModel().getId())) {
                    System.out.println("1" + peminjamanruangan.getRuanganModel().getId());
                    System.out.println("2" + peminjamanRuanganModel.getRuanganModel().getId());
                    peminjamanRuanganListsama.add(peminjamanRuanganModel);
                } else {
                    peminjamanRuanganListbeda.add(peminjamanRuanganModel);

                }
            }


            System.out.println(peminjamanruangan.getRuanganModel().getId());
            boolean checkedsama = false;

            for (PeminjamanRuanganModel peminjamanRuanganModel1 : peminjamanRuanganListsama) {
                String tanggalmulaidb = formatter.format(peminjamanRuanganModel1.getTanggal_mulai());
                if (tanggalmulai.equals(tanggalmulaidb)) {
                    Date mulaidb = sdf.parse(peminjamanRuanganModel1.getWaktu_mulai());
                    System.out.println("tembus sama hari");
                    System.out.println(peminjamanruangan.getWaktu_mulai());
                    System.out.println(peminjamanRuanganModel1.getWaktu_mulai());
                    System.out.println("ini mulai yang bener " + mulai);
                    System.out.println("ini mulaidb yang bener " + mulaidb);
                    System.out.println(!(mulai.compareTo(mulaidb) == 0));
                    System.out.println(!(mulai.compareTo(mulaidb) < 0));
                    System.out.println(mulai.compareTo(mulaidb) < 0);
                    System.out.println(mulai.compareTo(mulaidb) > 0);
                    System.out.println(!(mulai.compareTo(mulaidb) > 0));
                    checkedsama = true;
                    if (!(mulai.compareTo(mulaidb) == 0)) {
                        System.out.println("tembus bdea waktu");
                        if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                            if (mulai.compareTo(akhir) < 0) {
                                if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                                    peminjamanruangan.setIs_disetujui(false);
                                    peminjamanruangan.setUserModelPenyetuju(null);
                                    peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
                                    response.setStatus(200);
                                    response.setMessage("success");
                                    response.setResult(peminjamanruangan);
                                    System.out.println("jebol tanggal beda jam beda");
                                } else {
                                    response.setStatus(500);
                                    response.setMessage("Peminjaman melebihi kapasitas ruangan");
                                }
                            } else {
                                response.setStatus(500);
                                response.setMessage("Waktu Peminjaman harus lebih awal");
                            }
                        } else {
                            response.setStatus(500);
                            response.setMessage("Tanggal Peminjaman harus lebih awal");
                        }
                    } else {
                        response.setStatus(500);
                        response.setMessage("Sudah ada peminjaman pada waktu dan tanggal tersebut");
                    }
                } else {
                    System.out.println("kelempar ke beda hari");
                    if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                        if (mulai.compareTo(akhir) < 0) {
                            if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {

                                peminjamanruangan.setIs_disetujui(false);
                                peminjamanruangan.setUserModelPenyetuju(null);
                                peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
                                response.setStatus(200);
                                response.setMessage("success");
                                response.setResult(peminjamanruangan);
                                System.out.println("jebol bead hari bead ruangan");
                            } else {
                                response.setStatus(500);
                                response.setMessage("Peminjaman melebihi kapasitas ruangan");
                            }
                        } else {
                            response.setStatus(500);
                            response.setMessage("Waktu Peminjaman harus lebih awal");
                        }
                    } else {
                        response.setStatus(500);
                        response.setMessage("Tanggal Peminjaman harus lebih awal");
                    }
                }
            }

//        System.out.println(peminjamanRuanganList);
            for (PeminjamanRuanganModel peminjamanRuanganModel1 : peminjamanRuanganListbeda) {
                if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                    if (mulai.compareTo(akhir) < 0) {
                        if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                            if (checkedsama == false) {

                                peminjamanruangan.setIs_disetujui(false);
                                peminjamanruangan.setUserModelPenyetuju(null);
                                peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
                                response.setStatus(200);
                                response.setMessage("success");
                                response.setResult(peminjamanruangan);
                                System.out.println("jebol ruangan sama beda hari");
                            } else {
                                response.setStatus(500);
                                response.setMessage("Sudah ada peminjaman pada waktu dan tanggal tersebut");
                            }
                        }
                    } else {
                        response.setStatus(500);
                        response.setMessage("Waktu Peminjaman harus lebih awal");
                    }
                } else {
                    response.setStatus(500);
                    response.setMessage("Tanggal Peminjaman harus lebih awal");
                }
            }
        }
        return response;
    }

//                else {
//                    System.out.println("kelempar ke beda ruangan");
//                    if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
//                        if (mulai.compareTo(akhir) < 0) {
//                            if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
//                                if(checkedsama==false){
//
//                                    peminjamanruangan.setIs_disetujui(false);
//                                    peminjamanruangan.setUserModelPenyetuju(null);
//                                    peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanruangan);
//                                    response.setStatus(200);
//                                    response.setMessage("success");
//                                    response.setResult(peminjamanruangan);
//                                    System.out.println("jebol ruangan sama beda hari");
//                                }else{
//                                    response.setStatus(500);
//                                    response.setMessage("Sudah ada peminjaman pada waktu dan tanggal tersebut");
//                                }
//                            }
//                        } else {
//                            response.setStatus(500);
//                            response.setMessage("Waktu Peminjaman harus lebih awal");
//                        }
//                    } else {
//                        response.setStatus(500);
//                        response.setMessage("Tanggal Peminjaman harus lebih awal");
//                    }






    @GetMapping(value = "/listruangan")
    private BaseResponse<List<RuanganModel>> retrieveRuangan(){
        BaseResponse<List<RuanganModel>> response = new BaseResponse<>();
        response.setMessage("success");
        response.setStatus(200);
        response.setResult(ruanganRestService.getRuanganList());
            return response;
    }

}

