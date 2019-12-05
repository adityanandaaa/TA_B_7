package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Repository.FasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FasilitasServiceImpl implements FasilitasService {

    @Autowired
    private FasilitasDb fasilitasDb;

    @Override
    public List<FasilitasModel> getFasilitasList(){
        return fasilitasDb.findAll();
    }

    @Override
    public Long getAvailableStok(FasilitasModel fasilitasModel) {
        FasilitasModel fasilitas = fasilitasDb.findFasilitasModelById(fasilitasModel.getId());
        Long jumlahFasilitas = fasilitas.getJumlah();
        List<RuanganFasilitasModel> listFasilitasRuangan = fasilitas.getListRuangan();
        if(fasilitas.getListRuangan().size()==0){
            return fasilitas.getJumlah();
        }
        else{
            Long stokFasilitasTersedia = jumlahFasilitas;
            for(int i = 0; i<listFasilitasRuangan.size(); i++){
                stokFasilitasTersedia = stokFasilitasTersedia - listFasilitasRuangan.get(i).getJumlah_fasilitas();
            }
            return  stokFasilitasTersedia;
        }
    }

    @Override
    public FasilitasModel getFasilitasById(Long id) {
        return fasilitasDb.findFasilitasModelById(id);
    }

    @Override
    public FasilitasModel getFasilitasByNama(String nama) {
        return fasilitasDb.findFasilitasModelByNama(nama);
    }
}
