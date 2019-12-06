package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Repository.RuanganFasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuanganFasilitasRestServiceImpl implements RuanganFasilitasRestService {

    @Autowired
    private RuanganFasilitasDb ruanganFasilitasDb;

    @Override
    public List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel){
        return ruanganFasilitasDb.findByRuanganModel(ruanganModel);
    }
}
