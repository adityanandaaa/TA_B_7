package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Repository.PeminjamanRuanganDb;
import apap.ta.ruangan.Rest.PengajuanSurat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {

    private final WebClient webClient;

    @Autowired
    PeminjamanRuanganDb peminjamanRuanganDb;

    public PeminjamanRuanganRestServiceImpl (WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://d3358147-6e01-490c-a290-3d8c320c4f93.mock.pstmn.io/rest/situ/pengajuanSurat/3").build();
    }

    @Override
    public PeminjamanRuanganModel createPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel){
        peminjamanRuanganModel.setRuanganModel(peminjamanRuanganModel.getRuanganModel());
        return peminjamanRuanganDb.save(peminjamanRuanganModel);
    }

    @Override
    public Mono<PengajuanSurat> retrieveListSurat(){
        return this.webClient.get().retrieve().bodyToMono(PengajuanSurat.class);

    }

}
