package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Repository.PeminjamanRuanganDb;
import apap.ta.ruangan.Rest.PengajuanSurat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {


    @Autowired
    PeminjamanRuanganDb peminjamanRuanganDb;


    @Override
    public PeminjamanRuanganModel createPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel){
        peminjamanRuanganModel.setRuanganModel(peminjamanRuanganModel.getRuanganModel());
        return peminjamanRuanganDb.save(peminjamanRuanganModel);
    }

    @Override
    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDb.findAll();
    }

}
