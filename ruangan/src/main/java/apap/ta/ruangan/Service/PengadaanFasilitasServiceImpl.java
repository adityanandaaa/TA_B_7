package apap.ta.ruangan.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.ruangan.Model.PengadaanFasilitasModel;
import apap.ta.ruangan.Repository.PengadaanFasilitasDb;

@Service
@Transactional
public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService {
    @Autowired
    private PengadaanFasilitasDb pengadaanFasilitasDb;

    @Override
    public List<PengadaanFasilitasModel> getAllPengadaanFasilitas() {
        return pengadaanFasilitasDb.findAll();
    }

    @Override
    public PengadaanFasilitasModel getPengadaanFasilitasById(Long id) {
        if(pengadaanFasilitasDb.findById(id).isPresent()){
            return pengadaanFasilitasDb.findById(id).get();
        }
        return null;
    }

    @Override
    public void deletePengadaanFasilitas(Long id) {
        pengadaanFasilitasDb.deleteById(id);
    }

}