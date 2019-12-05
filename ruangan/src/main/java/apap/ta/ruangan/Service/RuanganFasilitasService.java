package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;

import java.util.List;
import java.util.Optional;

public interface RuanganFasilitasService {
    List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel);
    List<RuanganFasilitasModel> getAllFasilitas();
    void addFasilitas(RuanganFasilitasModel ruanganFasilitasModel, String namaFasilitas);
    Optional<RuanganFasilitasModel> getRuanganFasilitasById(Long id);
    List<RuanganFasilitasModel> getRuanganFasilitasByFasilitas(FasilitasModel fasilitasModel);
    Boolean isFasilitasJumlahEnough(FasilitasModel fasilitasModel, long demand, long current);
    Boolean isRuanganKapasitasEnough(RuanganModel ruang, long demand, long current);
    RuanganFasilitasModel ubahJumlahFasilitas(RuanganFasilitasModel ruangFasilitas, long newJumlah);
    void deleteRuanganFasilitas(RuanganFasilitasModel ruanganFasilitasModel);

}
