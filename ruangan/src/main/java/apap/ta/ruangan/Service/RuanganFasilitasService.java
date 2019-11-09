package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;

import java.util.List;

public interface RuanganFasilitasService {
    List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel);
}
