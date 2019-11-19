package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Repository.PeminjamanRuanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}
