package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;

import java.util.List;

public interface PeminjamanRuanganService {

    void addPeminjamRuangan(PeminjamanRuanganModel peminjamanRuanganModel);
    PeminjamanRuanganModel getPeminjamanRuanganById(Long idPeminjamanRuangan);
    List<PeminjamanRuanganModel> getPeminjamanRuanganList();
}
