package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Rest.FasilitasResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FasilitasRestService {

    List<FasilitasModel> retrieveListFasilitasModel();

    FasilitasModel getFasilitasById(Long id);
//
//    Mono<FasilitasResponse> retrieveBranch(FasilitasModel fasilitasModel);
}
