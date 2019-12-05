package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Model.UserModel;
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

    @Override
    public PeminjamanRuanganModel getPeminjamanRuanganById(Long idPeminjamanRuangan){
        return peminjamanRuanganDb.findById(idPeminjamanRuangan).get();
    }

    @Override
    public void addPeminjamRuangan(PeminjamanRuanganModel peminjamanRuanganModel){
        peminjamanRuanganDb.save(peminjamanRuanganModel);
    }

    @Override
    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDb.findAll();
    }

    @Override
    public PeminjamanRuanganModel ubahPersetujuan(Long idPeminjamanRuangan, boolean newIsDisetujui, UserModel userPenyetuju) {
        PeminjamanRuanganModel peminjamanRuanganModel = getPeminjamanRuanganById(idPeminjamanRuangan);
        peminjamanRuanganModel.setIs_disetujui(newIsDisetujui);
        if(newIsDisetujui) {
            peminjamanRuanganModel.setUserModelPenyetuju(userPenyetuju);
        }
        else {
            peminjamanRuanganModel.setUserModelPenyetuju(null);
        }
        peminjamanRuanganDb.save(peminjamanRuanganModel);
        return peminjamanRuanganModel;
    }
}
