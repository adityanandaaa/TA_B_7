package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganModel;

import java.util.List;

public interface RuanganRestService {

    RuanganModel getRuanganByNama (String nama);
    List<RuanganModel> getRuanganList();
}
