package apap.ta.ruangan.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import apap.ta.ruangan.Model.PengadaanFasilitasModel;
import apap.ta.ruangan.Repository.PengadaanFasilitasDb;

public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService {
    @Autowired
    private PengadaanFasilitasDb pengadaanFasilitasDb;

    @Override
    public List<PengadaanFasilitasModel> getAllPengadaanFasilitas() {
        return pengadaanFasilitasDb.findAll();
    }

}