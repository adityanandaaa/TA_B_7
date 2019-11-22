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

//    @Override
//    public void addFasilitasRuangan(RuanganFasilitasModel ruanganFasilitasModel) {
//        ruanganFasilitasDb.save(ruanganFasilitasModel);
//    }

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


}
