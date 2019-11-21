package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import apap.ta.ruangan.Rest.PengajuanSurat;
import apap.ta.ruangan.Rest.PengajuanSuratResponse;
import apap.ta.ruangan.Service.PeminjamanRuanganService;
import apap.ta.ruangan.Service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/peminjaman-ruangan")
public class PeminjamanRuanganController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private UserDb userDb;


    @RequestMapping(value = "/tambah", method = RequestMethod.GET)
    public String addPeminjamanRuanganFormPage(Model model) {
        PeminjamanRuanganModel peminjamanRuanganModel = new PeminjamanRuanganModel();

        List<PengajuanSurat> pengajuanSuratList = new ArrayList<>();

        String path = "https://d3358147-6e01-490c-a290-3d8c320c4f93.mock.pstmn.io/rest/situ/pengajuanSurat/ruangan" ;
        PengajuanSuratResponse response = restTemplate.getForObject(path, PengajuanSuratResponse.class);
        pengajuanSuratList = response.getResult();
        
        PengajuanSurat pengajuanSurat = new PengajuanSurat();
        model.addAttribute("pengajuanSurat",pengajuanSurat);
        model.addAttribute("peminjamanruangan", peminjamanRuanganModel);
        model.addAttribute("listOfRuangan",ruanganService.getRuanganList());
        model.addAttribute("pageTitle", "Add Peminjaman Ruangan");
        model.addAttribute("listPengajuanSurat",pengajuanSuratList);

        return "form-add-peminjaman-ruangan";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.POST)
    public String addPeminjamanRuanganPage(@ModelAttribute PeminjamanRuanganModel peminjamanruangan,PengajuanSurat pengajuanSurat,
                                           Model model, Principal principal,final BindingResult bindingResult) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserModel> listuser = userDb.findAll();
        UserModel login = null;
        String currentPrincipalName = authentication.getName();
        for (UserModel userModel : listuser) {
            if (currentPrincipalName.equals(userModel.getUsername())) {
                login = userModel;

            }
        }
        RuanganModel calonruangan = null;
        List<RuanganModel> ruanganModels = ruanganService.getRuanganList();
        for (RuanganModel ruanganModel : ruanganModels) {
            if (peminjamanruangan.getRuanganModel().getId() == ruanganModel.getId()) {
                calonruangan = ruanganModel;
            }
        }
