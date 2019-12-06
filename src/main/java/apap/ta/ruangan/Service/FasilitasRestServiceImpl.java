package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Repository.FasilitasDb;
import apap.ta.ruangan.Rest.FasilitasResponse;
import apap.ta.ruangan.Rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FasilitasRestServiceImpl implements FasilitasRestService {

//    private final WebClient webClient;

    @Autowired
    private FasilitasDb fasilitasDb;

    @Override
    public List<FasilitasModel> retrieveListFasilitasModel(){
        return fasilitasDb.findAll();
    }

    @Override
    public FasilitasModel getFasilitasById(Long id){
        return fasilitasDb.findById(id).get();
    }

//    public FasilitasRestServiceImpl(WebClient.Builder webClientBuilder){
//        this.webClient = webClientBuilder.baseUrl(Setting.baseUrl).build();
//    }
//
//    @Override
//    public Mono<FasilitasResponse> retrieveBranch(FasilitasModel fasilitasModel){
//        return this.webClient.get().uri("api/v1/fasilitas?namaRuangan="+fasilitasModel)
//                .retrieve().bodyToMono(FasilitasResponse.class);
//    }
}

