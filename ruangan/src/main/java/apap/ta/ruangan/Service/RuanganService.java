package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganModel;

import java.util.List;

public interface RuanganService {

//    void addRuangan(RuanganModel ruanganModel);

    RuanganModel getRuanganById(Long idRuangan);

    List<RuanganModel> getRuanganList();



}
