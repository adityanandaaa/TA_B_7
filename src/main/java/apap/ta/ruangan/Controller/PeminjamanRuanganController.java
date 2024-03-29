package apap.ta.ruangan.Controller;


import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import apap.ta.ruangan.Rest.*;
import apap.ta.ruangan.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestService userRestService;
    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/tambah", method = RequestMethod.GET)
    public String addPeminjamanRuanganFormPage(Model model) {
        PeminjamanRuanganModel peminjamanRuanganModel = new PeminjamanRuanganModel();


        PengajuanSurat pengajuanSurat = new PengajuanSurat();
        PengajuanSuratModel pengajuanSuratModel = new PengajuanSuratModel();
        PengajuanSuratResponse pengajuanSuratResponse = new PengajuanSuratResponse();
        Long nomorsurat = 0L;
        model.addAttribute("nomorsurat",nomorsurat);
        model.addAttribute("pengajuanSuratModel",pengajuanSuratModel);
        model.addAttribute("pengajuanSuratResponse",pengajuanSuratResponse);
        model.addAttribute("pengajuanSurat",pengajuanSurat);
        model.addAttribute("peminjamanruangan", peminjamanRuanganModel);
        model.addAttribute("listOfRuangan",ruanganService.getRuanganList());
        model.addAttribute("pageTitle", "Add Peminjaman Ruangan");


        return "form-add-peminjaman-ruangan";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.POST)
    public String addPeminjamanRuanganPage(@ModelAttribute PeminjamanRuanganModel peminjamanruangan,
                                           Model model, PengajuanSuratModel pengajuanSuratModel) throws ParseException {
//        peminjamanRuanganService.addPeminjamRuangan(peminjamanruangan,pengajuanSuratModel);
//        return "view-all-peminjaman-ruangan";

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

        PengajuanSurat pengajuanSurat ;
        String path = "https://si-tu-b8.herokuapp.com/api/v1/situ/pengajuanSurat/" + pengajuanSuratModel.getHasil() ;
        PengajuanSuratResponse response = restTemplate.getForObject(path, PengajuanSuratResponse.class);
        pengajuanSurat = response.getResult();
//        Map<String, String> profile = null;
        String role = "default";

//
//        System.out.println(pengajuanSurat.getStatus());
//        System.out.println(pengajuanSurat.getIdUser());
        UserModel userpenyetuju = null;
        UserModel UserSurat;

        role = "employees";
        String pathemp = "http://sivitas.herokuapp.com/api/employees/" + pengajuanSurat.getIdUser();
        UserModelResponse responseemp = restTemplate.getForObject(pathemp, UserModelResponse.class);
        UserSurat = responseemp.getResult();
        System.out.println(UserSurat);

        userpenyetuju = userService.findByuuid(response.getResult().getIdUser());


//        if(pengajuanSurat.getIdUser()!= null) {
//            for (int i = 1; i <= 3; i++) {
//                if (i == 3) {
//                    role = "teachers";
//
////                    profile = userRestService.getUserProfile(role, pengajuanSurat.getIdUser());
////                    System.out.println(profile);
//
//                    String pathguru = "http://sivitas.herokuapp.com/api/teachers/" + pengajuanSurat.getIdUser();
//                    UserModelResponse responsete = restTemplate.getForObject(pathguru, UserModelResponse.class);
//                    UserSurat = responsete.getResult();
//
//                    userpenyetuju = UserSurat;
//                }
//                if (i == 2) {
//                    role = "students";
//
////                    profile = userRestService.getUserProfile(role, pengajuanSurat.getIdUser());
////                    System.out.println(profile);
//
//                    String pathguru = "http://sivitas.herokuapp.com/api/students/" + pengajuanSurat.getIdUser();
//                    UserModelResponse responstu = restTemplate.getForObject(pathguru, UserModelResponse.class);
//                    UserSurat = responstu.getResult();
//
//                    userpenyetuju = UserSurat;
//
//                } else {
//                    role = "employees";
//
////                    profile = userRestService.getUserProfile(role, pengajuanSurat.getIdUser());
////                    System.out.println(profile);
//
//                    String pathemp = "http://sivitas.herokuapp.com/api/employees/" + pengajuanSurat.getIdUser();
//                    UserModelResponse responseemp = restTemplate.getForObject(pathemp, UserModelResponse.class);
//                    UserSurat = responseemp.getResult();
//                    System.out.println(UserSurat);
//
//                    userpenyetuju = UserSurat;
//                }
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
                    if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())
                            || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
                        if (mulai.compareTo(akhir) < 0) {
                            if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                                if (pengajuanSurat.getStatus() >= 2) {
                                    peminjamanruangan.setIs_disetujui(true);
                                    peminjamanruangan.setUserModelPeminjam(login);
                                    peminjamanruangan.setUserModelPenyetuju(userpenyetuju);

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
                        if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()
                        || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
                            if (pengajuanSurat.getStatus() >= 2) {
                                peminjamanruangan.setIs_disetujui(true);
                                peminjamanruangan.setUserModelPeminjam(login);
                                peminjamanruangan.setUserModelPenyetuju(userpenyetuju);

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
            if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())
            || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
                if (mulai.compareTo(akhir) < 0) {
                    if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
                        if (checkedsama == false) {
                            if (pengajuanSurat.getStatus() >= 2) {
                                peminjamanruangan.setIs_disetujui(true);
                                peminjamanruangan.setUserModelPeminjam(login);
                                peminjamanruangan.setUserModelPenyetuju(userpenyetuju);

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
//        String message = "kentut sapi";
//        model.addAttribute("message", message);
        return "view-all-peminjaman-ruangan";
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
    public String viewAllruangan(Model model,Authentication authentication) {
        String role = authentication.getAuthorities().toString();
        model.addAttribute("role",role);


            List<PeminjamanRuanganModel> peminjamanRuanganList = peminjamanRuanganService.getPeminjamanRuanganList();
            List<RuanganModel> ruanganModelList = ruanganService.getRuanganList();

            model.addAttribute("peminjamanRuanganList", peminjamanRuanganList);
            model.addAttribute("ruanganModelList", ruanganModelList);
            model.addAttribute("pageTitle", "All Peminjaman Ruangan");
            model.addAttribute("pageFooter", "View Store");
            UserModel loggedUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("isAdminTU", loggedUser.getRole().getId() == 2);

            return "view-all-peminjaman-ruangan";

    }
    @RequestMapping(value = "/ubah-persetujuan", method = RequestMethod.GET)
    public String ubahPersetujuanForm(@RequestParam(value = "idPeminjamanRuangan", required = true) Long idPeminjamanRuangan, Model model){
        PeminjamanRuanganModel peminjamanruangan = peminjamanRuanganService.getPeminjamanRuanganById(idPeminjamanRuangan);
        model.addAttribute("peminjamanruangan", peminjamanruangan);
        model.addAttribute("pageTitle", "Ubah Persetujuan Peminjaman Ruangan");
        model.addAttribute("pageFooter", "View Store");

        return "form-ubah-persetujuan";
    }
    @RequestMapping(value = "/ubah-persetujuan", method = RequestMethod.POST)
    public String ubahPersetujuanSubmit(@RequestParam(value = "idPeminjamanRuangan", required = true) Long idPeminjamanRuangan, @ModelAttribute PeminjamanRuanganModel peminjamanRuanganModel, Model model){
        UserModel loggedUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        PeminjamanRuanganModel peminjamanruangan = peminjamanRuanganService.ubahPersetujuan(idPeminjamanRuangan, peminjamanRuanganModel.getIs_disetujui(), loggedUser);
        model.addAttribute("peminjamanruangan", peminjamanruangan);
        model.addAttribute("pageTitle", "Ubah Persetujuan Peminjaman Ruangan");
        model.addAttribute("pageFooter", "View Store");

        return "form-ubah-persetujuan";
    }
}
