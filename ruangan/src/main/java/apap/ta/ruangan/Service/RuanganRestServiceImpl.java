package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Repository.RuanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RuanganRestServiceImpl implements RuanganRestService {

    @Autowired
    private RuanganDb ruanganDb;

    @Override
    public RuanganModel getRuanganByNama (String nama){
        return ruanganDb.findByNama(nama);
    }
}
