package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.PeminjamanRuanganDb;
import apap.ta.ruangan.Repository.UserDb;
import apap.ta.ruangan.Rest.PengajuanSurat;
import apap.ta.ruangan.Rest.PengajuanSuratModel;
import apap.ta.ruangan.Rest.PengajuanSuratResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService{



    @Autowired
    private PeminjamanRuanganDb peminjamanRuanganDb;

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private UserDb userDb;

    @Autowired
    private UserService userService;
    @Override
    public PeminjamanRuanganModel getPeminjamanRuanganById(Long idPeminjamanRuangan){
        return peminjamanRuanganDb.findById(idPeminjamanRuangan).get();
    }

    @Override
    public void addPeminjamRuangan(PeminjamanRuanganModel peminjamanruangan){
        peminjamanRuanganDb.save(peminjamanruangan);
    }

//    @Override
//    public void addPeminjamRuangan(PeminjamanRuanganModel peminjamanruangan, PengajuanSuratModel pengajuanSuratModel) throws ParseException {
//
//
//        //        peminjamanRuanganDb.save(peminjamanruangan);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        List<UserModel> listuser = userDb.findAll();
//        UserModel login = null;
//        String currentPrincipalName = authentication.getName();
//        for (UserModel userModel : listuser) {
//            if (currentPrincipalName.equals(userModel.getUsername())) {
//                login = userModel;
//
//            }
//        }
//        RuanganModel calonruangan = null;
//        List<RuanganModel> ruanganModels = ruanganService.getRuanganList();
//        for (RuanganModel ruanganModel : ruanganModels) {
//            if (peminjamanruangan.getRuanganModel().getId() == ruanganModel.getId()) {
//                calonruangan = ruanganModel;
//            }
//        }
//        PengajuanSurat pengajuanSurat;
//        String path = "https://d3358147-6e01-490c-a290-3d8c320c4f93.mock.pstmn.io/rest/situ/pengajuanSurat/" + pengajuanSuratModel.getHasil();
//        PengajuanSuratResponse response = restTemplate.getForObject(path, PengajuanSuratResponse.class);
//        pengajuanSurat = response.getResult();
//
//        UserModel userpenyetuju = userDb.findById(pengajuanSurat.getIdUser());
//        String messages;
//        DateFormat sdf = new SimpleDateFormat("hh:mm");
//        Date mulai = sdf.parse(peminjamanruangan.getWaktu_mulai());
//        Date akhir = sdf.parse(peminjamanruangan.getWaktu_selesai());
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String tanggalmulai = formatter.format(peminjamanruangan.getTanggal_mulai());
//
//        List<PeminjamanRuanganModel> peminjamanRuanganModelList = peminjamanRuanganService.getPeminjamanRuanganList();
//        List<PeminjamanRuanganModel> peminjamanRuanganListsama = new ArrayList<>();
//        List<PeminjamanRuanganModel> peminjamanRuanganListbeda = new ArrayList<>();
//
//        for (PeminjamanRuanganModel peminjamanRuanganModel : peminjamanRuanganModelList) {
//            if (Objects.equals(peminjamanruangan.getRuanganModel().getId(), peminjamanRuanganModel.getRuanganModel().getId())) {
//                System.out.println("1" + peminjamanruangan.getRuanganModel().getId());
//                System.out.println("2" + peminjamanRuanganModel.getRuanganModel().getId());
//                peminjamanRuanganListsama.add(peminjamanRuanganModel);
//            } else {
//                peminjamanRuanganListbeda.add(peminjamanRuanganModel);
//
//            }
//        }
//
//        System.out.println(peminjamanruangan.getRuanganModel().getId());
//        boolean checkedsama = false;
//
//        for (PeminjamanRuanganModel peminjamanRuanganModel1 : peminjamanRuanganListsama) {
//            String tanggalmulaidb = formatter.format(peminjamanRuanganModel1.getTanggal_mulai());
//            if (tanggalmulai.equals(tanggalmulaidb)) {
//                Date mulaidb = sdf.parse(peminjamanRuanganModel1.getWaktu_mulai());
//                System.out.println("tembus sama hari");
//                System.out.println("tanggal mulai" + tanggalmulaidb);
//                System.out.println("tanggal mulai" + tanggalmulai);
//                System.out.println(peminjamanruangan.getWaktu_mulai());
//                System.out.println(peminjamanRuanganModel1.getWaktu_mulai());
//                System.out.println("ini mulai yang bener " + mulai);
//                System.out.println("ini mulaidb yang bener " + mulaidb);
//                System.out.println(!(mulai.compareTo(mulaidb) == 0));
//                System.out.println(!(mulai.compareTo(mulaidb) < 0));
//                System.out.println(mulai.compareTo(mulaidb) < 0);
//                System.out.println(mulai.compareTo(mulaidb) > 0);
//                System.out.println(!(mulai.compareTo(mulaidb) > 0));
//                checkedsama = true;
//                if (!(mulai.compareTo(mulaidb) == 0)) {
//                    System.out.println("tembus bdea waktu");
//                    if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())
//                            || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
//                        if (mulai.compareTo(akhir) < 0) {
//                            if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
//                                if (pengajuanSurat.getStatus() >= 2) {
//                                    peminjamanruangan.setIs_disetujui(true);
//                                    peminjamanruangan.setUserModelPeminjam(login);
//                                    peminjamanruangan.setUserModelPenyetuju(userpenyetuju);
//
//                                    peminjamanRuanganDb.save(peminjamanruangan);
//
//
//                                } else {
//                                    peminjamanruangan.setIs_disetujui(false);
//                                    peminjamanruangan.setUserModelPenyetuju(null);
//                                    peminjamanruangan.setUserModelPeminjam(login);
//
//                                    peminjamanRuanganDb.save(peminjamanruangan);
//
//                                }
//                            } else {
//                                messages = "Kapasitas tidak sesuai";
//
//                            }
//                        } else {
//                            messages = "Waktu tidak sesuai";
//
//                        }
//                    } else {
//                        messages = "Tanggal tidak sesuai";
//
//                    }
//                } else {
//                    messages = "Sudah ada peminjaman pada waktu dan tanggal tersebut";
//
//                }
//            } else {
//                System.out.println("kelempar ke beda hari");
//                if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())) {
//                    if (mulai.compareTo(akhir) < 0) {
//                        if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()
//                                || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
//                            if (pengajuanSurat.getStatus() >= 2) {
//                                peminjamanruangan.setIs_disetujui(true);
//                                peminjamanruangan.setUserModelPeminjam(login);
//                                peminjamanruangan.setUserModelPenyetuju(userpenyetuju);
//
//                                peminjamanRuanganDb.save(peminjamanruangan);
//
//
//                            } else {
//                                peminjamanruangan.setIs_disetujui(false);
//                                peminjamanruangan.setUserModelPenyetuju(null);
//                                peminjamanruangan.setUserModelPeminjam(login);
//
//                                peminjamanRuanganDb.save(peminjamanruangan);
//
//                            }
//                        } else {
//                            messages = "Kapasitas tidak sesuai";
//
//                        }
//                    } else {
//                        messages = "Waktu tidak sesuai";
//
//                    }
//                } else {
//                    messages = "Tanggal tidak sesuai";
//
//                }
//            }
//        }
//
////        System.out.println(peminjamanRuanganList);
//        for (PeminjamanRuanganModel peminjamanRuanganModel1 : peminjamanRuanganListbeda) {
//            if (peminjamanruangan.getTanggal_mulai().before(peminjamanruangan.getTanggal_selesai())
//                    || peminjamanruangan.getTanggal_mulai().equals(peminjamanruangan.getTanggal_selesai())) {
//                if (mulai.compareTo(akhir) < 0) {
//                    if (peminjamanruangan.getJumlah_peserta() < calonruangan.getKapasitas()) {
//                        if (checkedsama == false) {
//                            if (pengajuanSurat.getStatus() >= 2) {
//                                peminjamanruangan.setIs_disetujui(true);
//                                peminjamanruangan.setUserModelPeminjam(login);
//                                peminjamanruangan.setUserModelPenyetuju(userpenyetuju);
//
//                                peminjamanRuanganDb.save(peminjamanruangan);
//
//
//                            } else {
//                                peminjamanruangan.setIs_disetujui(false);
//                                peminjamanruangan.setUserModelPenyetuju(null);
//                                peminjamanruangan.setUserModelPeminjam(login);
//
//                                peminjamanRuanganDb.save(peminjamanruangan);
//
//                            }
//                        } else {
//                            messages = "Sudah ada peminjaman pada waktu dan tanggal tersebut";
//
//                        }
//                    } else {
//                        messages = "Kapasitas tidak sesuai";
//
//                    }
//                } else {
//                    messages = "Waktu tidak sesuai";
//
//                }
//            } else {
//                messages = "Tanggal tidak sesuai";
//
//            }
//        }

//    }
    @Override
    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDb.findAll();
    }

    @Override
    public PeminjamanRuanganModel ubahPersetujuan(Long idPeminjamanRuangan, boolean newIsDisetujui, UserModel userPenyetuju) {
        PeminjamanRuanganModel peminjamanRuanganModel = getPeminjamanRuanganById(idPeminjamanRuangan);
        peminjamanRuanganModel.setIs_disetujui(newIsDisetujui);
        if(newIsDisetujui) {
            peminjamanRuanganModel.setUserModelPenyetuju(userPenyetuju);
        }
        else {
            peminjamanRuanganModel.setUserModelPenyetuju(null);
        }
        peminjamanRuanganDb.save(peminjamanRuanganModel);
        return peminjamanRuanganModel;
    }
}
