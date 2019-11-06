package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Repository.PeminjamanRuanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService{
    @Autowired
    private PeminjamanRuanganDb peminjamanRuanganDb;

    public PeminjamanRuanganModel getPeminjamanRuanganById(Long idPeminjamanRuangan){
        return peminjamanRuanganDb.findById(idPeminjamanRuangan).get();
    }

    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDb.findAll();
    }
}
