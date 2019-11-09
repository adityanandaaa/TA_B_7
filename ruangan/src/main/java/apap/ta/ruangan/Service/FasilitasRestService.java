package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;

import java.util.List;

public interface FasilitasRestService {

    List<FasilitasModel> retrieveListFasilitasModel();

    FasilitasModel getFasilitasById(Long id);
}
