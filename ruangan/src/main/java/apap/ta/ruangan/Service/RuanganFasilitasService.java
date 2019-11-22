package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;

import java.util.List;

public interface RuanganFasilitasService {
    List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel);
    //void addFasilitasRuangan(RuanganFasilitasModel ruanganFasilitasModel);
    List<RuanganFasilitasModel> getAllFasilitas();
    void addFasilitas(RuanganFasilitasModel ruanganFasilitasModel, String namaFasilitas);
}
