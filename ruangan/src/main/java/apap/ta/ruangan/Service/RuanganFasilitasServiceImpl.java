package apap.ta.ruangan.Service;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import apap.ta.ruangan.Repository.FasilitasDb;
import apap.ta.ruangan.Repository.RuanganFasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RuanganFasilitasServiceImpl implements RuanganFasilitasService {

    @Autowired
    private RuanganFasilitasDb ruanganFasilitasDb;

    @Autowired
    private FasilitasDb fasilitasDb;

    @Autowired
    private FasilitasService fasilitasService;

    @Autowired
    private RuanganService ruanganService;

    @Override
    public List<RuanganFasilitasModel> getRuanganFasilitasByRuangan(RuanganModel ruanganModel){
        return ruanganFasilitasDb.findByRuanganModel(ruanganModel);
    }


    @Override
    public List<RuanganFasilitasModel> getAllFasilitas() {
        return ruanganFasilitasDb.findAll();
    }

    @Override
    public void addFasilitas(RuanganFasilitasModel ruanganFasilitasModel, String namaFasilitas) {

        FasilitasModel fasilitas = fasilitasService.getFasilitasByNama(namaFasilitas);
        RuanganModel ruanganModel = ruanganFasilitasModel.getRuanganModel();

        if(fasilitas != null){
             RuanganFasilitasModel ruanganFasilitasModelObj = ruanganFasilitasDb.findByRuanganModelAndFasilitasModel(ruanganModel, fasilitas);
             if(ruanganFasilitasModelObj != null){
                 long jumlahFasilitasUpdate = ruanganFasilitasModelObj.getJumlah_fasilitas() + ruanganFasilitasModel.getJumlah_fasilitas();
                 ruanganFasilitasModelObj.setJumlah_fasilitas(jumlahFasilitasUpdate);

                 long jumlahFasilitasAllUpdate = fasilitas.getJumlah() + ruanganFasilitasModel.getJumlah_fasilitas();
                 fasilitas.setJumlah(jumlahFasilitasAllUpdate);
             }
             else{
                 RuanganFasilitasModel fasilitasRuanganBaru = new RuanganFasilitasModel();
                 fasilitasRuanganBaru.setRuanganModel(ruanganModel);
                 fasilitasRuanganBaru.setJumlah_fasilitas(ruanganFasilitasModel.getJumlah_fasilitas());
                 fasilitasRuanganBaru.setFasilitasModel(fasilitas);
                 ruanganFasilitasDb.save(fasilitasRuanganBaru);

                 FasilitasModel fasilitasExist = fasilitasService.getFasilitasByNama(namaFasilitas);
                 long jumlahUpdate = fasilitasExist.getJumlah() + fasilitasRuanganBaru.getJumlah_fasilitas();
                 fasilitasExist.setJumlah(jumlahUpdate);
                 fasilitasDb.save(fasilitasExist);

                 RuanganModel ruanganExist = ruanganService.getRuanganById(ruanganModel.getId());
                 ruanganExist.getListFasilitas().add(fasilitasRuanganBaru);
             }
        }
        else{
            FasilitasModel fasilitasModel = new FasilitasModel();

            RuanganFasilitasModel fasilitasRuanganNew = new RuanganFasilitasModel();
            fasilitasRuanganNew.setRuanganModel(ruanganModel);
            fasilitasRuanganNew.setJumlah_fasilitas(ruanganFasilitasModel.getJumlah_fasilitas());
            fasilitasRuanganNew.setFasilitasModel(fasilitasModel);
            ruanganFasilitasDb.save(fasilitasRuanganNew);

            fasilitasModel.setNama(namaFasilitas);
            fasilitasModel.setJumlah(ruanganFasilitasModel.getJumlah_fasilitas());
            List<RuanganFasilitasModel> listRuanganFasilitasNew = new ArrayList<RuanganFasilitasModel>();
            fasilitasModel.setListRuangan(listRuanganFasilitasNew);
            fasilitasModel.getListRuangan().add(fasilitasRuanganNew);
            fasilitasDb.save(fasilitasModel);

            RuanganModel existRuangan = ruanganService.getRuanganById(ruanganModel.getId());
            existRuangan.getListFasilitas().add(fasilitasRuanganNew);



        }
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
