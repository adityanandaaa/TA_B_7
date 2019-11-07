package apap.ta.ruangan.Service;

import java.util.List;

import apap.ta.ruangan.Model.PengadaanFasilitasModel;

public interface PengadaanFasilitasService {
    List<PengadaanFasilitasModel> getAllPengadaanFasilitas();
    PengadaanFasilitasModel getPengadaanFasilitasById(Long id);
    void deletePengadaanFasilitas(Long id);
}