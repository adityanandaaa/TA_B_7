package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Rest.PengajuanSuratModel;

import java.text.ParseException;
import java.util.List;

public interface PeminjamanRuanganService {

//    void addPeminjamRuangan(PeminjamanRuanganModel peminjamanRuanganModel, PengajuanSuratModel pengajuanSuratModel) throws ParseException;
    void addPeminjamRuangan(PeminjamanRuanganModel peminjamanRuanganModel);
    PeminjamanRuanganModel getPeminjamanRuanganById(Long idPeminjamanRuangan);
    List<PeminjamanRuanganModel> getPeminjamanRuanganList();
    PeminjamanRuanganModel ubahPersetujuan(Long idPeminjamanRuangan, boolean newIsDisetujui, UserModel userPenyetuju);
}
