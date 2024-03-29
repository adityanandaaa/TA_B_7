package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;

import java.util.List;

public interface FasilitasService {
    List<FasilitasModel> getFasilitasList();
    Long getAvailableStok(FasilitasModel fasilitasModel);
    FasilitasModel getFasilitasById(Long id);
    FasilitasModel getFasilitasByNama(String nama);
}