//        List<PengajuanSurat> pengajuanSuratList = new ArrayList<>();
//
//        String path = "https://d3358147-6e01-490c-a290-3d8c320c4f93.mock.pstmn.io/rest/situ/pengajuanSurat/ruangan" ;
//        PengajuanSuratResponse response = restTemplate.getForObject(path, PengajuanSuratResponse.class);
//        pengajuanSuratList = response.getResult();
//        for(PengajuanSurat pengajuanSurat1 : pengajuanSuratList){
//            if(pengajuanSurat1.getStatus() == pengajuanSurat.getStatus()){
//                pengajuanSurat = pengajuanSurat1;
//            }
//        }
        String messages;
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
                                if (pengajuanSurat.getStatus() >= 2) {
                                    peminjamanruangan.setIs_disetujui(true);
                                    peminjamanruangan.setUserModelPeminjam(login);
                                    peminjamanruangan.setUserModelPenyetuju(null);

                                    peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                    model.addAttribute("peminjamanruangan", peminjamanruangan);
                                    return "add-peminjaman-ruangan";

                                } else {
                                    peminjamanruangan.setIs_disetujui(false);
                                    peminjamanruangan.setUserModelPenyetuju(null);
                                    peminjamanruangan.setUserModelPeminjam(login);

                                    peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                    model.addAttribute("peminjamanruangan", peminjamanruangan);
                                    return "add-peminjaman-ruangan";
                                }
                            } else {
                                messages = "Kapasitas tidak sesuai";
                                model.addAttribute("messages", messages);
                                return "gabisa-add-peminjaman-ruangan";
                            }
                        } else {
                            messages = "Waktu tidak sesuai";
                            model.addAttribute("messages", messages);
                            return "gabisa-add-peminjaman-ruangan";
                        }
                    } else {
                        messages = "Tanggal tidak sesuai";
                        model.addAttribute("messages", messages);
                        return "gabisa-add-peminjaman-ruangan";
                    }
                } else {
                    messages = "Sudah ada peminjaman pada waktu dan tanggal tersebut";
                    model.addAttribute("messages", messages);
                    return "gabisa-add-peminjaman-ruangan";
                }
            } else {
                System.out.println("kelempar ke beda hari");
                if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                    if (mulai.compareTo(akhir) < 0) {
                        if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                            if (pengajuanSurat.getStatus() >= 2) {
                                peminjamanruangan.setIs_disetujui(true);
                                peminjamanruangan.setUserModelPeminjam(login);
                                peminjamanruangan.setUserModelPenyetuju(null);

                                peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                model.addAttribute("peminjamanruangan", peminjamanruangan);
                                return "add-peminjaman-ruangan";

                            } else {
                                peminjamanruangan.setIs_disetujui(false);
                                peminjamanruangan.setUserModelPenyetuju(null);
                                peminjamanruangan.setUserModelPeminjam(login);

                                peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                model.addAttribute("peminjamanruangan", peminjamanruangan);
                                return "add-peminjaman-ruangan";
                            }
                        } else {
                            messages = "Kapasitas tidak sesuai";
                            model.addAttribute("messages", messages);
                            return "gabisa-add-peminjaman-ruangan";
                        }
                    } else {
                        messages = "Waktu tidak sesuai";
                        model.addAttribute("messages", messages);
                        return "gabisa-add-peminjaman-ruangan";
                    }
                } else {
                    messages = "Tanggal tidak sesuai";
                    model.addAttribute("messages", messages);
                    return "gabisa-add-peminjaman-ruangan";
                }
            }
        }

//        System.out.println(peminjamanRuanganList);
        for (PeminjamanRuanganModel peminjamanRuanganModel1 : peminjamanRuanganListbeda) {
            if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
                if (mulai.compareTo(akhir) < 0) {
                    if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                        if (checkedsama == false) {
                            if (pengajuanSurat.getStatus() >= 2) {
                                peminjamanruangan.setIs_disetujui(true);
                                peminjamanruangan.setUserModelPeminjam(login);
                                peminjamanruangan.setUserModelPenyetuju(null);

                                peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                model.addAttribute("peminjamanruangan", peminjamanruangan);
                                return "add-peminjaman-ruangan";

                            } else {
                                peminjamanruangan.setIs_disetujui(false);
                                peminjamanruangan.setUserModelPenyetuju(null);
                                peminjamanruangan.setUserModelPeminjam(login);

                                peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan);
                                model.addAttribute("peminjamanruangan", peminjamanruangan);
                                return "add-peminjaman-ruangan";
                            }
                        }else{
                            messages = "Sudah ada peminjaman pada waktu dan tanggal tersebut";
                            model.addAttribute("messages", messages);
                            return "gabisa-add-peminjaman-ruangan";
                        }
                    } else {
                        messages = "Kapasitas tidak sesuai";
                        model.addAttribute("messages", messages);
                        return "gabisa-add-peminjaman-ruangan";
                    }
                } else {
                    messages = "Waktu tidak sesuai";
                    model.addAttribute("messages", messages);
                    return "gabisa-add-peminjaman-ruangan";
                }
            } else {
                messages = "Tanggal tidak sesuai";
                model.addAttribute("messages", messages);
                return "gabisa-add-peminjaman-ruangan";
            }
        }

        return null;
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
        List<RuanganModel> ruanganModelList = ruanganService.getRuanganList();

        model.addAttribute("peminjamanRuanganList", peminjamanRuanganList);
        model.addAttribute("ruanganModelList",ruanganModelList);
        model.addAttribute("pageTitle", "All Peminjaman Ruangan");
        model.addAttribute("pageFooter", "View Store");

        return "view-all-peminjaman-ruangan";
    }

}
