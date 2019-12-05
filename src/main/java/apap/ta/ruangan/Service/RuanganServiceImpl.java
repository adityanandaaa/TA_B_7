package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Repository.RuanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    private RuanganDb ruanganDb;

    @Override
    public RuanganModel getRuanganById(Long idRuangan){
        return ruanganDb.findById(idRuangan).get();
    }

    @Override
    public List<RuanganModel> getRuanganList(){
        return ruanganDb.findAll();
    }
}
