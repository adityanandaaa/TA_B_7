package apap.ta.ruangan.Service;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Repository.RuanganFasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RuanganFasilitasServiceImpl implements RuanganFasilitasService {

    @Autowired
    private RuanganFasilitasDb ruanganFasilitasDb;

    @Override
    public List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel){
        return ruanganFasilitasDb.findByRuanganModel(ruanganModel);
    }

    @Override
    public Optional<RuanganFasilitasModel> getRuanganFasilitasById(Long id){
        return ruanganFasilitasDb.findById(id);
    }

    @Override
    public List<RuanganFasilitasModel> getRuanganFasilitasByFasilitas(FasilitasModel fasilitasModel) {
        return ruanganFasilitasDb.findByFasilitasModel(fasilitasModel);
    }

    @Override
    public Boolean isFasilitasJumlahEnough(FasilitasModel fasilitas, long demand, long current) {
        List<RuanganFasilitasModel> usingRuangans = ruanganFasilitasDb.findByFasilitasModel(fasilitas);
        long used = 0;
        for(RuanganFasilitasModel ruang : usingRuangans){
            used += ruang.getJumlah_fasilitas();
        }
        used = used - current + demand;
        if(used > fasilitas.getJumlah()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean isRuanganKapasitasEnough(RuanganModel ruangan, long demand, long current) {
        List<RuanganFasilitasModel> fasilitasUsed = ruanganFasilitasDb.findByRuanganModel(ruangan);
        long used = 0;
        for(RuanganFasilitasModel fas : fasilitasUsed){
            used += fas.getJumlah_fasilitas();
        }
        used = used - current + demand;
        if(used > ruangan.getKapasitas()){
            return false;
        }
        return true;
    }

    @Override
    public RuanganFasilitasModel ubahJumlahFasilitas(RuanganFasilitasModel ruangFasilitas, long newJumlah){
        RuanganFasilitasModel ruFas = ruanganFasilitasDb.findById(ruangFasilitas.getId()).get();
        ruFas.setJumlah_fasilitas(newJumlah);
        ruanganFasilitasDb.save(ruFas);
        return ruFas;

    }
}
